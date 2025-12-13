package com.juanzubiri.ecommerce.backend.infrastructure.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.juanzubiri.ecommerce.backend.domain.model.Category;
import com.juanzubiri.ecommerce.backend.infrastructure.entity.CategoryEntity;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
	
     @Mappings(
    		 
    		 {
    			 @Mapping(source = "id", target = "id"),
    			 @Mapping(source = "name", target = "name"),
    			 @Mapping(source = "dateCreated", target = "dateCreated"),
    			 @Mapping(source = "dateUpdated", target = "dateUpdated")
    		 }
    	)
     
        Category toCategory(CategoryEntity categoryEntity); // entity a domain
        Iterable<Category> toCategoryList(Iterable<CategoryEntity>categoryEntities);
        
        @InheritInverseConfiguration
        CategoryEntity toCategoryEntity (Category category); // domain a entity
}


