package com.koreait.kod.biz.productAndWishList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("imageService")
public class ImageServiceImpl implements ImageService  {
	
	@Autowired
	ImageDAO imageDAO;
	@Override
	public List<ImageDTO> selectAll(ImageDTO imageDTO) {
		return imageDAO.selectAll(imageDTO);
	}

	@Override
	public ImageDTO selectOne(ImageDTO imageDTO) {
		return imageDAO.selectOne(imageDTO);
	}

	@Override
	public boolean insert(ImageDTO imageDTO) {
		return imageDAO.insert(imageDTO);
	}

	@Override
	public boolean update(ImageDTO imageDTO) {
		return imageDAO.update(imageDTO);
	}

	@Override
	public boolean delete(ImageDTO imageDTO) {
		return imageDAO.delete(imageDTO);
	}

}
