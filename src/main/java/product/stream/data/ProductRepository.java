package product.stream.data;

import org.springframework.data.repository.CrudRepository;

import product.stream.model.Product;

public interface ProductRepository extends CrudRepository<Product, String> {

}
