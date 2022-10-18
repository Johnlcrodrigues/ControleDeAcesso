package br.controledeacesso.dao;

import java.util.List;

import br.controledeacesso.dto.DepartamentoDTO;
import br.controledeacesso.util.DAOFactory;

public class DepartamentoRN {

	private DepartamentoDAO departamentoDAO;

	public DepartamentoRN() {
		this.departamentoDAO = DAOFactory.criarDepartamentoDAO();
	}

	public DepartamentoDTO carregar(Integer id) {
		return this.departamentoDAO.carregar(id);
	}

	public DepartamentoDTO buscarPorRamal(String ramal) {
		return this.departamentoDAO.buscarPorRamal(ramal);
	}

	public void salvar(DepartamentoDTO departamentoDTO) {
		Integer id = departamentoDTO.getId();
		if (id == null || id == 0) {
			this.departamentoDAO.salvar(departamentoDTO);
		} else {
			this.departamentoDAO.atualizar(departamentoDTO);
		}
	}

	public void excluir(DepartamentoDTO departamentoDTO) {
		this.departamentoDAO.excluir(departamentoDTO);
	}

	public List<DepartamentoDTO> listar() {
		return this.departamentoDAO.listar();
	}

}
