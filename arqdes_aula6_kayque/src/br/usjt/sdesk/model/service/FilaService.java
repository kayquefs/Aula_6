package br.usjt.sdesk.model.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.sdesk.model.dao.FilaDAO;
import br.usjt.sdesk.model.entity.Fila;

@Service
public class FilaService {
	private FilaDAO dao;
	
	@Autowired
	public FilaService(FilaDAO dao){
		this.dao = dao;
	}
	
	public List<Fila> listarFilas() throws IOException{
		return dao.listarFilas();
	}
	
	public Fila carregar(int id) throws IOException{
		return dao.carregar(id);
	}

	public List<Fila> carregarNovas(Date data) throws IOException{
		return dao.carregarNovas(data);
	}

}
