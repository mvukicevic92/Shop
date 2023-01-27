package Project.Shop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Project.Shop.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	Product findOneById(Long id);
	List<Product> findByCategoryId(Long categoryId);
	Page<Product> findByPriceBetween(Double priceFrom, Double priceTo, Pageable pageable);
	Page<Product> findByPriceBetweenAndCategoryId(Double priceFrom, Double priceTo, Long categoryId, Pageable pageable);

}
