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

import com.residencia.commerce.entity.Categoria;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface ResponseApiCategoria {

	@GetMapping
	 @Operation(summary="Listar todos as categorias", description  = "Listagem de categorias")
		@ApiResponses(value = { 
				  @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
				  @ApiResponse(responseCode = "400", description = "ID Inválido"), 
				  @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"), 
				  @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
   public ResponseEntity<List<Categoria>> findAllCategoria() ;
	
	@GetMapping("/{id}")
    @Operation(summary="Retornar uma categoria", description  = "Categoria")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Buscado com sucesso"),
			  @ApiResponse(responseCode = "400", description = "ID Inválido"), 
			  @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"), 
			  @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
    public ResponseEntity<Categoria> findCategoriaById(@PathVariable Integer id) ;
	
	
	@PostMapping
    @Operation(summary="Inserir os dados de categoria", description  = "Categoria adicionada")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Salvo com sucesso"),
			  @ApiResponse(responseCode = "400", description = "ID Inválido"), 
			  @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"), 
			  @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
    public ResponseEntity<Categoria> saveCategoria(@RequestBody @Valid Categoria categoria);
	
	
	@PutMapping
    @Operation(summary="Atualizar os dados de categoria", description  = "Categoria atualizada")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
			  @ApiResponse(responseCode = "400", description = "ID Inválido"), 
			  @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"), 
			  @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
    public ResponseEntity<Categoria> updateCategoria(@RequestBody @Valid Categoria categoria);
	
	@DeleteMapping("/{id}")
    @Operation(summary="Remover uma categoria", description  = "Categoria removida")
   	@ApiResponses(value = { 
   			  @ApiResponse(responseCode = "200", description = "Deletado com sucesso"),
   			  @ApiResponse(responseCode = "400", description = "ID Inválido"), 
   			  @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar recurso"), 
   			  @ApiResponse(responseCode = "404", description = "Recurso não encontrado")})
    public ResponseEntity<String> deleteCategoriaById(@PathVariable Integer id);
}
