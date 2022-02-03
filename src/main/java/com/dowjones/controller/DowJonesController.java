package com.dowjones.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.collections4.IterableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dowjones.domain.DowJonesIndex;
import com.dowjones.error.ElementNotFoundException;
import com.dowjones.repository.DowJonesRepository;
import com.dowjones.service.DowJonesService;

@RestController
public class DowJonesController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DowJonesController.class);

	@Autowired
	private DowJonesRepository repository;

	@Autowired
	private DowJonesService service;

	// FindAll
	@GetMapping("/dowjones")
	List<DowJonesIndex> findAll() {
		return IterableUtils.toList(repository.findAll());
	}

	// Save
	// returns 201 instead of 200
	@PostMapping("/dowjones/add")
	@ResponseStatus(HttpStatus.CREATED)
	DowJonesIndex newDowJonesIndex(@RequestBody DowJonesIndex newElem) {
		return repository.save(newElem);
	}

	// FindById
	@GetMapping("/dowjones/{id}")
	DowJonesIndex findOne(@PathVariable Long id) {
		return repository.findById(id).orElseThrow(() -> new ElementNotFoundException(id));
	}

	// FindByStock
	@GetMapping("/dowjones/stock/{stock}")
	List<DowJonesIndex> findByStock(@PathVariable String stock) {
		return repository.findByStock(stock);
	}

	// Save or update
	@PutMapping("/dowjones/{id}")
	DowJonesIndex saveOrUpdate(@RequestBody DowJonesIndex newElem, @PathVariable Long id) {

		return repository.findById(id).map(x -> {
			x.setHigh(newElem.getHigh());
			x.setLow(newElem.getLow());
			x.setClose(newElem.getClose());
			// TODO: add the other updatable fields 
			return repository.save(x);
		}).orElseGet(() -> {
			newElem.setId(id);
			return repository.save(newElem);
		});
	}

	@DeleteMapping("/dowjones/{id}")
	void deleteDowJonesIndex(@PathVariable Long id) {
		repository.deleteById(id);
	}

	@PostMapping("/dowjones/upload")
	// public ResponseEntity<?> uploadFile(
	public List<DowJonesIndex> uploadFile(@RequestParam("file") MultipartFile uploadFile) {
		try {
			CompletableFuture<List<DowJonesIndex>> result = service.save(uploadFile);
			return result.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
