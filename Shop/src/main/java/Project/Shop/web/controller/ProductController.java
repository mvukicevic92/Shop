package Project.Shop.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Project.Shop.model.Product;
import Project.Shop.service.ProductService;
import Project.Shop.support.ProductDtoToProduct;
import Project.Shop.support.ProductToProductDto;
import Project.Shop.web.dto.BuyDTO;
import Project.Shop.web.dto.ProductDTO;

@RestController
@RequestMapping(value = "/api/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductDtoToProduct toProduct;
	
	@Autowired
	private ProductToProductDto toProductDto;
	
	@GetMapping
	public ResponseEntity<List<ProductDTO>> getAll(@RequestParam(required = false) Double priceFrom,
												   @RequestParam(required = false) Double priceTo,
												   @RequestParam(required = false) Long categoryId,
												   @RequestParam(required = false, defaultValue = "0") Integer pageNo){
		Page<Product> page;
		try {
			page = productService.search(priceFrom, priceTo, categoryId, pageNo);
		}catch(Exception e) {
			page = productService.findAll(pageNo);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Total-Pages", Integer.toString(page.getTotalPages()));
		
		return new ResponseEntity<>(toProductDto.convert(page.getContent()), HttpStatus.OK);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO productDto){
		Product product = toProduct.convert(productDto);
		if(product.getCategory() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Product savedProduct = productService.save(product);
		
		return new ResponseEntity<>(toProductDto.convert(savedProduct), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO productDto){
		if(!id.equals(productDto.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Product product = toProduct.convert(productDto);
		if(product.getCategory() == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Product savedProduct = productService.update(product);
		
		return new ResponseEntity<>(toProductDto.convert(savedProduct), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> delete (@PathVariable Long id){
		Product product = productService.delete(id);
		if(product == null) {
			return new ResponseEntity<>(toProductDto.convert(product), HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> getOne(@PathVariable Long id){
		Product product = productService.findOne(id);
		if(product != null) {
			return new ResponseEntity<>(toProductDto.convert(product), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value = "/buy", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> buy(@Valid @RequestBody BuyDTO buyDto){
		Product product = productService.buy(buyDto.getQuantity(), buyDto.getProductId());
		return new ResponseEntity<>(toProductDto.convert(product), HttpStatus.OK);
	}

}
