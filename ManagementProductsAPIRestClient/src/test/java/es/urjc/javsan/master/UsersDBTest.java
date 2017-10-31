package es.urjc.javsan.master;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;

import es.urjc.javsan.master.customers.UsersDB;
import es.urjc.javsan.master.customers.UsersRepo;
import es.urjc.javsan.master.entities.User;


@RunWith(SpringRunner.class)
public class UsersDBTest {

	private static final int NUM_USERS = 2;
	
	@Mock
	private UsersRepo userRepository;
	
	@InjectMocks
	private UsersDB usersDB;
		
	@Before
	public void init() {
		usersDB = new UsersDB();
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void userDBTest(){
		ArrayList<User> users = new ArrayList<>();
		
		SimpleGrantedAuthority[] userRoles = {new SimpleGrantedAuthority("ROLE_USER")};
		users.add(new User("user", "user1", Arrays.asList(userRoles)));

		SimpleGrantedAuthority[] adminRoles = {new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN")};
		users.add(new User("root", "root1", Arrays.asList(adminRoles)));
		
		when(userRepository.findAll()).then(answer ->{
        	return users;
        });
		assertEquals(usersDB.findAll().size(), NUM_USERS);
	}
}
