package it.apuliadigital.fidelity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.apuliadigital.fidelity.model.PointRecord;

@Repository
public interface PointRecordReposity  extends CrudRepository<PointRecord, Integer> {

}