package com.koreait.kod.biz.productAndWishList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("productService")
public class ProductServiceImpl implements ProductService{
	@Autowired 
	ProductDAO productDAO;
	@Autowired
	IProductDAO iproductDAO;

	@Override
	public List<ProductDTO> selectAll(ProductDTO productDTO) {

		if (productDTO.getSearchCondition().equals("allProductsDatas")) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("productID", productDTO.getProductID());
			map.put("productName", productDTO.getProductName());
			map.put("productMinPrice", productDTO.getProductMinPrice());
			map.put("productMaxPrice", productDTO.getProductMaxPrice());
			map.put("productBrand", productDTO.getProductBrand());
			map.put("categoryID", productDTO.getCategoryID());
			map.put("productCategory", productDTO.getProductCategory());
			System.out.println(productDTO.getProductName());
			System.out.println("형련" + map);
			return iproductDAO.selectAll(map);
		}
		else {
			return productDAO.selectAll(productDTO);
		}
	}

	@Override
	public ProductDTO selectOne(ProductDTO productDTO) {
		return productDAO.selectOne(productDTO);

	}

	@Override
	public boolean insert(ProductDTO productDTO) {
		return productDAO.insert(productDTO); 
		
	} 

	@Override
	public boolean update(ProductDTO productDTO) {
		return productDAO.update(productDTO);
	
	}

	@Override
	public boolean delete(ProductDTO productDTO) {
		return productDAO.delete(productDTO);
	}
}
