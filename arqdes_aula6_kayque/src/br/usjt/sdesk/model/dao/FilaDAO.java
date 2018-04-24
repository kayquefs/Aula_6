package br.usjt.sdesk.model.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Repository;

import br.usjt.sdesk.model.entity.Chamado;
import br.usjt.sdesk.model.entity.Fila;
@Repository
public class FilaDAO {
	@PersistenceContext
	EntityManager manager;
	
	public List<Fila> listarFilas() throws IOException {
		return manager.createQuery("select f from Fila f").getResultList();
	}

	public Fila carregar(int id) throws IOException {
		return manager.find(Fila.class, id);
	}

	public List<Fila> carregarNovas(Date data) throws IOException  {
		
		String jpql = "select f from Fila f where f.dataAtualizacao > :data";
		
		Query query = manager.createQuery(jpql);
		query.setParameter("data", data, TemporalType.TIMESTAMP);

		List<Fila> result = query.getResultList();
		return result;
	}
}
