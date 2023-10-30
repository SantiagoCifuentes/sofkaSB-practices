package com.sofkaSBpractices.persistence;

import com.sofkaSBpractices.domain.Purchase;
import com.sofkaSBpractices.domain.repository.PurchaseRepository;
import com.sofkaSBpractices.persistence.crud.CompraCrudRepository;
import com.sofkaSBpractices.persistence.entity.Compra;
import com.sofkaSBpractices.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return  compraCrudRepository.findByIdCliente(clientId)
                .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        //compra trae los producots con el get, y con el set se le dice a qué producto pertenece
        //para ello se puso en la clase Compra el código  cascade = {CascadeType.ALL}
        // en la relación OneTomany de compra, además de la anotación @MapsId en la clase
        //ComprasProducto
        compra.getProductos().forEach(producto -> producto.setCompra(compra) );

        return mapper.toPurchase(compraCrudRepository.save(compra));
    }
}
