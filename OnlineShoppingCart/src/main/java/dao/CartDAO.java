package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baseclasses.Cart;

public interface CartDAO extends JpaRepository<Cart,Integer> {

}
