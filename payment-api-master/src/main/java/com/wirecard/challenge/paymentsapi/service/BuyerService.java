package com.wirecard.challenge.paymentsapi.service;

import com.wirecard.challenge.paymentsapi.dto.BuyerDTO;
import com.wirecard.challenge.paymentsapi.model.Buyer;
import com.wirecard.challenge.paymentsapi.repository.BuyerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
public class BuyerService {

    @Autowired
    private BuyerRepository br;

    public ResponseEntity<?> findByBuyerAll() {
        try {
            Iterable<Buyer> listaBuyers = br.findAll();
            return new ResponseEntity<>(listaBuyers, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> saveBuyer(BuyerDTO dto) {
        try {
           Buyer buyer = Buyer.builder()
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .nome(dto.getNome())
                .build();
            Buyer buyerSave = br.save(buyer);
            return new ResponseEntity<>(buyerSave, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> findById(Long id) throws Exception {

        Optional<Buyer> optionalBuyer = Optional.ofNullable(
            br.findById(id)).orElseThrow(()-> new Exception());

        if(optionalBuyer.isPresent()) {
            return new ResponseEntity<>(optionalBuyer, HttpStatus.OK);
        }

        return new ResponseEntity<>("Buyer not found!", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> deleteBuyer(Long id) {
        try {
            br.deleteById(id);
            return new ResponseEntity<>("Buyer delete success!", HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return new ResponseEntity<>("Buyer not found!", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> alterBuyer(BuyerDTO dto) {
       try {
           Buyer buyer = Buyer.builder()
               .id(dto.getId())
               .cpf(dto.getCpf())
               .email(dto.getEmail())
               .nome(dto.getNome())
               .build();
           Buyer buyerSave = br.save(buyer);
           return new ResponseEntity<>(buyerSave, HttpStatus.OK);
       } catch (Exception e) {
           log.error(e.getLocalizedMessage());
           return new ResponseEntity<>("Error Buyer created!", HttpStatus.BAD_REQUEST);
       }

    }
}
