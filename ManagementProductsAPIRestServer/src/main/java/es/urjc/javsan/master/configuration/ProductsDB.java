package es.urjc.javsan.master.configuration;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.javsan.master.entities.Product;

@Service
public class ProductsDB {

	@Autowired
	private ProductsRepository customRepo;
	
	@PostConstruct
	private void initDatabase() {	
		System.out.println("Data base initialized...");
	}
	
	public void add(Product product) {
		customRepo.save(product);
	}

	public void delete(int code) {
		customRepo.delete(code);
	}

	public Product get(int code) {
		return customRepo.findOne(code);
	}
	
	public void edit(Product product) {
		customRepo.save(product);
	}
	
	public ArrayList<Product> findAll() {
		ArrayList<Product> products = new ArrayList<>();
		
		for (Product p : customRepo.findAll()) {
			products.add(p);
		}	
		return products;
	}	
}
