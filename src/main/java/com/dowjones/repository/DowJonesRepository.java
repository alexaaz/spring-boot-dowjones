package com.dowjones.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dowjones.domain.DowJonesIndex;


public interface DowJonesRepository extends CrudRepository<DowJonesIndex, Long> {

    List<DowJonesIndex> findByStock(String stock);

}