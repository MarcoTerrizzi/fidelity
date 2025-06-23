package it.apuliadigital.fidelity.sevice.Interface;

import java.util.List;

import it.apuliadigital.fidelity.model.PointRecord;

public interface IPointRecord {

    PointRecord addRecord(PointRecord record);

    List<PointRecord> getRecordsByCard(Long numCard);

    List<PointRecord> getRecordsByOrderCode(Long orderCode);

    List<PointRecord> getAllRecords();
}
