package com.dowjones;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.dowjones.controller.DowJonesController;
import com.dowjones.domain.DowJonesIndex;
import com.dowjones.repository.DowJonesRepository;
import com.dowjones.service.DowJonesService;
import com.dowjones.utils.DowJonesConstants;

@AutoConfigureMockMvc
@ContextConfiguration(classes = { DowJonesController.class, DowJonesService.class })
@WebMvcTest
class DowJonesIntegrationTest {

	@MockBean
	private DowJonesRepository dowJonesRepository;

//	@InjectMocks
	@MockBean
	private DowJonesService dowJonesService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetAllDowJonesData() throws Exception {

		DowJonesIndex dj1 = new DowJonesIndex(1, "AB", LocalDate.parse("01/14/2011", DowJonesConstants.dateFormatter),
				new BigDecimal(16.71), new BigDecimal(16.71), new BigDecimal(15.64), new BigDecimal(15.97), 242963398l,
				new BigDecimal(-4.42849), new BigDecimal(1.380223028), new BigDecimal(239655616), new BigDecimal(16.19),
				new BigDecimal(15.79), new BigDecimal(-2.47066), new BigDecimal(19), new BigDecimal(0.187852));
		DowJonesIndex dj2 = new DowJonesIndex(1, "AXP", LocalDate.parse("01/15/2011", DowJonesConstants.dateFormatter),
				new BigDecimal(19.99), new BigDecimal(16.71), new BigDecimal(15.64), new BigDecimal(15.97), 242963398l,
				new BigDecimal(-4.42849), new BigDecimal(1.380223028), new BigDecimal(239655616), new BigDecimal(16.19),
				new BigDecimal(15.79), new BigDecimal(-2.47066), new BigDecimal(19), new BigDecimal(0.187852));
		doReturn(CompletableFuture.completedFuture(Arrays.asList(dj1, dj2))).when(dowJonesService).getAllDowJonesData();
		doReturn(Arrays.asList(dj1, dj2)).when(dowJonesRepository).findAll();

		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/dowjones"))
				// Validate the response code and content type
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				// Validate the returned fields
				.andExpect(jsonPath("$[0].stock", is("AB"))).andExpect(jsonPath("$[1].stock", is("AXP")));
//			.andExpect(jsonPath("$[0].open").value(IsCloseTo.closeTo(17.00, 0.20)));			
//			.andExpect(jsonPath("$[1].open").value(IsCloseTo.closeTo(20.00, 0.03)));
	}
}
