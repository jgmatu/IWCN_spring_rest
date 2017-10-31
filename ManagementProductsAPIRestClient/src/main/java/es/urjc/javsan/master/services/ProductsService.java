package es.urjc.javsan.master.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import es.urjc.javsan.master.entities.Product;

@Service
public class ProductsService {

	private static final String REST = "http://localhost:8080";
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Product get(int code) {
		String url = REST + "/product?code="+code;
		ResponseEntity<Product> response = restTemplate.getForEntity(url, Product.class);

		return response.getBody();
	}
	
	public String insert(Product p) {
		String url = REST + "/add";
		ResponseEntity<String> response = restTemplate.postForEntity(url, p, String.class);
		
		return response.getBody();
	}
	
	public String edit(Product p) {
		String url = REST + "/edit?code="+p.getCode();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		return response.getBody();
	}
	
	public String delete(int code) {
		String url = REST + "/delete?code="+code;		
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		return response.getBody();
	}
	
	public Product[] findAll() {
		String url = REST + "/list";
		ResponseEntity<Product[]> responseEntity = restTemplate.getForEntity(url, Product[].class);

		return responseEntity.getBody();
	}
}
