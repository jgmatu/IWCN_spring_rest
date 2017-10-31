package es.urjc.javsan.master.customers;

import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import es.urjc.javsan.master.entities.User;

@Service
public class UsersDB {
	
	@Autowired
	private UsersRepo usersRepo;
	
    @PostConstruct
	private void initDatabase() {	
		System.out.println("Data base users initialized...");

		SimpleGrantedAuthority[] userRoles = {new SimpleGrantedAuthority("ROLE_USER")};
		usersRepo.save(new User("user", "user1", Arrays.asList(userRoles)));
	
		SimpleGrantedAuthority[] adminRoles = {new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN")};
		usersRepo.save(new User("root", "root1", Arrays.asList(adminRoles)));
	}	
    
    
    public ArrayList<User> findAll() {
    	ArrayList<User> users = new ArrayList<>();
    	
    	for (User u : usersRepo.findAll()) {
    		users.add(u);
    	}
    	return users;
    }
}
