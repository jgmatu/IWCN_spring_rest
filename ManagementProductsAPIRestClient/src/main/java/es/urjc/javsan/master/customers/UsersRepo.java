package es.urjc.javsan.master.customers;

import org.springframework.data.repository.CrudRepository;
import es.urjc.javsan.master.entities.User;


public interface UsersRepo extends CrudRepository<User, Long> {
    User findByName(String name);
}
