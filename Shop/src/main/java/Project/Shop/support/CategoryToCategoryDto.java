package Project.Shop.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import Project.Shop.model.Category;
import Project.Shop.web.dto.CategoryDTO;

@Component
public class CategoryToCategoryDto implements Converter<Category, CategoryDTO>{

	@Override
	public CategoryDTO convert(Category category) {
		CategoryDTO dto = new CategoryDTO();
		dto.setId(category.getId());
		dto.setName(category.getName());
		return dto;
	}
	
	public List<CategoryDTO> convert(List<Category> categories){
		List<CategoryDTO> dto = new ArrayList<>();
		for(Category category : categories) {
			dto.add(convert(category));
		}
		return dto;
	}

}
