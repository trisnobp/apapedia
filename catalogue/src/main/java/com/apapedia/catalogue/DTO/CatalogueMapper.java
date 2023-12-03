package com.apapedia.catalogue.DTO;

import org.aspectj.lang.annotation.After;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;

import com.apapedia.catalogue.DTO.request.CreateCatalogueRequestDTO;
import com.apapedia.catalogue.DTO.response.ResponseCatalogueDTO;
import com.apapedia.catalogue.model.Catalogue;
import com.github.javafaker.Cat;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CatalogueMapper {

    ResponseCatalogueDTO catalogueTOResponseCatalogueDTO(Catalogue catalogue);
    @AfterMapping
    default void setDisplayName(Catalogue catalogue, @MappingTarget ResponseCatalogueDTO dto){
        dto.setDisplayCategory(catalogue.getCategory().getNamaCategory().toString());
    }


}
