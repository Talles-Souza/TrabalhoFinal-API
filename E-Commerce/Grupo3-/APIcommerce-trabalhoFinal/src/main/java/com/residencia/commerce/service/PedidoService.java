package com.residencia.commerce.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static java.util.stream.Collectors.toList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.commerce.dto.ItemPedidoDTO;
import com.residencia.commerce.dto.PedidoDTO;
import com.residencia.commerce.entity.Cliente;
import com.residencia.commerce.entity.ItemPedido;
import com.residencia.commerce.entity.Pedido;
import com.residencia.commerce.repository.ItemPedidoRepository;
import com.residencia.commerce.repository.PedidoRepository;

@Service
public class PedidoService {
	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	ClienteService clienteService;
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	@Autowired
	EnderecoService enderecoService;
	@Autowired
	EmailService emailService;

	@Autowired
	ItemPedidoService itemPedidoService;

	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public List<PedidoDTO> findAllPedido() {
		List<Pedido> listaPedidosEntity = pedidoRepository.findAll();
		List<PedidoDTO> listaPedidosDTO = new ArrayList<>();

		for (Pedido pedido : listaPedidosEntity) {
			listaPedidosDTO.add(converterEntityToDTO(pedido));
		}

		return listaPedidosDTO;
	}

	public String confirmarPedido(Integer id) {
		Pedido pedido = pedidoRepository.findById(id).get();
		ItemPedido itemPedido = itemPedidoRepository.findById(id).get();
		emailService.sendMail(pedido, itemPedido);
		return "Pedido finalizado com sucesso!";

	}

	public PedidoDTO savePedido(PedidoDTO pedidoDTO) {
		Date data = new Date();
		pedidoDTO.setDataPedido(data);

		for (ItemPedidoDTO item : pedidoDTO.getItemPedidoList()) {
			itemPedidoService.calculaPercentual(item);
		}
		Pedido pedido = pedidoRepository.save(ConverteDTOToEntidade(pedidoDTO));

		return converterEntityToDTO(pedido);
	}

	public PedidoDTO findPedidoById(Integer id) {
		return pedidoRepository.findById(id).isPresent() ? converterEntityToDTO(pedidoRepository.findById(id).get())
				: null;

	}

	public PedidoDTO updatePedido(PedidoDTO pedidoDTO) {
		Pedido pedido = pedidoRepository.save(ConverteDTOToEntidade(pedidoDTO));
		return converterEntityToDTO(pedido);
	}

	public void deletePedidoById(Integer id) {
		Pedido pedido = pedidoRepository.findById(id).get();
		pedidoRepository.delete(pedido);
	}

	public Pedido ConverteDTOToEntidade(PedidoDTO pedidoDTO) {
		Pedido pedido = new Pedido();
		Cliente cliente = new Cliente();
		pedido.setIdPedido(pedidoDTO.getIdPedido());
		pedido.setDataPedido(pedidoDTO.getDataPedido());
		pedido.setDataEntregaPedido(pedidoDTO.getDataEntregaPedido());
		pedido.setDataEnvioPedido(pedidoDTO.getDataEnvioPedido());
		pedido.setStatusPedido(pedidoDTO.getStatusPedido());

		pedido.setItemPedidoList(
				pedidoDTO.getItemPedidoList().stream().map(itemPedidoService::ConverteDTOToEntidade).collect(toList()));

		cliente = clienteService
				.ConverteDTOToEntidade(clienteService.findClienteById(pedidoDTO.getClienteDTO().getIdCliente()));
		pedido.setCliente(cliente);
		return pedido;
	}

	public PedidoDTO converterEntityToDTO(Pedido pedido) {
		PedidoDTO pedidoDTO = new PedidoDTO();
		pedidoDTO.setIdPedido(pedido.getIdPedido());
		pedidoDTO.setDataPedido(pedido.getDataPedido());
		pedidoDTO.setDataEntregaPedido(pedido.getDataEntregaPedido());
		pedidoDTO.setIdPedido(pedido.getIdPedido());
		pedidoDTO.setDataEnvioPedido(pedido.getDataEnvioPedido());
		pedidoDTO.setStatusPedido(pedido.getStatusPedido());
		pedidoDTO.setItemPedidoList(
				pedido.getItemPedidoList().stream().map(itemPedidoService::converterEntityToDTO).collect(toList()));

		return pedidoDTO;
	}

}
