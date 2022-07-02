package com.kara.webserver.repositories;

import com.kara.webserver.datatypes.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
