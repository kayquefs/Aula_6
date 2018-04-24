package br.usjt.sdesk.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.usjt.sdesk.model.entity.Chamado;
import br.usjt.sdesk.model.entity.Fila;
import br.usjt.sdesk.model.service.ChamadoService;
import br.usjt.sdesk.model.service.FilaService;

@RestController
public class ManterChamadosRest {

	private ChamadoService cService;
	private FilaService fService;

	@Autowired
	public ManterChamadosRest(ChamadoService c, FilaService f) {
		cService = c;
		fService = f;
	}

	@RequestMapping(method = RequestMethod.GET, value = "rest/chamados/{id}")
	public @ResponseBody List<Chamado> listarChamados(
			@PathVariable("id") Long id) {
		List<Chamado> chamados = null;
		try {
			Fila fila = fService.carregar(id.intValue());
			chamados = cService.listarChamados(fila);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return chamados;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "rest/chamados")
	public @ResponseBody List<Chamado> listarTodosChamados() {
		List<Chamado> chamados = null;
		try {
			chamados = cService.listarTodosChamados();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return chamados;
	}

	@RequestMapping(method = RequestMethod.GET, value = "rest/chamados/abertos/{id}")
	public @ResponseBody List<Chamado> listarChamadosAbertos(
			@PathVariable("id") Long id) {
		List<Chamado> chamados = null;
		try {
			Fila fila = fService.carregar(id.intValue());
			chamados = cService.listarTodosChamados();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return chamados;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "rest/filas")
	public @ResponseBody List<Fila> listarFilas() {
		List<Fila> filas = null;
		try {
			filas = fService.listarFilas();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filas;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "rest/filas/{data}")
	public @ResponseBody List<Fila> listarFilasNovas(
			@PathVariable("data") String data) {
		List<Fila> filas = null;
		try {
			DateFormat formatter = new SimpleDateFormat(Fila.DATE_PATTERN);
			Date dataAtualizacao = (Date) formatter.parse(data);
			filas = fService.carregarNovas(dataAtualizacao);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return filas;
	}

	@Transactional
	@RequestMapping(method = RequestMethod.POST, value = "rest/chamados")
	public ResponseEntity<Chamado> criarChamado(@RequestBody Chamado chamado) {
		try {
			cService.novoChamado(chamado);
			return new ResponseEntity<Chamado>(chamado, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<Chamado>(chamado,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	@RequestMapping(method = RequestMethod.PUT, value = "rest/chamados")
	public void fecharChamados(@RequestBody List<Chamado> chamados) {
		try {
			ArrayList<Integer> lista = new ArrayList<>();
			for(Chamado chamado:chamados){
				lista.add(chamado.getNumero());
			}
			cService.fecharChamados(lista);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}









