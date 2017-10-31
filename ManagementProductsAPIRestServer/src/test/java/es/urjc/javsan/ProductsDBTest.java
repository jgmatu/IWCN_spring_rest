package es.urjc.javsan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import es.urjc.javsan.master.configuration.ProductsDB;
import es.urjc.javsan.master.configuration.ProductsRepository;
import es.urjc.javsan.master.entities.Product;

@RunWith(SpringRunner.class)
public class ProductsDBTest {

	
	@Mock
	private ProductsRepository productRepo;
	
	@InjectMocks
	private ProductsDB productsDB;
	
	private static int NUM_PRODUCTS = 5;
	private static int PRICE = 10;
	
	@Before
	public void initBefore() {
		productsDB = new ProductsDB();
		
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void productDBfindOne() {
		Product product = new Product(10, "test", "testGet", 120.0f);
		
	    when(productRepo.findOne(10)).then(answer ->{
        	return product;
        });
	    assertNotNull(productsDB.get(10));	
	    assertEquals(product, productsDB.get(10));
	}
	
	@Test
	public void productDBDeleteTest() {
		productsDB.delete(1);
		verify(productRepo, times(1)).delete(1);
	}
	
	@Test
	public void productDBSaveTest() {
		Product product = new Product(11, "test", "testSave", 120.0f);
	
		productsDB.add(product);
		verify(productRepo, times(1)).save(product);
	}
	
	@Test
	public void productDBEditTest() {
		Product product = new Product(11, "test", "testSave", 120.0f);
		
		productsDB.edit(product);
		verify(productRepo, times(1)).save(product);		
	}
	
	@Test
	public void productDBfindAllTest() {
		ArrayList<Product> products = getProductsTest();
		
		when(productRepo.findAll()).then(answer ->{
        	return products;
        });
	    assertEquals(productsDB.findAll().size(), NUM_PRODUCTS);	    
	}
	
	private ArrayList<Product> getProductsTest() {
		ArrayList<Product> products = new ArrayList<>();
		
		for (int i = 0; i < NUM_PRODUCTS; i++) {
			products.add(new Product(i, "test", "test", i*PRICE));			
		}		
		return products;
	}
}
