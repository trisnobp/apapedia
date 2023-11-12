package com.apapedia.catalogue.DTO;

import org.mapstruct.Mapper;

import com.apapedia.catalogue.DTO.request.CreateCatalogueRequestDTO;
import com.apapedia.catalogue.model.Catalogue;

@Mapper(componentModel = "spring")
public interface CatalogueMapper {

    Catalogue createCatalogueRequestDTOToCatalogue(CreateCatalogueRequestDTO createCatalogueRequestDTO);

}
