package com.residencia.commerce.interfaces;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.residencia.commerce.dto.ProdutoDTO;
import com.residencia.commerce.entity.Produto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface ResponseApiProduto {

	@GetMapping
	@Operation(summary = "Listar todos os produtos", description = "Listagem de produtos")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<List<ProdutoDTO>> findAllProduto();

	@GetMapping("/{id}")
	@Operation(summary = "Retornar um produto", description = "Produto")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Buscado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<ProdutoDTO> findProdutoById(@PathVariable Integer id);

	@PostMapping
	@Operation(summary = "Inserir os dados de produto", description = "Produto adicionado")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Salvo com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<ProdutoDTO> saveProduto(@RequestBody @Valid ProdutoDTO produtoDTO);

	@PostMapping(value = "/com-foto", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	@Operation(summary = "Inserir os dados de produto e foto", description = "Produto adicionado com foto")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Salvo com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<ProdutoDTO> saveCategoriaComfoto(@RequestPart("produto") Produto produto,
			@RequestPart("file") MultipartFile file) throws Exception;

	@PutMapping
	@Operation(summary = "Atualizar os dados de produto", description = "Produto atualizado")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<ProdutoDTO> updateProduto(@RequestBody @Valid ProdutoDTO produtoDTO);

	@DeleteMapping("/{id}")
	@Operation(summary = "Remover um produto", description = "Produto removido")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
			@ApiResponse(responseCode = "400", description = "ID Inválido"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado") })
	public ResponseEntity<String> deleteProduto(@PathVariable Integer id);
}
