package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baseclasses.Cart;

import dao.CartDAO;

@Service("cartService")
public class CartService {
@Autowired
private CartDAO cd;

public CartDAO getCd() {
	return cd;
}

public void setCd(CartDAO cd) {
	this.cd = cd;
}
public List<Cart> getAllItems() {
	return(cd.findAll());
}
public Cart findItem(int pid) {
	
	return(cd.getById(pid));
}
public void Store(int pid,int qty) {
	Cart c=new Cart();
	c.setPid(pid);
	c.setQty(qty);
	cd.save(c);
}
public void deleteItem(int pid) {
	cd.deleteById(pid);
}
public void deleteAll() {
	cd.deleteAll();
}
}
