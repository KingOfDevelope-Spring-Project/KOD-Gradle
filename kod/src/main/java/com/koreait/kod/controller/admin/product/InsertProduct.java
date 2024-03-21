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
	FileUploadAndCopyService fileUploadAndCopyService; // Service
		
	@PostMapping("/insertProduct")
	public String insertProduct(@RequestParam("productCategory") int productCategory,
								@RequestParam("productBrand") String productBrand,
								@RequestParam("productName") String productName,
								@RequestParam("productInfo") String productInfo,
								@RequestParam("productPrice") int productPrice,
								@RequestParam("productStock") int productStock,
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
		
		// 상품 등록
		productDTO.setCategoryID(productCategory);
		productDTO.setProductBrand(productBrand);
		productDTO.setProductName(productName);
		productDTO.setProductInfo(productInfo);
		productDTO.setProductPrice(productPrice);
		productDTO.setProductStock(productStock);
		productService.insert(productDTO);
		
		// 이미지 파일이 업로드되었을 경우에만 업로드 및 파일복사 실행
		System.out.println("[로그:정현진] productImageList 개수 : "+productImageList.size());
        if(productImageList != null && !productImageList.isEmpty()) {
        	String[] filePaths = fileSavePathService.getUploadFilePath(request);
            List<String> imageUrlList;
			try {
				imageUrlList = fileUploadAndCopyService.uploadAndCopy(filePaths, productImageList);
			} catch (IOException e) {
				System.out.println("fileUploadAndCopyService에서 오류발생");
				
				return "common/error";
			}
            for (String imageUrl : imageUrlList) {
            	productDTO.setProductName(productName);
            	System.out.println("[로그:정현진] productID : "+imageDTO.getProductID());
            	System.out.println("[로그:정현진] imageUrl : "+imageUrl);
            	imageDTO.setProductID(productService.selectOne(productDTO).getProductID());
            	imageDTO.setImageUrl(imageUrl);
            	imageService.insert(imageDTO);
			}
        }

		return "admin/product/insertProduct";
	}
}
