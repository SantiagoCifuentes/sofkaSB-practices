package com.sofkaSBpractices.persistence;

import com.sofkaSBpractices.domain.Product;
import com.sofkaSBpractices.domain.repository.ProductRepository;
import com.sofkaSBpractices.persistence.crud.ProductoCrudRepository;
import com.sofkaSBpractices.persistence.entity.Producto;
import com.sofkaSBpractices.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {

    //aquí estarían todos los productos de la bd
    private ProductoCrudRepository productoCrudRepository;
    private ProductMapper mapper;

//    public List<Producto> getAll(){
//        return  (List<Producto>) productoCrudRepository.findAll();
//    }

//    public List<Producto>getByCategoria(int idCategoria){
//        return productoCrudRepository.findByIdCategoriaOrderByNombre(idCategoria);
//    }


    Optional<List<Producto>> getEscasos(int cantidad ){
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
    }

    public List<Producto>getPrecioVenta(double precioVenta){
        return productoCrudRepository.findByPrecioVenta(precioVenta);
    }

    public Optional<Producto>getProducto(int idProducto){
        return productoCrudRepository.findById(idProducto);
    }

    public  Producto save(Producto producto){
        return  productoCrudRepository.save(producto);
    }

    @Override
    public List<Product> getall() {
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombre(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(productos1 -> mapper.toProducts(productos1));// recibe los productos que tiene adentro
                                                                            //y los convierte a products y los retorna
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public List<Product> getSellingPrice(double precioVenta) {
        return null;
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    public void delete(int idProducto){
        productoCrudRepository.deleteById(idProducto);
    }
}
