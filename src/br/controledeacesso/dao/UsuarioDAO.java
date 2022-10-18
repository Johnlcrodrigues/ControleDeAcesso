package br.controledeacesso.dao;

import java.util.List;

import br.controledeacesso.dto.UsuarioDTO;

public interface UsuarioDAO {

	public void salvar(UsuarioDTO usuarioDTO);

	public void atualizar(UsuarioDTO usuarioDTO);

	public void excluir(UsuarioDTO usuarioDTO);

	public UsuarioDTO carregar(Integer id);

	public UsuarioDTO buscarPorLogin(String login);

	public List<UsuarioDTO> listar();

}
