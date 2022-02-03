package com.dowjones;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IterableUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import com.dowjones.domain.DowJonesIndex;
import com.dowjones.repository.DowJonesRepository;
import com.dowjones.service.DowJonesService;
import com.dowjones.utils.DowJonesConstants;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DowJonesServiceMockTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(StartDowJonesApplication.class);

	@Mock
	private DowJonesRepository dowJonesRepository;

	@InjectMocks
	private DowJonesService dowJonesService = new DowJonesService();

	List<DowJonesIndex> dowJonesData = Arrays.asList(
			new DowJonesIndex(1, "AA", LocalDate.parse("01/07/2011", DowJonesConstants.dateFormatter),
					new BigDecimal(15.82), new BigDecimal(16.72), new BigDecimal(15.78), new BigDecimal(16.42),
					239655616l, new BigDecimal(3.79267), new BigDecimal(0), new BigDecimal(0), new BigDecimal(16.71),
					new BigDecimal(15.97), new BigDecimal(-4.42849), new BigDecimal(26), new BigDecimal(0.182704)),
			new DowJonesIndex(1, "AA", LocalDate.parse("01/14/2011", DowJonesConstants.dateFormatter),
					new BigDecimal(16.71), new BigDecimal(16.71), new BigDecimal(15.64), new BigDecimal(15.97),
					242963398l, new BigDecimal(-4.42849), new BigDecimal(1.380223028), new BigDecimal(239655616),
					new BigDecimal(16.19), new BigDecimal(15.79), new BigDecimal(-2.47066), new BigDecimal(19),
					new BigDecimal(0.187852)),
			new DowJonesIndex(1, "AB", LocalDate.parse("01/14/2011", DowJonesConstants.dateFormatter),
					new BigDecimal(16.71), new BigDecimal(16.71), new BigDecimal(15.64), new BigDecimal(15.97),
					242963398l, new BigDecimal(-4.42849), new BigDecimal(1.380223028), new BigDecimal(239655616),
					new BigDecimal(16.19), new BigDecimal(15.79), new BigDecimal(-2.47066), new BigDecimal(19),
					new BigDecimal(0.187852)));

	DowJonesIndex newElement = new DowJonesIndex(1, "ABA",
			LocalDate.parse("01/14/2011", DowJonesConstants.dateFormatter), new BigDecimal(19.71),
			new BigDecimal(19.71), new BigDecimal(15.64), new BigDecimal(15.97), 242963398l, new BigDecimal(-4.42849),
			new BigDecimal(1.380223028), new BigDecimal(239655616), new BigDecimal(16.19), new BigDecimal(15.79),
			new BigDecimal(-2.47066), new BigDecimal(19), new BigDecimal(0.187852));

	List<DowJonesIndex> uploadedDowJonesData = Arrays.asList(
			new DowJonesIndex(1, "BB", LocalDate.parse("01/08/2011", DowJonesConstants.dateFormatter),
					new BigDecimal(1.82), new BigDecimal(16.72), new BigDecimal(15.78), new BigDecimal(16.42),
					239655616l, new BigDecimal(3.79267), new BigDecimal(0), new BigDecimal(0), new BigDecimal(16.71),
					new BigDecimal(15.97), new BigDecimal(-4.42849), new BigDecimal(26), new BigDecimal(0.182704)),
			new DowJonesIndex(1, "BB", LocalDate.parse("01/08/2011", DowJonesConstants.dateFormatter),
					new BigDecimal(2.71), new BigDecimal(16.71), new BigDecimal(15.64), new BigDecimal(15.97),
					242963398l, new BigDecimal(-4.42849), new BigDecimal(1.380223028), new BigDecimal(239655616),
					new BigDecimal(16.19), new BigDecimal(15.79), new BigDecimal(-2.47066), new BigDecimal(19),
					new BigDecimal(0.187852)));

	@Mock
	MultipartFile file;

	@BeforeEach
	void init() throws InterruptedException, ExecutionException {
		when(dowJonesRepository.findAll()).thenReturn(dowJonesData);
		when(dowJonesRepository.save(newElement)).thenReturn(newElement);
		when(dowJonesRepository.findByStock("AA")).thenReturn(dowJonesData.subList(0, 1));
		when(dowJonesRepository.saveAll(uploadedDowJonesData)).thenReturn(IterableUtils.toList(uploadedDowJonesData));
	}

	@Test
	void testGetAllJonesData() {
		try {
			LOGGER.info("dowJonesService.getAllDowJonesData().get(){} "
					+ dowJonesService.getAllDowJonesData().get().toString());
			assertEquals(CollectionUtils.isNotEmpty(dowJonesService.getAllDowJonesData().get()), true);
			assertEquals(dowJonesService.getAllDowJonesData().get().size(), 3);
		} catch (InterruptedException e) {
			LOGGER.error("dowJonesService.getAllDowJonesData() {} " + e.toString());
		} catch (ExecutionException e) {
			LOGGER.error("dowJonesService.getAllDowJonesData() {} " + e.toString());
		}

	}

	@Test
	void testFindByStock() {
		try {
			assertEquals(dowJonesService.findByStock("AA").get(), dowJonesData.subList(0, 1));
		} catch (InterruptedException e) {
			LOGGER.error("testFindByStock() {} " + e.toString());
		} catch (ExecutionException e) {
			LOGGER.error("testFindByStock() {} " + e.toString());
			e.printStackTrace();
		}
	}

	@Test
	void testAdd() {
		try {
			assertEquals(dowJonesService.add(newElement).get(), newElement);
		} catch (InterruptedException e) {
			LOGGER.error("testAdd() {} " + e.toString());
		} catch (ExecutionException e) {
			LOGGER.error("testAdd() {} " + e.toString());
		}
	}

	@Test
	void testUpload() {
		try {
			assertEquals(dowJonesService.save(file), uploadedDowJonesData);
		} catch (Exception e) {
			LOGGER.error("testUpload(): dowJonesService.save() {} " + e.toString());
		}
	}
}
