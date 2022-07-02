package com.kara.webserver.repositories;

import com.kara.webserver.datatypes.ProductionLine;

import org.springframework.data.repository.CrudRepository;


public interface PrLineRepository extends CrudRepository<ProductionLine, Integer> {
}
