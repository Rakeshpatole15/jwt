package com.jwt.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jwt.entity.User;

public interface UserRepo extends MongoRepository<User, Integer> {

	void save(int id);

}
