package es.urjc.javsan.master;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;

import es.urjc.javsan.master.entities.User;

@RunWith(SpringRunner.class)
public class UserTest {

	private User userOne;
	private User userTwo;
	private User adminOne;
	
	@Before 
	public void initialize() {
		SimpleGrantedAuthority[] userRoles = {new SimpleGrantedAuthority("ROLE_USER")};
		SimpleGrantedAuthority[] adminRoles = {new SimpleGrantedAuthority("ROLE_USER"), 
				new SimpleGrantedAuthority("ROLE_ADMIN")};
		
		userOne = new User("user", "user1", Arrays.asList(userRoles));
		userTwo = new User("user", "user1", Arrays.asList(userRoles));		
		
		adminOne = new User("admin", "user1", Arrays.asList(adminRoles));
	}
	
	@Test
	public void equalsTest() {
		assertEquals(userOne, userTwo);
		assertNotEquals(userOne, adminOne);
		assertNotEquals(userTwo, adminOne);		
		assertArrayEquals(userOne.getRoles().toArray(), userTwo.getRoles().toArray());
	}
}