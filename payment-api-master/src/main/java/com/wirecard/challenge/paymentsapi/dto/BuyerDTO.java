package com.wirecard.challenge.paymentsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Classe que representa a transferencia de dados do comprador
 *
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuyerDTO {

	private Long id;

	private String email;

	private String cpf;

	private String nome;
}
