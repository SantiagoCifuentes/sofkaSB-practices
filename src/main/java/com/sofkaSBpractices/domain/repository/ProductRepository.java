package com.sofkaSBpractices.domain.repository;


import com.sofkaSBpractices.domain.Product;


import java.util.List;
import java.util.Optional;

//een esta clase solo se indica el nombre de los meths que queremos que cualquier repositorio que trabaje con productos
//tenga que implementar

public interface ProductRepository {
    List<Product> getall();
    Optional<List<Product>> getByCategory (int categoryId);
    Optional<List<Product>> getScarseProducts (int quantity);
    Optional<Product> getProduct (int productId);
    List<Product>getSellingPrice(double precioVenta);
    Product save(Product product);
    void delete (int productId);

}
