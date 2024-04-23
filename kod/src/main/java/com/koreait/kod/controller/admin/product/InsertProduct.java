package com.koreait.kod.controller.admin.product;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.koreait.kod.biz.productAndWishList.ImageDTO;
import com.koreait.kod.biz.productAndWishList.ImageService;
import com.koreait.kod.biz.productAndWishList.ProductDTO;
import com.koreait.kod.biz.productAndWishList.ProductService;
import com.koreait.kod.controller.util.CreateFileSavePathService;
import com.koreait.kod.controller.util.FileUploadAndCopyService;
import com.koreait.kod.controller.util.LoginCheckAspect.LoginCheck;
import com.koreait.kod.controller.util.LoginCheckAspect.Role;

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
	@LoginCheck(checkRole = Role.ADMIN)
	public String insertProduct(@RequestParam("productCategory") int productCategory,
								@RequestParam("productBrand") String productBrand,
								@RequestParam("productName") String productName,
								@RequestParam("productInfo") String productInfo,
								@RequestParam("productPrice") int productPrice,
								@RequestParam("productStock") int productStock,
								@RequestPart("productImageList") List<MultipartFile> productImageList,
								ProductDTO productDTO, 
								ImageDTO imageDTO,
								HttpSession session,
								HttpServletRequest request) {
		
		// 등록할 파일이 존재한다면 파일 업로드와 파일복사 실행
        if(productImageList != null && !productImageList.isEmpty()) {
        	// 파일 저장 경로 설정 [원본파일 경로, 썸네일파일 경로]
        	String[] filePaths = fileSavePathService.getUploadFilePath(request); 
            List<String> imageUrlList;
			try { // 파일 업로드와 파일복사 실행
				imageUrlList = fileUploadAndCopyService.uploadAndCopy(filePaths, productImageList); 
			} catch (IOException e) {
				System.out.println("fileUploadAndCopyService 오류발생");
				return "common/error";
			}
			
			// 상품정보 설정, 상품등록
			productDTO.setCategoryID(productCategory);
			productDTO.setProductBrand(productBrand);
			productDTO.setProductName(productName);
			productDTO.setProductInfo(productInfo);
			productDTO.setProductPrice(productPrice);
			productDTO.setProductStock(productStock);
			boolean flag = productService.insert(productDTO);
			
			// 썸네일파일 DB 등록
            for (String imageUrl : imageUrlList) { 
            	productDTO.setSearchCondition("getProductID");
            	productDTO.setProductName(productName); // PRODUCT_NAME
            	int productID = productService.selectOne(productDTO).getProductID();
            	System.out.println("로그:정현진] productID : "+productID);
            	imageDTO.setProductID(productID);
            	imageDTO.setImageUrl(imageUrl);
            	imageService.insert(imageDTO);
			}
        }

		return "admin/product/insertProduct";
	}
}
