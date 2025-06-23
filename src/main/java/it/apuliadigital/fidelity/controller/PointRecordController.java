package it.apuliadigital.fidelity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.apuliadigital.fidelity.model.PointRecord;
import it.apuliadigital.fidelity.sevice.Interface.IPointRecord;
@Validated
@RestController
@RequestMapping("/point-records")
public class PointRecordController {

    @Autowired
    private IPointRecord pointRecordService;

    // aggiunge un nuovo record di punti
    @PostMapping
    public ResponseEntity<PointRecord> createPointRecord(@RequestBody PointRecord pointRecord) {
        PointRecord createdRecord = pointRecordService.addRecord(pointRecord);
        return new ResponseEntity<>(createdRecord, HttpStatus.CREATED);
    }

    // restituisce tutti i record di punti
    @GetMapping
    public ResponseEntity<List<PointRecord>> getAllPointRecords() {
        List<PointRecord> records = pointRecordService.getAllRecords();
        if (records.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(records);
    }

    // restituisce i record di punti per numero di carta
    @GetMapping("/by-card/{cardId}")
    public ResponseEntity<List<PointRecord>> getRecordsByCardId(@PathVariable Long cardId) {
        List<PointRecord> records = pointRecordService.getRecordsByCard(cardId);
        if (records.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(records);
    }

    // restituisce i record di punti per codice ordine
    @GetMapping("/by-order/{orderCode}")
    public ResponseEntity<List<PointRecord>> getRecordsByOrderCode(@PathVariable Long orderCode) {
        List<PointRecord> records = pointRecordService.getRecordsByOrderCode(orderCode);
        if (records.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(records);
    }
}
