package Project.Shop.web.dto;

import javax.validation.constraints.Positive;

public class CategoryDTO {
	
	@Positive
	private Long id;
	
	private String name;

	public CategoryDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
