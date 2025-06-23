package it.apuliadigital.fidelity.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.apuliadigital.fidelity.model.PointRecord;
import it.apuliadigital.fidelity.repository.PointRecordRepository;
import it.apuliadigital.fidelity.sevice.Interface.IPointRecord;

@Service
public class PointRecordServiceImpl implements IPointRecord {

    @Autowired
    private PointRecordRepository pointRecordRepository;

    @Override
    public PointRecord addRecord(PointRecord record) {
        return pointRecordRepository.save(record);
    }

    @Override
    public List<PointRecord> getRecordsByCard(Long numCard) {
        return pointRecordRepository.findByNumCard(numCard);
    }

    @Override
    public List<PointRecord> getRecordsByOrderCode(Long orderCode) {
        return pointRecordRepository.findByOrderCode(orderCode);
    }

    @Override
    public List<PointRecord> getAllRecords() {
        return pointRecordRepository.findAll();
    }
}
