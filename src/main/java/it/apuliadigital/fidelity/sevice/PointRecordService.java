package it.apuliadigital.fidelity.sevice;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.apuliadigital.fidelity.model.PointRecord;

@Service
public class PointRecordService {
    public ResponseEntity<Integer> removeSaldoPunti(int saldoSoldi, int remove) {
        if (saldoSoldi < remove) {
            return ResponseEntity.badRequest().build();
        }
        saldoSoldi -= remove;
        return ResponseEntity.ok().body(saldoSoldi);
    }

    public ResponseEntity<PointRecord> addRecord(int codiceOrdine, int money, int numTessera, int valPoint) {

        PointRecord record = new PointRecord(LocalDateTime.now(), numTessera, codiceOrdine, money, valPoint);
        return ResponseEntity.ok().body(record);
    }

    
}
