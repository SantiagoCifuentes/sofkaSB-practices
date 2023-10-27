package com.sofkaSBpractices.domain.service;

import com.sofkaSBpractices.domain.Product;
import com.sofkaSBpractices.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll(){
        return productRepository.getall();
    }

    public Optional<Product>getProduct(int productId){
        return productRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory (int categoryId){
        return productRepository.getByCategory(categoryId);
    }

    public Product save (Product product){
        return productRepository.save(product);
    }

    //se busca el producto con el método anteriormente utilizado
    //busca el producto y si no lo encuentra retorna un false
//    public boolean delete(int productId){
//        return getProduct(productId).map(product -> {
//            productRepository.delete(productId);
//            return true;
//        }).orElse(false);
//    }

    //si existe lo borra
    public boolean delete(int productId) {
        if (getProduct(productId).isPresent()) {
            productRepository.delete(productId);
            return true;
        } else {
                return false;
        }
    }
}
