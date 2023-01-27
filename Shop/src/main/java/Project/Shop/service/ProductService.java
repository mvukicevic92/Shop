package Project.Shop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import Project.Shop.model.Product;

public interface ProductService {
	
	Product findOne(Long id);
	List<Product> findAll();
	Page<Product> findAll(Integer pageNo);
	Product save(Product product);
	Product update(Product product);
	Product delete(Long id);
	List<Product> findByCategoryId(Long categoryId);
	Page<Product> search(Double priceFrom, Double priceTo, Long categoryId, Integer pageNo);
	Product buy(Integer quantity, Long productId);

}
