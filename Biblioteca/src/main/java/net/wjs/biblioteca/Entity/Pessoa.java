package net.wjs.biblioteca.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="TB_usuario")
public class Pessoa {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id_usuario;

	@Column(nullable=false)
	private String ds_nome;

	public long getId() {
		return id_usuario;

	}
	public String getNome() {
		return ds_nome;
	}
	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}
	public void setDs_nome(String ds_nome) {
		this.ds_nome = ds_nome;
	}
	
}

