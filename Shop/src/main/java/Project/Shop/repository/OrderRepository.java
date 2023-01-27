package Project.Shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Project.Shop.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	Order findOneById(Long id);
	List<Order> findByProductId(Long productId);
}
