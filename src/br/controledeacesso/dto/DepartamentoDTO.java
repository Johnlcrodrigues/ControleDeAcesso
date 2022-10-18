package br.controledeacesso.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_departamento")
public class DepartamentoDTO {

	@Id
	@GeneratedValue
	@Column(name = "id_departamento")
	private int id;

	@Column(name = "nome_departamento", nullable = true)
	private String nome;

	@Column(name = "chefe_departamento", nullable = true)
	private String chefe;

	@Column(name = "integrante_departamento", nullable = true)
	private String integrante;

	@Column(name = "ramal_departamento", nullable = true)
	private String ramal;

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

	public String getChefe() {
		return chefe;
	}

	public void setChefe(String chefe) {
		this.chefe = chefe;
	}

	public String getintegrante() {
		return integrante;
	}

	public void setintegrante(String integrante) {
		this.integrante = integrante;
	}

	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chefe == null) ? 0 : chefe.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((integrante == null) ? 0 : integrante.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((ramal == null) ? 0 : ramal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DepartamentoDTO other = (DepartamentoDTO) obj;
		if (chefe == null) {
			if (other.chefe != null)
				return false;
		} else if (!chefe.equals(other.chefe))
			return false;
		if (id != other.id)
			return false;
		if (integrante == null) {
			if (other.integrante != null)
				return false;
		} else if (!integrante.equals(other.integrante))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (ramal == null) {
			if (other.ramal != null)
				return false;
		} else if (!ramal.equals(other.ramal))
			return false;
		return true;
	}

}
