package br.usjt.sdesk.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class Fila {
	public final static String DATE_PATTERN = "dd-MM-yyyy'T'HH:mm:ss'Z'Z";
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id_fila")
	private int id;
	
	@NotNull
	@Size(max=45)
	@Column(name="nm_fila")
	private String nome;
	
	@Null
	@Size(max=256)
	@Column(name="nm_figura")
	private String figura;
	
	@JsonFormat(pattern=Fila.DATE_PATTERN)
	@Null
	@Column(name="dt_atual")
	private Date dataAtualizacao;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getFigura() {
		return figura;
	}
	public void setFigura(String figura) {
		this.figura = figura;
	}
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	
	@Override
	public String toString() {
		return "Fila [id=" + id + ", nome=" + nome + ", figura=" + figura + ", dataAtualizacao=" + dataAtualizacao
				+ "]";
	}
	
}
