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

import com.residencia.commerce.dto.ClienteDTO;
import com.residencia.commerce.exception.NoSuchElementFoundException;
import com.residencia.commerce.interfaces.ResponseApiCliente;
import com.residencia.commerce.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/cliente")
@Tag(name = "Cliente")
public class ClienteController implements ResponseApiCliente {

	@Autowired
	private ClienteService clienteService;

	public ResponseEntity<List<ClienteDTO>> findAllCliente() {
		List<ClienteDTO> clienteList = clienteService.findAllCliente();
		return new ResponseEntity<>(clienteList, HttpStatus.OK);
	}

	public ResponseEntity<ClienteDTO> findClienteById(@PathVariable Integer id) {
		ClienteDTO clienteDTO = clienteService.findClienteById(id);
		if (null == clienteDTO)
			throw new NoSuchElementFoundException("Não foi encontrado cliente com id: " + id + " pois não existe.");
		else
			return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
	}

	public ResponseEntity<ClienteDTO> saveCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
		ClienteDTO novoCliente = clienteService.saveCliente(clienteDTO);
		return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
	}

	public ResponseEntity<ClienteDTO> updateCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
		ClienteDTO clienteAtualizado = clienteService.findClienteById(clienteDTO.getIdCliente());
		if (null == clienteAtualizado)
			throw new NoSuchElementFoundException("Não foi possivel atualizar cliente com este id");
		else
			return new ResponseEntity<>(clienteService.updateCliente(clienteDTO), HttpStatus.OK);
	}

	public ResponseEntity<String> deleteCliente(@PathVariable Integer id) {
		ClienteDTO clienteAtualizado = clienteService.findClienteById(id);
		if (null == clienteAtualizado)
			throw new NoSuchElementFoundException(
					"Não foi possivel deletar cliente com id: " + id + " pois não existe.");
		else
			clienteService.deleteClienteById(id);
		return new ResponseEntity<>("Cliente deletado com sucesso!", HttpStatus.OK);
	}
}
