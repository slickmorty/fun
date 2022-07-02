package com.kara.webserver.repositories;

import com.kara.webserver.datatypes.Station;

import org.springframework.data.repository.CrudRepository;

public interface StationRepository extends CrudRepository<Station, Integer> {
}
