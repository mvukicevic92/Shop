package Project.Shop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import Project.Shop.model.Order;
import Project.Shop.repository.OrderRepository;
import Project.Shop.service.OrderService;

@Service
public class JpaOrderService implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Order findOne(Long id) {
		return orderRepository.findOneById(id);
	}

	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public Page<Order> findAll(Integer pageNo) {
		return orderRepository.findAll(PageRequest.of(pageNo, 5));
	}

	@Override
	public Order save(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Order update(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Order delete(Long id) {
		Optional<Order> order = orderRepository.findById(id);
		if(order.isPresent()) {
			orderRepository.deleteById(id);
			return order.get();
		}
		return null;
	}

	@Override
	public List<Order> findByProductId(Long productId) {
		return orderRepository.findByProductId(productId);
	}

}
