package com.kara.webserver.repositories;

import com.kara.webserver.datatypes.Work;

import org.springframework.data.repository.CrudRepository;

public interface WorkRepository extends CrudRepository<Work, Integer> {
}
