package es.urjc.javsan.master;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import es.urjc.javsan.master.entities.Product;
import es.urjc.javsan.master.services.ProductsService;


@RunWith(SpringRunner.class)
public class ClientRestTest {
	
	private static final int NUM_PRODUCTS = 3;
	private static final String REST = "http://localhost:8080";
	private static final String RESULT = "Ok!";
	
	@Mock
	private RestTemplate restTemplateTest;
	
	@InjectMocks
	private ProductsService productsService;
	
	@Before
	public void init() {
		productsService = new ProductsService();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void addProductTest() {
		Product p = new Product(11, "test", "testing", 1.0f);
		String url = REST + "/add";
		
		when(restTemplateTest.postForObject(url, p, String.class)).then(answer ->{
        	return RESULT;
        });
		assertEquals(RESULT, productsService.insert(p));
	    verify(restTemplateTest, times(1)).postForObject(url, p, String.class);

	}
	
	@Test
	public void editProductTest() {
		Product p = new Product(11, "test", "testing", 1.0f);
		String url = REST + "/edit?code="+p.getCode();
		
		when(restTemplateTest.postForObject(url, p, String.class)).then(answer ->{
        	return RESULT;
        });
		assertEquals(RESULT, productsService.edit(p));
	    verify(restTemplateTest, times(1)).postForObject(url, p, String.class);		
	}
	
	@Test
	public void getProductTest() {
		Product p = new Product(11, "test", "testing", 1.0f);
		String url = REST + "/product?code="+p.getCode();
		
		when(restTemplateTest.getForObject(url, Product.class)).then(answer ->{
        	return p;
        });
		assertNotNull(productsService.get(p.getCode()));
		assertEquals(p, productsService.get(p.getCode()));
	    verify(restTemplateTest, times(2)).getForObject(url, Product.class);
	}
	
	@Test
	public void listProductsTest() {
		String url = REST + "/list";
		Product[] products = new Product[NUM_PRODUCTS];

		for (int i = 0; i < NUM_PRODUCTS; i++) {
			products[i] = new Product(i, "Testing", "Test", i*1.0f);			
		}
		
		when(restTemplateTest.getForEntity(url, Product[].class)).then(answer ->{
			HttpHeaders responseHeaders = new HttpHeaders();
			
        	return new ResponseEntity<Product[]>(products, responseHeaders, HttpStatus.CREATED);
        });
				
		int total = 0;
		for (Product p : productsService.findAll()) {
			assertEquals(p, products[total]);
			total++;
		}	
		assertEquals(total, NUM_PRODUCTS);
	    verify(restTemplateTest, times(1)).getForEntity(url, Product[].class);	
	}

}
