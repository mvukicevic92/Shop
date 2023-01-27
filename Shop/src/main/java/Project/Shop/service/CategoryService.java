package Project.Shop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import Project.Shop.model.Category;

public interface CategoryService {
	
	Category findOne(Long id);
	List<Category> findAll();
	Page<Category> findAll(Integer pageNo);
	Category save(Category category);
	Category update(Category category);
	Category delete(Long id);

}
