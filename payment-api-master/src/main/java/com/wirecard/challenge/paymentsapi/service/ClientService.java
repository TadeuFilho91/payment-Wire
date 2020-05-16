package com.wirecard.challenge.paymentsapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wirecard.challenge.paymentsapi.dto.ClientDto;
import com.wirecard.challenge.paymentsapi.model.Client;
import com.wirecard.challenge.paymentsapi.repository.ClientRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	public ResponseEntity<?> saveClient(ClientDto clientDto) {

		try {
			Client client = Client.builder()
					.name(clientDto.getName())
					.build();
			Client clientSave = clientRepository.save(client);
			return new ResponseEntity<>(clientSave, HttpStatus.CREATED);

		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> findByBuyerAll() {
		try {
			Iterable<Client> listaClients = clientRepository.findAll();
			return new ResponseEntity<>(listaClients, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<?> findById(Long id) throws Exception {

		Optional<Client> optionalBuyer = Optional.ofNullable(clientRepository.findById(id))
				.orElseThrow(() -> new Exception());

		if (optionalBuyer.isPresent()) {
			return new ResponseEntity<>(optionalBuyer, HttpStatus.OK);
		}

		return new ResponseEntity<>("Buyer not found!", HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<?> alterClient(ClientDto clientDto) {
		try {
			Client client = Client.builder()
					.id(clientDto.getId())
					.name(clientDto.getName()).build();
			Client clientSave = clientRepository.save(client);
			return new ResponseEntity<>(clientSave, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>("Error client created!", HttpStatus.BAD_REQUEST);
		}

	}

	public ResponseEntity<?> deleteClient(Long id) {
		try {
			clientRepository.deleteById(id);
			return new ResponseEntity<>("Client deleted sucess", HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return new ResponseEntity<>("Client not found!", HttpStatus.NOT_FOUND);
		}
	}
}
