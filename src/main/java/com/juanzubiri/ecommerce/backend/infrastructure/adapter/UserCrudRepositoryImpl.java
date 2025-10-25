package com.juanzubiri.ecommerce.backend.infrastructure.adapter;

import org.springframework.stereotype.Repository;
import com.juanzubiri.ecommerce.backend.domain.model.User;
import com.juanzubiri.ecommerce.backend.domain.port.IUserRepository;
import com.juanzubiri.ecommerce.backend.infrastructure.UserEntity;
import com.juanzubiri.ecommerce.backend.infrastructure.mapper.UserMapper;

@Repository
public class UserCrudRepositoryImpl implements IUserRepository {

    private final IUserCrudRepository iUserCrudRepository;

    private final UserMapper userMapper;

     public UserCrudRepositoryImpl(IUserCrudRepository iUserCrudRepository, UserMapper userMapper) {
        this.iUserCrudRepository = iUserCrudRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        UserEntity entity = userMapper.toUserEntity(user); 
        UserEntity savedEntity = iUserCrudRepository.save(entity); 
        return userMapper.toUser(savedEntity); 
    }

	@Override
	public User findByEmail(String email) {
		
		return iUserCrudRepository.findByEmail(email)
	            .map(userMapper::toUser) 
	            .orElse(null); 
	}

	@Override
	public User findById(Integer id) {

	    return iUserCrudRepository.findById(id)
	            .map(userMapper::toUser) 
	            .orElse(null); 
	    
	}
	

}
