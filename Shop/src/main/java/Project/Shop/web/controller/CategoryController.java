package Project.Shop.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Project.Shop.model.Category;
import Project.Shop.service.CategoryService;
import Project.Shop.support.CategoryToCategoryDto;
import Project.Shop.web.dto.CategoryDTO;

@RestController
@RequestMapping(value = "/api/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CategoryToCategoryDto toCategoryDto;
	
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> getAll(@RequestParam(required = false, defaultValue = "0") Integer pageNo){
		Page<Category> page = categoryService.findAll(pageNo);
		return new ResponseEntity<>(toCategoryDto.convert(page.getContent()), HttpStatus.OK);
	}

}
