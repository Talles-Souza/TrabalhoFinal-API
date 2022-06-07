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

import com.residencia.commerce.dto.ItemPedidoDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface ResponseApiItemPeidido {

	@GetMapping
	@Operation(summary = "Listar todos os itens dos pedidos", description = "Listagem de itens dos pedidos")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<List<ItemPedidoDTO>> findAllItemPedido();

	@GetMapping("/{id}")
	@Operation(summary = "Retornar um item do pedido", description = "Item do pedido")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Buscado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<ItemPedidoDTO> findItemPedidoById(@PathVariable Integer id);

	@PostMapping
	@Operation(summary = "Inserir os dados de item do pedido", description = "Item do pedido adicinado")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Salvo com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<ItemPedidoDTO> saveItemPedido(@RequestBody @Valid ItemPedidoDTO itemPedidoDTO);

	@PutMapping
	@Operation(summary = "Atualizar os dados de item do pedido", description = "Item do pedido atualizado")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<ItemPedidoDTO> updateItemPedido(@RequestBody @Valid ItemPedidoDTO itemPedidoDTO);

	@DeleteMapping("/{id}")
	@Operation(summary = "Remover um item do pedido", description = "Item do pedido removido")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<String> deleteItemPedido(@PathVariable Integer id);
}
