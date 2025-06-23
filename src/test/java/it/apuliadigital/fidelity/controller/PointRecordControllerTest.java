package it.apuliadigital.fidelity.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.apuliadigital.fidelity.model.PointRecord;
import it.apuliadigital.fidelity.sevice.Interface.IPointRecord;

@WebMvcTest(PointRecordController.class)
public class PointRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IPointRecord pointRecordService;

    @Autowired
    private ObjectMapper objectMapper;

    private PointRecord sampleRecord;

    @BeforeEach
    void setup() {
        sampleRecord = new PointRecord();
        sampleRecord.setId(1L);
        sampleRecord.setNumCard(1001L);
        sampleRecord.setOrderCode(5001L);
        
    }

    @Test
    void testCreatePointRecord() throws Exception {
        when(pointRecordService.addRecord(any(PointRecord.class))).thenReturn(sampleRecord);

        mockMvc.perform(post("/point-records")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleRecord)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(sampleRecord.getId()))
                .andExpect(jsonPath("$.numCard").value(sampleRecord.getNumCard()))
                .andExpect(jsonPath("$.orderCode").value(sampleRecord.getOrderCode()));
    }

    @Test
    void testGetAllPointRecords_withData() throws Exception {
        List<PointRecord> records = Arrays.asList(sampleRecord);
        when(pointRecordService.getAllRecords()).thenReturn(records);

        mockMvc.perform(get("/point-records"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void testGetAllPointRecords_noData() throws Exception {
        when(pointRecordService.getAllRecords()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/point-records"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetRecordsByCardId_withData() throws Exception {
        when(pointRecordService.getRecordsByCard(1001L)).thenReturn(Arrays.asList(sampleRecord));

        mockMvc.perform(get("/point-records/by-card/1001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void testGetRecordsByCardId_noData() throws Exception {
        when(pointRecordService.getRecordsByCard(1002L)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/point-records/by-card/1002"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetRecordsByOrderCode_withData() throws Exception {
        when(pointRecordService.getRecordsByOrderCode(5001L)).thenReturn(Arrays.asList(sampleRecord));

        mockMvc.perform(get("/point-records/by-order/5001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void testGetRecordsByOrderCode_noData() throws Exception {
        when(pointRecordService.getRecordsByOrderCode(5002L)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/point-records/by-order/5002"))
                .andExpect(status().isNoContent());
    }
}
