package com.dowjones;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.dowjones.domain.DowJonesIndex;
import com.dowjones.repository.DowJonesRepository;
import com.dowjones.service.DowJonesService;
import com.dowjones.utils.DowJonesConstants;
import com.dowjones.utils.FileUtils;

@SpringBootApplication
public class StartDowJonesApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(StartDowJonesApplication.class);

	@Autowired
	DowJonesService dowJonesService;

	public static void main(String[] args) {
		SpringApplication.run(StartDowJonesApplication.class, args);
	}

	@Profile("demo")
	@Bean
	CommandLineRunner initDatabase(DowJonesRepository repository) {
		return args -> {
			long start = System.currentTimeMillis();

			// start asynchronous calls:
			CompletableFuture<DowJonesIndex> addedElem = dowJonesService.add(new DowJonesIndex(1, "AA",
					LocalDate.parse("01/07/2011", DowJonesConstants.dateFormatter), new BigDecimal(15.82),
					new BigDecimal(16.72), new BigDecimal(15.78), new BigDecimal(16.42), 239655616l,
					new BigDecimal(3.79267), new BigDecimal(0), new BigDecimal(0), new BigDecimal(16.71),
					new BigDecimal(15.97), new BigDecimal(-4.42849), new BigDecimal(26), new BigDecimal(0.182704)));

			LOGGER.info("\n addedElem{}", addedElem);

			LOGGER.info("\n save()");
			CompletableFuture<List<DowJonesIndex>> dowJonesData = dowJonesService
					.save(FileUtils.readFile("/dow_jones_index_frgm.data"));

			LOGGER.info("\n dowJonesData{}", dowJonesData);

			Thread.sleep(1000);

			LOGGER.info("\n ");
			CompletableFuture<List<DowJonesIndex>> dowJonesAA = dowJonesService.findByStock("AA");
			LOGGER.info("\n dowJonesAA{}", dowJonesAA);

			LOGGER.info("\n ");
			CompletableFuture<List<DowJonesIndex>> allDowJonesData = dowJonesService.getAllDowJonesData();

			LOGGER.info("\n allDowJonesData{}", allDowJonesData);

			// wait until all threads are all done
			CompletableFuture.allOf(addedElem, dowJonesData, dowJonesAA, allDowJonesData).join();

			LOGGER.info("\n finalized concurrently calling the methods");

			LOGGER.info("Elapsed time: " + (System.currentTimeMillis() - start));

//            LOGGER.info("addedElem --> " + addedElem.isDone());
//            LOGGER.info("dowJonesData(after file upload):--> " + dowJonesData.isDone());
//            LOGGER.info("findByStockAA--> " + dowJonesAA.isDone());
//            LOGGER.info("allDowJonesData--> " + allDowJonesData.isDone());
		};

	}

}