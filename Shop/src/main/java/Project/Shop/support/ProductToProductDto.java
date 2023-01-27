package Project.Shop.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import Project.Shop.model.Product;
import Project.Shop.web.dto.ProductDTO;

@Component
public class ProductToProductDto implements Converter<Product, ProductDTO>{

	@Autowired
	private CategoryToCategoryDto toCategoryDto;
	
	@Override
	public ProductDTO convert(Product product) {
		ProductDTO dto = new ProductDTO();
		dto.setId(product.getId());
		dto.setName(product.getName());
		dto.setPrice(product.getPrice());
		dto.setQuantity(product.getQuantity());
		dto.setCategory(toCategoryDto.convert(product.getCategory()));
		return dto;
	}
	
	public List<ProductDTO> convert(List<Product> products){
		List<ProductDTO> dto = new ArrayList<>();
		for(Product product : products) {
			dto.add(convert(product));
		}
		return dto;
	}

}
