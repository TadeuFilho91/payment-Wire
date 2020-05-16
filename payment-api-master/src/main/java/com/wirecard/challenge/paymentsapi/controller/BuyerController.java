package com.wirecard.challenge.paymentsapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wirecard.challenge.paymentsapi.dto.BuyerDTO;
import com.wirecard.challenge.paymentsapi.repository.BuyerRepository;
import com.wirecard.challenge.paymentsapi.service.BuyerService;

/**
 *
 *Classe responsável por controlar operações e requisições referentes ao comprador
 *
 */

@RestController
public class BuyerController {
	
	@Autowired
	private BuyerRepository br;

	@Autowired
	BuyerService buyerService;
	
	@GetMapping(path="/buyer", produces="application/json")
	public ResponseEntity<?> listaBuyers() {
		return buyerService.findByBuyerAll();
	}

	@GetMapping("/buyer/{id}")
	public ResponseEntity getBuyer(@PathVariable("id") Long id) throws Exception {
		return buyerService.findById(id);
	}

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> cadastrarBuyer(@RequestBody @Valid BuyerDTO dto) {
		return buyerService.saveBuyer(dto);
	}

	
	@DeleteMapping("/buyer/{id}")
	public ResponseEntity<?> deletarBuyer(@PathVariable("id") Long id) {
		return buyerService.deleteBuyer(id);
	}

	@PutMapping("/buyer/{id}")
	public ResponseEntity<?> alterBuyer(@RequestBody BuyerDTO dto) {
		return buyerService.alterBuyer(dto);
	}
}
