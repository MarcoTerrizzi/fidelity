package it.apuliadigital.fidelity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.apuliadigital.fidelity.model.PointRecord;

@Repository
public interface PointRecordRepository extends JpaRepository<PointRecord, Long> {

    List<PointRecord> findByNumCard(Long numCard);

    List<PointRecord> findByOrderCode(Long orderCode);
}
