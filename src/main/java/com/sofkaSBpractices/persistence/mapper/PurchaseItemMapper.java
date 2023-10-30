package com.sofkaSBpractices.persistence.mapper;

import com.sofkaSBpractices.domain.PurchaseItem;
import com.sofkaSBpractices.persistence.entity.ComprasProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface PurchaseItemMapper {

    @Mappings({
            @Mapping(source = "id.idProducto", target="productId"),
            @Mapping(source = "cantidad", target="quantity"),
            //con total no se hace ya que se llama igual en la clase base y en la de dominio(maper)
            @Mapping(source = "estado", target="active"),
    })
    PurchaseItem toPurchaseItem(ComprasProducto producto);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "compra", ignore = true),
            @Mapping(target = "producto", ignore = true),
            @Mapping(target = "id.idCompra", ignore = true),
    })
    //esta conversión utiiliza de manera inversa los mappings definidos arriba
    ComprasProducto toComprasProducto(PurchaseItem item);




}
