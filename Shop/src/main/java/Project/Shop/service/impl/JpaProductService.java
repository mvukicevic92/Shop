package Project.Shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import Project.Shop.model.Product;
import Project.Shop.repository.ProductRepository;
import Project.Shop.service.ProductService;

@Service
public class JpaProductService implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product findOne(Long id) {
		return productRepository.findOneById(id);
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Page<Product> findAll(Integer pageNo) {
		return productRepository.findAll(PageRequest.of(pageNo, 5));
	}

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product update(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product delete(Long id) {
		Product product = productRepository.findOneById(id);
		if(product != null) {
			product.getCategory().getProducts().remove(product);
			productRepository.delete(product);
			return product;
		}
		return null;
	}

	@Override
	public List<Product> findByCategoryId(Long categoryId) {
		return productRepository.findByCategoryId(categoryId);
	}

	@Override
	public Page<Product> search(Double priceFrom, Double priceTo, Long categoryId, Integer pageNo) {
		if(priceFrom == null) {
			priceFrom = 0.0;
		}
		if(priceTo == null) {
			priceTo = Double.MAX_VALUE;
		}
		if(categoryId == null) {
			Page<Product> product = productRepository.findByPriceBetween(priceFrom, priceTo, PageRequest.of(pageNo, 5));
			return product;
		}
		return productRepository.findByPriceBetweenAndCategoryId(priceFrom, priceTo, categoryId, PageRequest.of(pageNo, 5));
	}

	@Override
	public Product buy(Integer quantity, Long productId) {
		Product product = productRepository.findOneById(productId);
		Integer newQuantity = product.getQuantity() - quantity;
		if(product.getQuantity() > quantity) {
			product.setQuantity(newQuantity);
		}
		return productRepository.save(product);
	}

}
