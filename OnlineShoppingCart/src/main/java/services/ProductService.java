package services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baseclasses.Product;

import dao.ProductDAO;

@Component("ps")
public class ProductService {
	@Autowired
	private ProductDAO productDAO;

	public ProductDAO getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	public void createProduct() throws IOException {
		Product p=new Product();
		BufferedImage bimg;
		ByteArrayOutputStream bos;
		byte[] data;
		
		p.setPid(1);
		p.setPname("shampoo");
		p.setPdes("to cleanse hair");
		p.setPqty(5);
		p.setPrice(25);
		bimg=ImageIO.read(new File("shampoo.jpg"));
		bos=new ByteArrayOutputStream();
		ImageIO.write(bimg, "jpg", bos);
		data=bos.toByteArray();
		p.setPimg(data);
		productDAO.save(p);
		
		p=new Product();
		p.setPid(2);
		p.setPname("soap");
		p.setPdes("to cleanse body");
		p.setPqty(20);
		p.setPrice(50);
		bimg=ImageIO.read(new File("soap.jpg"));
		bos=new ByteArrayOutputStream();
		ImageIO.write(bimg, "jpg", bos);
		data=bos.toByteArray();
		p.setPimg(data);
		productDAO.save(p);
		
		p=new Product();
		p.setPid(3);
		p.setPname("scrub");
		p.setPdes("to clean body");
		p.setPqty(30);
		p.setPrice(60);
		bimg=ImageIO.read(new File("scrub1.jpg"));
		bos=new ByteArrayOutputStream();
		ImageIO.write(bimg, "jpg", bos);
		data=bos.toByteArray();
		p.setPimg(data);
		productDAO.save(p);
	}
	public void findProduct(int id)
	{
		Optional<Product> optional=productDAO.findById(Integer.valueOf(id));
		if(!optional.isEmpty())
		{
			Product product=optional.get();
			System.out.println(product.getPid()+" "+product.getPname()+" "+product.getPdes()+" "+product.getPqty());
			ByteArrayInputStream bis=new ByteArrayInputStream(product.getPimg());
			try {
				BufferedImage bimg=ImageIO.read(bis);
				ImageIO.write(bimg,"jpg", new File("output.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public void getAllProducts() {
		List<Product> list=productDAO.findAll();
		for(Product p:list) {
			System.out.println(p.getPid()+" "+p.getPname()+" "+p.getPdes()+" "+p.getPqty());
		}
	}
}
