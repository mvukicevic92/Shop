package Project.Shop.web.dto;

import javax.validation.constraints.Positive;

public class BuyDTO {
	
	@Positive
	private Integer quantity;
	
	private Long productId;

	public BuyDTO() {
		super();
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	

}
