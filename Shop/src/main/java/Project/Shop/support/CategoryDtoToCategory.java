package Project.Shop.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import Project.Shop.model.Category;
import Project.Shop.service.CategoryService;
import Project.Shop.web.dto.CategoryDTO;

@Component
public class CategoryDtoToCategory implements Converter<CategoryDTO, Category>{
	
	@Autowired
	private CategoryService categoryService;

	@Override
	public Category convert(CategoryDTO dto) {
		Category category = new Category();
		if(dto.getId() == null) {
			category = new Category();
		}else {
			category = categoryService.findOne(dto.getId());
		}
		if(category != null) {
			category.setName(dto.getName());
		}
		return category;
	}

}
