package br.controledeacesso.dao;

import java.util.List;

import br.controledeacesso.dto.UsuarioDTO;
import br.controledeacesso.util.DAOFactory;

//Página 197
public class UsuarioRN {

	private UsuarioDAO usuarioDAO;

	public UsuarioRN() {
		this.usuarioDAO = DAOFactory.criarUsuarioDAO();
	}

	public UsuarioDTO carregar(Integer id) {
		return this.usuarioDAO.carregar(id);
	}

	public UsuarioDTO buscarPorLogin(String login) {
		return this.usuarioDAO.buscarPorLogin(login);
	}

	public void salvar(UsuarioDTO usuarioDTO) {
		Integer id = usuarioDTO.getMatricula();
		if (id == null || id == 0) {
			usuarioDTO.getPermissao().add("ROLE_USER");
			this.usuarioDAO.salvar(usuarioDTO);
		} else {
			this.usuarioDAO.atualizar(usuarioDTO);
		}
	}

	public void excluir(UsuarioDTO usuarioDTO) {
		this.usuarioDAO.excluir(usuarioDTO);
	}

	public List<UsuarioDTO> listar() {
		return this.usuarioDAO.listar();
	}

}
