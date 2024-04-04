package com.koreait.kod.controller.admin.product;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.koreait.kod.biz.productAndWishList.ImageDTO;
import com.koreait.kod.biz.productAndWishList.ImageService;
import com.koreait.kod.biz.productAndWishList.ProductDTO;
import com.koreait.kod.biz.productAndWishList.ProductService;
import com.koreait.kod.controller.user.review.FileUploadAndCopyService;
import com.koreait.kod.controller.util.CreateFileSavePathService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UpdateProduct {

	@Autowired
	ProductService productService;
	@Autowired
	ImageService imageService;
	@Autowired
    CreateFileSavePathService fileSavePathService;
	@Autowired
	FileUploadAndCopyService fileUploadAndCopyService; // Service
	
	@PostMapping("/updateProduct")
	public String updateProduct(@RequestParam("productCategory") int productCategory,
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
		
//		if(!((MemberDTO)session.getAttribute("adminDTO")).getMemberRole().equals("ADMIN")) {
//			return "common/error";
//		}
		
//		productService.update(productDTO);
		
		// 상품정보 변경 기능에서 이미지를 변경하려고 할 때 , 기존에 존재하는 경로의 이미지파일을 삭제하고 새로운 이미지를 등록해야하나요 ?
		// 아니면 기존에 존재하는 경로의 이미지파일을 남겨둔 채 새로운 이미지를 등록해야하나요 ?
		// DB에서는 이미지ID값을 찾아서 이미지URL만 UPDATE할 계획입니다.
		
		// 추가적으로 이미지 파일이 계속해서 쌓일텐데 관리를 어떻게 해줘야하는지 궁금합니다. 삭제하는지 남겨두는지
		
		
		productDTO.setCategoryID(productCategory);
		productDTO.setProductBrand(productBrand);
		productDTO.setProductName(productName);
		productDTO.setProductInfo(productInfo);
		productDTO.setProductPrice(productPrice);
		productDTO.setProductStock(productStock);
		System.out.println("[로그:정현진] productDTO : "+productDTO);
		boolean flag = productService.update(productDTO);
		System.out.println("[로그:정현진] 상품정보 변경 flag : "+flag);
		
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
	        	productDTO.setSearchCondition("getProductID");
	        	productDTO.setProductName(productName);
//		        System.out.println("[로그:정현진] productID : "+imageDTO.getProductID());
//		        System.out.println("[로그:정현진] imageUrl : "+imageUrl);
	        	int productID = productService.selectOne(productDTO).getProductID();
	        	System.out.println("로그:정현진] productID : "+productID);
	        	imageDTO.setProductID(productID);
	        	imageDTO.setImageUrl(imageUrl);
	        	imageService.update(imageDTO);
			}
	    }
		
		return "admin/product/productUpdate";
	}
}
