package br.controledeacesso.dao;

import java.util.List;

import br.controledeacesso.dto.DepartamentoDTO;

public interface DepartamentoDAO {

	public void salvar(DepartamentoDTO departamentoDTO);

	public void atualizar(DepartamentoDTO departamentoDTO);

	public void excluir(DepartamentoDTO departamentoDTO);

	public DepartamentoDTO carregar(Integer id);

	public DepartamentoDTO buscarPorRamal(String ramal);

	public List<DepartamentoDTO> listar();

}
