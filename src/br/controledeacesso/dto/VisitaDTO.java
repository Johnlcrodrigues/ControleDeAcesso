package br.controledeacesso.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.ForeignKey;

@Entity
@Table(name = "tb_visita")
public class VisitaDTO {

	@Id
	@GeneratedValue
	@Column(name = "id_visita")
	private int codRegistro;

	@Column(name = "data_visita", nullable = true)
	private Date data;

	// @Column(name = "horaentrada_visita", nullable = true)
	// private Date horaEntrada;
	//
	// @Column(name = "horasaida_visita", nullable = true)
	// private Date horaSaida;

	// Olhar página 319
	@ManyToOne
	@JoinColumn(name = "id_visitante", nullable = true, foreignKey = @ForeignKey(name = "fk_visita_visitante"))
	private VisitanteDTO idVisitante;

	@ManyToOne
	@JoinColumn(name = "id_departamento", nullable = true, foreignKey = @ForeignKey(name = "fk_visita_departamento"))
	private DepartamentoDTO idDepartamento;

	public int getCodRegistro() {
		return codRegistro;
	}

	public void setCodRegistro(int codRegistro) {
		this.codRegistro = codRegistro;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	// public Date getHoraEntrada() {
	// return horaEntrada;
	// }
	//
	// public void setHoraEntrada(Date horaEntrada) {
	// this.horaEntrada = horaEntrada;
	// }
	//
	// public Date getHoraSaida() {
	// return horaSaida;
	// }
	//
	// public void setHoraSaida(Date horaSaida) {
	// this.horaSaida = horaSaida;
	// }

	public VisitanteDTO getIdVisitante() {
		return idVisitante;
	}

	public void setIdVisitante(VisitanteDTO idVisitante) {
		this.idVisitante = idVisitante;
	}

	public DepartamentoDTO getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(DepartamentoDTO idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codRegistro;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result
				+ ((idDepartamento == null) ? 0 : idDepartamento.hashCode());
		result = prime * result
				+ ((idVisitante == null) ? 0 : idVisitante.hashCode());
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
		VisitaDTO other = (VisitaDTO) obj;
		if (codRegistro != other.codRegistro)
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (idDepartamento == null) {
			if (other.idDepartamento != null)
				return false;
		} else if (!idDepartamento.equals(other.idDepartamento))
			return false;
		if (idVisitante == null) {
			if (other.idVisitante != null)
				return false;
		} else if (!idVisitante.equals(other.idVisitante))
			return false;
		return true;
	}

}
