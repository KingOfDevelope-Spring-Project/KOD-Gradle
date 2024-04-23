package com.koreait.kod.controller.admin.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.kod.biz.productAndWishList.CategoryDTO;
import com.koreait.kod.biz.productAndWishList.CategoryService;
import com.koreait.kod.biz.productAndWishList.ImageDTO;
import com.koreait.kod.biz.productAndWishList.ImageService;
import com.koreait.kod.biz.productAndWishList.ProductDTO;
import com.koreait.kod.biz.productAndWishList.ProductService;
import com.koreait.kod.controller.util.LoginCheckAspect.LoginCheck;
import com.koreait.kod.controller.util.LoginCheckAspect.Role;

import jakarta.servlet.http.HttpSession;

@Controller
public class GetUpdateProductPage {

   @Autowired
   ProductService productService;
   @Autowired
   CategoryService categoryService;
   @Autowired
   ImageService imageService;
   
   @GetMapping("/getUpdateProductPage")
   @LoginCheck(checkRole = Role.ADMIN)
   public String getProductData(ProductDTO productDTO,CategoryDTO categoryDTO, ImageDTO imageDTO, Model model, HttpSession session) {
      
      System.out.println("!@#!@#!@#!@#" + productDTO);
      productDTO.setSearchCondition("getProductData");
      model.addAttribute("productDatas", productService.selectOne(productDTO));
      System.out.println("@@@@@@@@@@"+model.getAttribute("productDatas"));
      
      model.addAttribute("categoryDatas", categoryService.selectAll(categoryDTO));
      model.addAttribute("imageDatas", imageService.selectAll(imageDTO));
      System.out.println("@@ 이미지 데이터 : "+model.getAttribute("imageDatas"));
      return "admin/product/productUpdate";
   }
}