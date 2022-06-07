package com.residencia.commerce.interfaces;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.residencia.commerce.dto.CepDTO;
import com.residencia.commerce.dto.EnderecoDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface ResponseApiEndereco {
	@GetMapping
	@Operation(summary = "Listar todos os endereços", description = "Listagem de endereços")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<List<EnderecoDTO>> findAllEndereco();

	@GetMapping("/{id}")
	@Operation(summary = "Retornar um endereço", description = "Endereço")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Buscado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<EnderecoDTO> findEnderecoById(@PathVariable Integer id);

	@PostMapping
	@Operation(summary = "Inserir os dados de endereço", description = "Endereço adicionado")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Salvo com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<EnderecoDTO> saveEndereco(@RequestBody @Valid EnderecoDTO enderecoDTO);

	@PutMapping
	@Operation(summary = "Atualizar os dados de endereço", description = "Endereço atualizado")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<EnderecoDTO> updateEndereco(@RequestBody @Valid EnderecoDTO enderecoDTO);

	@DeleteMapping("/{id}")
	@Operation(summary = "Remover um endereço", description = "Endereço removido")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<String> deleteEndereco(@PathVariable Integer id);

	@Operation(summary = "Resgata o endereço pelo seu CEP", description = "Informe o CEP do endereço para obter as informações sobre ele", responses = {
			@ApiResponse(responseCode = "200", description = "Endereço encontrado com sucesso"),
			@ApiResponse(responseCode = "400", description = "Id invalido"),
			@ApiResponse(responseCode = "404", description = "Não existe endereço com esse CEP"),
			@ApiResponse(responseCode = "403", description = "Recurso não encontrado")})
	@GetMapping("/cep/{cep}")
	public ResponseEntity<CepDTO> consultarCep(@PathVariable String cep);
	
	@Operation(summary = "Salve o endereço pelo seu CEP", description = "Informe o CEP do endereço para salvar as informações sobre ele", responses = {
			@ApiResponse(responseCode = "200", description = "Endereço Salvo com sucesso"),
			@ApiResponse(responseCode = "400", description = "Id invalido"),
			@ApiResponse(responseCode = "404", description = "Não existe endereço com esse CEP"),
			@ApiResponse(responseCode = "403", description = "Recurso não encontrado")})
	@PostMapping("/cep/{cep}")
	public ResponseEntity<EnderecoDTO> SalvarCep(@PathVariable String cep, EnderecoDTO enderecoDTO);
}
