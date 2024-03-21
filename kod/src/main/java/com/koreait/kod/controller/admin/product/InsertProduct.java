package com.koreait.kod.controller.admin.product;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.productAndWishList.ImageDTO;
import com.koreait.kod.biz.productAndWishList.ImageService;
import com.koreait.kod.biz.productAndWishList.ProductDTO;
import com.koreait.kod.biz.productAndWishList.ProductService;
import com.koreait.kod.controller.user.review.FileUploadAndCopyService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class InsertProduct { // Controller

	@Autowired
	ProductService productService;
	@Autowired
	ImageService imageService;
	@Autowired
    CreateFileSavePathService fileSavePathService;
	@Autowired
	FileUploadAndCopyService fileUploadAndCopy; // Service
		
	@PostMapping("/insertProduct")
	public String insertProduct(@RequestParam("productCategory") int productCategory,
								@RequestParam("productBrand") String productBrand,
								@RequestParam("productName") String productName,
								@RequestParam("productInfo") String productInfo,
								@RequestParam("productPrice") int productPrice,
								@RequestParam("productStock") int productStock,
								@RequestParam("productImageName") String productImageName,
								@RequestParam("productImageList") List<MultipartFile> productImageList,
								ProductDTO productDTO, 
								ImageDTO imageDTO,
								HttpSession session,
								HttpServletRequest request) {
		
		// 관리자가 아니라면
		// ~~~로 이동
		if(!((MemberDTO)session.getAttribute("adminDTO")).getMemberRole().equals("ADMIN")) {
			return "common/error";
		}
		
		/*
		 * 상품등록 한글코딩
		 * 1. 상품이미지 가져오기
		 * 2. 만약 등록할 이미지가 있다면
		 *   2-1. 파일을 저장할 경로 얻어오기
		 *   2-2. 원본폴더 경로에 원본파일 저장, 복사파일 생성, 복사폴더 경로에 복사파일 저장
		 * 3. 상품 등록 
		 * 
		 * 상품정보를 가져오기 위해서는 ProductDTO가 필요함
		 * 그리고 이미지등록 유무를 확인하여 파일저장 로직 수행 
		 * 
		 */
		
		// 이미지 파일이 업로드되었을 경우에만 업로드 및 파일복사 실행
        if(productImageList != null && !productImageList.isEmpty()) {
        	String[] filePaths = fileSavePathService.getUploadFilePath(request);
            List<String> uuids;
			try {
				uuids = fileUploadAndCopy.uploadAndCopy(filePaths, productImageList);
			} catch (IOException e) {
				System.out.println("InsertProductController에서 오류발생");
				e.printStackTrace();
				
				return "에러페이지로 이동";
			}
            for (String uuid : uuids) {
            	imageDTO.setImageUrl(uuid);
            	imageService.insert(imageDTO);
			}
        }
		
        // 상품 등록
		productDTO.setCategoryID(productCategory);
		productDTO.setProductBrand(productBrand);
		productDTO.setProductName(productName);
		productDTO.setProductInfo(productInfo);
		productDTO.setProductPrice(productPrice);
		productDTO.setProductStock(productStock);
		productService.insert(productDTO);
		
		return "admin/product/insertProduct";
	}
}
