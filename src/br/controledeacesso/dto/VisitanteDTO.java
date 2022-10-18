package br.controledeacesso.dto;

import java.io.ByteArrayInputStream;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Entity
@Table(name = "tb_visitante")
public class VisitanteDTO {

	@Id
	@GeneratedValue
	@Column(name = "id_visitante")
	private int id;

	@Column(name = "nome_visitante", nullable = true)
	private String nome;

	@Column(name = "cpf_visitante", unique = true, nullable = true)
	private String cpf;

	@Column(name = "identidade_visitante", unique = true, nullable = true)
	private String identidade;

	@Column(name = "endereço_visitante", nullable = true)
	private String endereco;

	@Column(name = "telefone_visitante", nullable = true)
	private String telefone;

	@Column(name = "celular_visitante", nullable = true)
	private String celular;

	@Lob
	@Column(name = "foto_visitante", nullable = true, columnDefinition = "mediumblob")
	private byte[] foto;

	@Transient
	private StreamedContent imagem;

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getIdentidade() {
		return identidade;
	}

	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public StreamedContent getImagem() {
		if (this.getFoto() != null) {
			return new DefaultStreamedContent(new ByteArrayInputStream(
					this.getFoto()), "");
		} else {
			return new DefaultStreamedContent();
		}
	}

	public void setImagem(StreamedContent imagem) {
		this.imagem = imagem;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((celular == null) ? 0 : celular.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result
				+ ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + Arrays.hashCode(foto);
		result = prime * result + id;
		result = prime * result
				+ ((identidade == null) ? 0 : identidade.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result
				+ ((telefone == null) ? 0 : telefone.hashCode());
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
		VisitanteDTO other = (VisitanteDTO) obj;
		if (celular == null) {
			if (other.celular != null)
				return false;
		} else if (!celular.equals(other.celular))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (!Arrays.equals(foto, other.foto))
			return false;
		if (id != other.id)
			return false;
		if (identidade == null) {
			if (other.identidade != null)
				return false;
		} else if (!identidade.equals(other.identidade))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}

}
