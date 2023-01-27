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

import Project.Shop.model.Order;
import Project.Shop.service.OrderService;
import Project.Shop.support.OrderToOrderDto;
import Project.Shop.web.dto.OrderDTO;

@RestController
@RequestMapping(value = "/api/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderToOrderDto toOrderDto;
	
	@GetMapping
	public ResponseEntity<List<OrderDTO>> getAll(@RequestParam(required = false, defaultValue = "0") Integer pageNo){
		Page<Order> page = orderService.findAll(pageNo);
		return new ResponseEntity<>(toOrderDto.convert(page.getContent()), HttpStatus.OK);
	}

}
