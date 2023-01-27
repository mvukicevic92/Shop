package Project.Shop.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import Project.Shop.model.Order;
import Project.Shop.service.OrderService;
import Project.Shop.service.ProductService;
import Project.Shop.web.dto.OrderDTO;

@Component
public class OrderDtoToOrder implements Converter<OrderDTO, Order>{
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;

	@Override
	public Order convert(OrderDTO dto) {
		Order order = new Order();
		if(dto.getId() == null) {
			order = new Order();
		}else {
			order = orderService.findOne(dto.getId());
		}
		if(order != null) {
			order.setQuantity(dto.getQuantity());
			order.setProduct(productService.findOne(dto.getProduct().getId()));
		}
		return order;
	}

}
