package br.controledeacesso.dao;

import java.util.List;

import br.controledeacesso.dto.VisitaDTO;
import br.controledeacesso.util.DAOFactory;

public class VisitaRN {
	
	private VisitaDAO visitaDAO;
	
	public VisitaRN() {
		this.visitaDAO = DAOFactory.criarVisitaDAO();
	}
	
	public VisitaDTO carregar(Integer id) {
		return this.visitaDAO.carregar(id);
	}

	public VisitaDTO buscarPorCpf(String data) {
		return this.visitaDAO.buscarPorData(data);
	}

	public void salvar(VisitaDTO visitaDTO) {
		Integer id = visitaDTO.getCodRegistro();
		if (id == null || id == 0) {
			this.visitaDAO.salvar(visitaDTO);
		} else {
			this.visitaDAO.atualizar(visitaDTO);
		}
	}

	public void excluir(VisitaDTO VisitaDTO) {
		this.visitaDAO.excluir(VisitaDTO);
	}

	public List<VisitaDTO> listar() {
		return this.visitaDAO.listar();
	}

}
