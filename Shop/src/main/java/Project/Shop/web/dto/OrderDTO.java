package Project.Shop.web.dto;

import javax.validation.constraints.Positive;

import Project.Shop.model.Product;

public class OrderDTO {
	
	@Positive
	private Long id;
	
	@Positive
	private Integer quantity;
	
	private Product product;

	public OrderDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	

}
