package Project.Shop.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import Project.Shop.model.Product;
import Project.Shop.service.CategoryService;
import Project.Shop.service.ProductService;
import Project.Shop.web.dto.ProductDTO;

@Component
public class ProductDtoToProduct implements Converter<ProductDTO, Product>{
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;

	@Override
	public Product convert(ProductDTO dto) {
		Product product = new Product();
		if(dto.getId() == null) {
			product = new Product();
		}else {
			product = productService.findOne(dto.getId());
		}
		if(product != null) {
			product.setName(dto.getName());
			product.setPrice(dto.getPrice());
			product.setQuantity(dto.getQuantity());
			product.setCategory(categoryService.findOne(dto.getCategory().getId()));
		}
		return product;
	}

}
