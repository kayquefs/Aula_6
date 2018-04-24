package br.usjt.sdesk.model.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.usjt.sdesk.model.entity.Chamado;
import br.usjt.sdesk.model.entity.Fila;
import br.usjt.sdesk.model.entity.Usuario;
@Repository
public class ChamadoDAO {
	@PersistenceContext
	EntityManager manager;
	
	public int inserirChamado(Chamado chamado) throws IOException {
		manager.persist(chamado);
		return chamado.getNumero();
	}
	
	public List<Chamado> listarChamadosAbertos(Fila fila) throws IOException{
		//conectei minha fila com a persistencia
		fila = manager.find(Fila.class, fila.getId());
		
		String jpql = "select c from Chamado c where c.fila = :fila and c.status = :status";
		
		Query query = manager.createQuery(jpql);
		query.setParameter("fila", fila);
		query.setParameter("status", Chamado.ABERTO);

		List<Chamado> result = query.getResultList();
		return result;
	}
	
	public List<Chamado> listarChamados(Fila fila) throws IOException{
		//conectei minha fila com a persistencia
				fila = manager.find(Fila.class, fila.getId());
				
				String jpql = "select c from Chamado c where c.fila = :fila";
				
				Query query = manager.createQuery(jpql);
				query.setParameter("fila", fila);

				List<Chamado> result = query.getResultList();
				return result;
	}

	public void fecharChamados(ArrayList<Integer> lista) throws IOException{
		
			for(int id:lista){
				Chamado chamado = manager.find(Chamado.class, id);
				chamado.setDataFechamento(new java.util.Date());
				chamado.setStatus(Chamado.FECHADO);
				manager.merge(chamado);
			}
		
	}

	public List<Chamado> listarTodosChamados() throws IOException{
		return manager.createQuery("select c from Chamado c").getResultList();

	}


}
