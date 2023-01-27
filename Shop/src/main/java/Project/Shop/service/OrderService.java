package Project.Shop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import Project.Shop.model.Order;

public interface OrderService {
	
	Order findOne(Long id);
	List<Order> findAll();
	Page<Order> findAll(Integer pageNo);
	Order save(Order order);
	Order update(Order order);
	Order delete(Long id);
	List<Order> findByProductId(Long productId);
}
