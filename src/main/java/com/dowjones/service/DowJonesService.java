package com.dowjones.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.collections4.IterableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dowjones.domain.DowJonesIndex;
import com.dowjones.repository.DowJonesRepository;
import com.dowjones.utils.FileUtils;

@Service
public class DowJonesService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DowJonesService.class);

	@Autowired
	private DowJonesRepository dowJonesRepository;

	@Async("threadPoolTaskExecutor")
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public CompletableFuture<List<DowJonesIndex>> save(final MultipartFile file) throws Exception {
		final long start = System.currentTimeMillis();

		List<DowJonesIndex> dowJonesData = FileUtils.parseCSVFile(file);

		LOGGER.info("Saving a list of dow jones elements, size={}", dowJonesData.size());

		dowJonesData = IterableUtils.toList(dowJonesRepository.saveAll(dowJonesData));

		LOGGER.info("Elapsed time: {}", (System.currentTimeMillis() - start));
		return CompletableFuture.completedFuture(dowJonesData);
	}

	@Async("threadPoolTaskExecutor")
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public CompletableFuture<List<DowJonesIndex>> getAllDowJonesData() {

		LOGGER.info("Request to get the dow jones data list");

		final List<DowJonesIndex> dowJonesData = IterableUtils.toList(dowJonesRepository.findAll());

		LOGGER.info("dow jones data list.sz={}", dowJonesData.size());
		return CompletableFuture.completedFuture(dowJonesData);
	}

	@Async("threadPoolTaskExecutor")
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public CompletableFuture<DowJonesIndex> add(DowJonesIndex element) {

		LOGGER.info("Request to add a new dow jones data element: {}", element);

		DowJonesIndex lastDowJonesElem = dowJonesRepository.save(element);

		return CompletableFuture.completedFuture(lastDowJonesElem);
	}

	@Async("threadPoolTaskExecutor")
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public CompletableFuture<List<DowJonesIndex>> findByStock(String stock) {

		LOGGER.debug("Request to find by stock: {}", stock);

		List<DowJonesIndex> elements = dowJonesRepository.findByStock(stock);
		LOGGER.info("Request to find by stock: {}, size={}", stock, elements.size());
		return CompletableFuture.completedFuture(elements);
	}
}