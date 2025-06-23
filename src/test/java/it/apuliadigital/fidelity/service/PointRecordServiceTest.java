package it.apuliadigital.fidelity.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import it.apuliadigital.fidelity.model.PointRecord;
import it.apuliadigital.fidelity.repository.PointRecordRepository;
import it.apuliadigital.fidelity.sevice.PointRecordServiceImpl;

public class PointRecordServiceTest {

    @Mock
    private PointRecordRepository pointRecordRepository;

    @InjectMocks
    private PointRecordServiceImpl pointRecordService;

    private PointRecord record1;
    private PointRecord record2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        record1 = new PointRecord();
        record1.setId(1L);
        record1.setNumCard(123L);
        record1.setOrderCode(456L);
        

        record2 = new PointRecord();
        record2.setId(2L);
        record2.setNumCard(123L);
        record2.setOrderCode(789L);
    }

    @Test
    public void testAddRecord() {
        when(pointRecordRepository.save(record1)).thenReturn(record1);

        PointRecord savedRecord = pointRecordService.addRecord(record1);

        assertNotNull(savedRecord);
        assertEquals(1L, savedRecord.getId());
        verify(pointRecordRepository, times(1)).save(record1);
    }

    @Test
    public void testGetRecordsByCard() {
        when(pointRecordRepository.findByNumCard(123L)).thenReturn(Arrays.asList(record1, record2));

        List<PointRecord> records = pointRecordService.getRecordsByCard(123L);

        assertEquals(2, records.size());
        assertTrue(records.contains(record1));
        assertTrue(records.contains(record2));
        verify(pointRecordRepository, times(1)).findByNumCard(123L);
    }

    @Test
    public void testGetRecordsByOrderCode() {
        when(pointRecordRepository.findByOrderCode(456L)).thenReturn(Collections.singletonList(record1));

        List<PointRecord> records = pointRecordService.getRecordsByOrderCode(456L);

        assertEquals(1, records.size());
        assertEquals(record1, records.get(0));
        verify(pointRecordRepository, times(1)).findByOrderCode(456L);
    }

    @Test
    public void testGetAllRecords() {
        when(pointRecordRepository.findAll()).thenReturn(Arrays.asList(record1, record2));

        List<PointRecord> records = pointRecordService.getAllRecords();

        assertEquals(2, records.size());
        verify(pointRecordRepository, times(1)).findAll();
    }
}
