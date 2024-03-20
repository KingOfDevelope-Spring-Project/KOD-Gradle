package com.koreait.kod.controller.admin.product;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.koreait.kod.biz.member.MemberDTO;
import com.koreait.kod.biz.productAndWishList.CategoryDTO;
import com.koreait.kod.biz.productAndWishList.ImageDTO;
import com.koreait.kod.biz.productAndWishList.ProductDTO;
import com.koreait.kod.biz.productAndWishList.ProductService;
import com.koreait.kod.controller.user.review.FileUploadAndCopyService;

import jakarta.servlet.http.HttpSession;

@Controller
public class InsertProduct {

	@Autowired
	ProductService productService;
	@Autowired
	FileUploadAndCopyService fileUploadAndCopy;
	@Value("${upload.file.productImagePath}")  
    private String uploadFilePath;
	// @Value() -> @어노테이션 설정을 공부하기 위해 추가한 코드(절대경로사용), 
	// 중간프로젝트 리뷰작성 로직에서 사용자환경에 따라 경로를 설정할수있도록 구현한 코드가 존재하여 다른방식으로 구현하였음
	
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
								CategoryDTO categoryDTO,
								ImageDTO imageDTO,
								HttpSession session) throws IOException {
		
		if(!((MemberDTO)session.getAttribute("adminDTO")).getMemberRole().equals("ADMIN")) {
			return "common/error";
		}
		
		/*
		 * 상품등록 한글코딩
		 * 1. 상품이미지 가져오기
		 * 1-1. 만약 등록할 이미지가 있다면 원본파일 저장, 복사파일 생성, 복사파일 저장
		 * 2. 상품 정보 등록 
		 * 3. 등록완료
		 * 
		 * 상품정보를 가져오기 위해서는 ProductDTO가 필요함
		 * 그리고 이미지등록 유무를 확인하여 파일저장 로직 수행 
		 * 
		 */
		
		
		productDTO.setCategoryID(productCategory);
		productDTO.setProductBrand(productBrand);
		productDTO.setProductName(productName);
		productDTO.setProductInfo(productInfo);
		productDTO.setProductPrice(productPrice);
		productDTO.setProductStock(productStock);
		productService.insert(productDTO);
		
		productDTO.setProductName(productName);
		int productID = productService.selectOne(productDTO).getProductID();
		
		imageDTO.setProductID(productID);
		imageDTO.setImageUrl(productImageName);
		
		
		
		
		// 파일이 업로드되었을 경우에만 파일 처리
		if(!(productImageList==null || productImageList.size()==0)) {
			List<String> uuids = fileUploadAndCopy.uploadAndCopy(uploadFilePath, productImageList);
		
			// 첫 번째 파일에 대한 경로 생성
			String filePath = uploadFilePath + File.separator + uuids.get(0) + "_" + productImageName;
            File copyFile = new File(filePath);
		
         // 복사 파일이 존재하지 않을 경우 생성
            if (!copyFile.exists()) { 
                if (copyFile.createNewFile()) {
                	imageDTO.setImageUrl(copyFile.getAbsolutePath());
                } else {
                    // 파일 생성 실패 시 예외 처리
                    throw new IOException("파일생성 실패, 예외처리: " + copyFile);
                }
            }
            else {// 파일이 이미 존재할 경우 처리 == 덮어쓰기
                // 새로운 파일명 생성: 원본 파일명 + 현재 시간(밀리초 단위)을 이용하여 중복을 피함
                String newFileName = uuids.get(0) + "_" + System.currentTimeMillis() + "_" + productImageName;
                File newCopyFile = new File(uploadFilePath + File.separator + newFileName);
                
                // 새로운 파일이 존재하지 않을 경우 생성
                if (!newCopyFile.exists()) {
                    if (newCopyFile.createNewFile()) {
                        // 파일 생성 성공 시 ReviewDTO의 이미지 경로를 새로운 파일의 절대 경로로 설정
                    	imageDTO.setImageUrl(productInfo);
                    } 
                    else {// 파일 생성 실패 시 예외 처리
                        throw new IOException("파일 생성 실패 : " + newCopyFile);
                    }
                } 
                else {// 파일이 이미 존재할 경우 에러 메시지 반환 또는 적절한 처리
                    throw new IOException("파일이 이미 존재 : " + newCopyFile);
                }
            }
		}
	    else {
            imageDTO.setImageUrl(null);
        }
		
		productService.insert(productDTO);

		
		return "admin/product/insertProduct";
		
	}
}
