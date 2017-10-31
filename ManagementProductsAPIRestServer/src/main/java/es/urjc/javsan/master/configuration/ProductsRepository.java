package es.urjc.javsan.master.configuration;

import org.springframework.data.repository.CrudRepository;

import es.urjc.javsan.master.entities.Product;

public interface ProductsRepository extends CrudRepository<Product, Integer> {

}
