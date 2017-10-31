package es.urjc.javsan.master;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;


import es.urjc.javsan.master.entities.Product;

@RunWith(SpringRunner.class)
public class ProductTest {

	private Product productOne;
	private Product productTwo;
	private Product productThree;
	
	@Before 
	public void initialize() {
		productOne = new Product(10, "test", "test", 11);
		productTwo = new Product(10, "test", "test", 11);
		productThree = new Product(12, "test", "test", 11);
	}
	
	@Test
	public void equalsTest() {
		assertEquals(productOne, productTwo);
		assertNotEquals(productOne, productThree);
		assertNotEquals(productTwo, productThree);
	}
}
