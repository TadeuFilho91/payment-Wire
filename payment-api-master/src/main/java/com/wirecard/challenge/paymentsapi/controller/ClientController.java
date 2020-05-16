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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wirecard.challenge.paymentsapi.dto.ClientDto;
import com.wirecard.challenge.paymentsapi.model.Client;
import com.wirecard.challenge.paymentsapi.repository.ClientRepository;
import com.wirecard.challenge.paymentsapi.service.ClientService;


/** Classe responsável por controlar as operações e requisições referentes ao Cliente
 *
 *
 */

@RestController
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientService clientService;

	
	@GetMapping(produces="application/json")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Iterable<Client> listaClients() {
		Iterable<Client> listaClients = clientRepository.findAll();
		return listaClients;
	}

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Client cadastrarClient(@RequestBody @Valid Client client) {
		return clientRepository.save(client);
	}


	@DeleteMapping("/client/{id}")
	public ResponseEntity<?> deletarClient(@PathVariable("id") Long id) {
		return clientService.deleteClient(id);
	}
	
	@PutMapping("/client/{id}")
	public ResponseEntity<?> alterClient(@RequestBody ClientDto clientDto) {
		return clientService.alterClient(clientDto);
	}
	
}
