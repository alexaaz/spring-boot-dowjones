package com.dowjones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dowjones.domain.DowJonesIndex;
import com.dowjones.service.DowJonesService;
import com.dowjones.utils.DowJonesConstants;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

@SpringBootTest 
public class DowJonesServiceTest {
	private final DowJonesIndex element = new DowJonesIndex(1, "EUR", LocalDate.parse("01/07/2021", DowJonesConstants.dateFormatter),
			new BigDecimal(15.82), new BigDecimal(16.72), new BigDecimal(15.78), new BigDecimal(16.42),
			239655616l, new BigDecimal(3.79267), new BigDecimal(0), new BigDecimal(0), new BigDecimal(16.71),
			new BigDecimal(15.97), new BigDecimal(-4.42849), new BigDecimal(26), new BigDecimal(0.182704));
    @Autowired
    private DowJonesService service;

    @Test
    public void testGetAllDowJonesData() throws Exception {
        assertThat(service.getAllDowJonesData().get().size()).isEqualTo(751);
    }

    @Test
    public void testFindByStock() throws Exception {
        assertThat(service.findByStock("AA").get().size()).isEqualTo(25);
        assertThat(service.findByStock("AXP").get().size()).isEqualTo(25);
        assertThat(service.findByStock("ZZZ").get().size()).isEqualTo(0);
    }
    
    @Test
    public void testAdd() throws Exception {
        assertThat(service.add(element).get()).isEqualTo(element);          
    }
    
}
