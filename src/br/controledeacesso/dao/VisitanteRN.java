package br.controledeacesso.dao;

import java.util.List;

import br.controledeacesso.dto.VisitanteDTO;
import br.controledeacesso.util.DAOFactory;

public class VisitanteRN {

	private VisitanteDAO visitanteDAO;

	public VisitanteRN() {
		this.visitanteDAO = DAOFactory.criarVisitanteDAO();
	}

	public VisitanteDTO carregar(Integer id) {
		return this.visitanteDAO.carregar(id);
	}

	public VisitanteDTO buscaPorCpf(String cpf) {
		return this.visitanteDAO.buscaPorCpf(cpf);
	}
	
	public VisitanteDTO buscaPorId(Integer id) {
		return this.visitanteDAO.buscaPorId(id);
	}

	public void salvar(VisitanteDTO visitanteDTO) {
		Integer id = visitanteDTO.getId();
		if (id == null || id == 0) {
			this.visitanteDAO.salvar(visitanteDTO);
		} else {
			this.visitanteDAO.atualizar(visitanteDTO);
		}
	}

	public void excluir(VisitanteDTO VisitanteDTO) {
		this.visitanteDAO.excluir(VisitanteDTO);
	}

	public List<VisitanteDTO> listar() {
		return this.visitanteDAO.listar();
	}

}
