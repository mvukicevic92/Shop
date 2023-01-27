package Project.Shop.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import Project.Shop.model.Order;
import Project.Shop.web.dto.OrderDTO;

@Component
public class OrderToOrderDto implements Converter<Order, OrderDTO>{

	@Override
	public OrderDTO convert(Order order) {
		OrderDTO dto = new OrderDTO();
		dto.setId(order.getId());
		dto.setQuantity(order.getQuantity());
		return dto;
	}
	
	public List<OrderDTO> convert(List<Order> orders){
		List<OrderDTO> dto = new ArrayList<>();
		for(Order order : orders) {
			dto.add(convert(order));
		}
		return dto;
	}

}
