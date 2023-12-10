package com.apapedia.order.dto;

import com.apapedia.order.dto.request.UpdateCatalogueRequestDTO;
import com.apapedia.order.dto.response.ResponseCatalogueDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CatalogueMapper {
    UpdateCatalogueRequestDTO catalogueToUpdateCatalogueRequestDTO(ResponseCatalogueDTO response);
}
