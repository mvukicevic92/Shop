package Project.Shop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import Project.Shop.model.Category;
import Project.Shop.repository.CategoryRepository;
import Project.Shop.service.CategoryService;

@Service
public class JpaCategoryService implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category findOne(Long id) {
		return categoryRepository.findOneById(id);
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Page<Category> findAll(Integer pageNo) {
		return categoryRepository.findAll(PageRequest.of(pageNo, 5));
	}

	@Override
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category update(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category delete(Long id) {
		Optional<Category> category = categoryRepository.findById(id);
		if(category.isPresent()) {
			categoryRepository.deleteById(id);
			return category.get();
		}
		return null;
	}

}
