package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baseclasses.Product;

public interface ProductDAO extends JpaRepository<Product,Integer>{

}
