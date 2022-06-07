package com.residencia.commerce.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.commerce.dto.CepDTO;
import com.residencia.commerce.dto.EnderecoDTO;
import com.residencia.commerce.exception.NoSuchElementFoundException;
import com.residencia.commerce.interfaces.ResponseApiEndereco;
import com.residencia.commerce.service.EnderecoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/endereco")
@Tag(name = "Endereço")
public class EnderecoController implements ResponseApiEndereco {

	@Autowired
	EnderecoService enderecoService;

	public ResponseEntity<List<EnderecoDTO>> findAllEndereco() {
		List<EnderecoDTO> enderecoList = enderecoService.findAllEndereco();
		return new ResponseEntity<>(enderecoList, HttpStatus.OK);
	}

	public ResponseEntity<EnderecoDTO> findEnderecoById(@PathVariable Integer id) {
		EnderecoDTO enderecoDTO = enderecoService.findEnderecoById(id);
		if (null == enderecoDTO)
			throw new NoSuchElementFoundException("Não foi encontrado endereço com id: " + id + " pois não existe.");
		else
			return new ResponseEntity<>(enderecoDTO, HttpStatus.OK);
	}

	public ResponseEntity<EnderecoDTO> saveEndereco(@RequestBody @Valid EnderecoDTO enderecoDTO) {
		EnderecoDTO novoEndereco = enderecoService.saveEndereco(enderecoDTO);
		return new ResponseEntity<>(novoEndereco, HttpStatus.CREATED);
	}

	public ResponseEntity<EnderecoDTO> updateEndereco(@RequestBody @Valid EnderecoDTO enderecoDTO) {
		EnderecoDTO enderecoAtualizado = enderecoService.findEnderecoById(enderecoDTO.getIdEndereco());
		if (null == enderecoAtualizado)
			throw new NoSuchElementFoundException("Não foi possivel atualizar endereço com este id");
		else
			return new ResponseEntity<>(enderecoService.updateEndereco(enderecoDTO), HttpStatus.OK);
	}

	public ResponseEntity<String> deleteEndereco(@PathVariable Integer id) {
		EnderecoDTO enderecoAtualizado = enderecoService.findEnderecoById(id);
		if (null == enderecoAtualizado)
			throw new NoSuchElementFoundException(
					"Não foi possivel deletar endereço com id: " + id + " pois não existe.");
		else
			enderecoService.deleteEnderecoById(id);
		return new ResponseEntity<>("Endereço deletado com sucesso!", HttpStatus.OK);
	}
    public ResponseEntity<CepDTO> consultarCep(@PathVariable String cep) {
		CepDTO cepDTO = enderecoService.consultarCepDTO(cep);
		return new ResponseEntity<>(cepDTO, HttpStatus.OK);
	}
    
    
    //Testando o metado ainda, precisamos colocar para funcionar
    public ResponseEntity<EnderecoDTO> SalvarCep(@PathVariable String cep, EnderecoDTO enderecoDTO) {
    	//CepDTO cepDTO = enderecoService.consultarCepDTO(cep);
    	EnderecoDTO cepDTO = enderecoService.saveCep(cep,enderecoDTO);
    	
    	return new ResponseEntity<>(cepDTO, HttpStatus.OK);
    }

}
