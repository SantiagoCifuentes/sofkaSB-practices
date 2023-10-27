package com.sofkaSBpractices.persistence.mapper;

import com.sofkaSBpractices.domain.Category;
import com.sofkaSBpractices.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring" , uses = {Category.class}) //se usa uses ya uqe se está mapeando category y esta
                                                                    // ya tiene su mapping propio
public interface CategoryMapper {

    @Mappings({
            // se lee de la sgte manera : donde sea idCategoria lo lleve a categoryId
            @Mapping(source = "idCategoria", target = "categoryId") ,
            @Mapping(source = "descripcion", target = "category") ,
            @Mapping(source = "estado", target = "active")
    })
    Category toCategory(Categoria categoria);

    @InheritInverseConfiguration //esta anotación indica que la conversión es la inversa a la de arriba, entonces no
                                //se tiene que definir los mapping

    @Mapping(target = "productos", ignore = true)//dentro de category no se encuentra la lista de productos
    Categoria toCategoria(Category category);

}
