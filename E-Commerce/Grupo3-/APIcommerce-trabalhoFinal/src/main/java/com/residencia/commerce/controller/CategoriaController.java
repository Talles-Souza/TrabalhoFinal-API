package com.residencia.commerce.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.residencia.commerce.entity.Categoria;
import com.residencia.commerce.exception.NoSuchElementFoundException;
import com.residencia.commerce.interfaces.ResponseApiCategoria;
import com.residencia.commerce.service.CategoriaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/categoria")
@Tag(name = "Categoria")
public class CategoriaController implements ResponseApiCategoria {
	@Autowired
	CategoriaService categoriaService;

	public ResponseEntity<List<Categoria>> findAllCategoria() {
		List<Categoria> categoriaList = categoriaService.findAllCategoria();
		return new ResponseEntity<>(categoriaList, HttpStatus.OK);
	}

	public ResponseEntity<Categoria> findCategoriaById(@PathVariable Integer id) {
		Categoria categoria = categoriaService.findCategoriaById(id);
		if (null == categoria)
			throw new NoSuchElementFoundException("Não foi encontrado categoria com id: " + id + " pois não existe.");
		else
			return new ResponseEntity<>(categoria, HttpStatus.OK);
	}

	public ResponseEntity<Categoria> saveCategoria(@RequestBody @Valid Categoria categoria) {
		return new ResponseEntity<>(categoriaService.saveCategoria(categoria), HttpStatus.CREATED);
	}

	public ResponseEntity<Categoria> updateCategoria(@RequestBody @Valid Categoria categoria) {
		Categoria categoriaAtualizada = categoriaService.findCategoriaById(categoria.getIdCategoria());
		if (null == categoriaAtualizada)
			throw new NoSuchElementFoundException("Não foi possivel atualizar categoria com este id");
		else
			return new ResponseEntity<>(categoriaService.updateCategoria(categoria), HttpStatus.OK);
	}

	public ResponseEntity<String> deleteCategoriaById(@PathVariable Integer id) {
		Categoria categoria = categoriaService.findCategoriaById(id);
		if (null == categoria)
			throw new NoSuchElementFoundException(
					"Não foi possivel deletar categoria com id: " + id + " pois não existe.");
		else
			categoriaService.deleteCategoriaById(id);
		return new ResponseEntity<>("Categoria deletada com sucesso!", HttpStatus.OK);
	}

}
