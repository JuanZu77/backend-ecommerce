package com.juanzubiri.ecommerce.backend.infrastructure.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.juanzubiri.ecommerce.backend.domain.model.User;
import com.juanzubiri.ecommerce.backend.infrastructure.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	@Mappings(
			{
				@Mapping(source = "id", target = "id"),
				@Mapping(source = "username", target = "username"),
				@Mapping(source = "firstName", target = "firstName"),
				@Mapping(source = "lastName", target = "lastName"),
				@Mapping(source = "email", target = "email"),
				@Mapping(source = "address", target = "address"),
				@Mapping(source = "cellphone", target = "cellphone"),
				@Mapping(source = "password", target = "password"),
				@Mapping(source = "userType", target = "userType"),
				@Mapping(source = "dateCreated", target = "dateCreated"),
				@Mapping(source = "dateUpdated", target = "dateUpdated"),
				
			}
			)
	
		User toUser(UserEntity userEntity); //entity a domain
	    Iterable<User> toUsers(Iterable<UserEntity>userEntities);
	    
	    @InheritInverseConfiguration
	    UserEntity toUserEntity(User user); //domain a entity

}
