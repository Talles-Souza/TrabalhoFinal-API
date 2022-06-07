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

import com.residencia.commerce.dto.PedidoDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface ResponseApiPedido {

	@GetMapping
	@Operation(summary = "Listar todos os pedidos", description = "Listagem de pedidos")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<List<PedidoDTO>> findAllPedido();

	@GetMapping("/{id}")
	@Operation(summary = "Retornar um pedido", description = "Pedido")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Buscado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<PedidoDTO> findPedidoById(@PathVariable Integer id);

	@PostMapping
	@Operation(summary = "Inserir os dados de pedido", description = "Pedido adicionado")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Salvo com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<PedidoDTO> savePedido(@RequestBody @Valid PedidoDTO pedidoDTO);

	@PutMapping
	@Operation(summary = "Atualizar os dados de pedido", description = "Pedido atualizado")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<PedidoDTO> updatePedido(@RequestBody @Valid PedidoDTO pedidoDTO);

	@DeleteMapping("/{id}")
	@Operation(summary = "Remover um pedido", description = "Pedido removido")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<String> deletePedido(@PathVariable Integer id);
}
