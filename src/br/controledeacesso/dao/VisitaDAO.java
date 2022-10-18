package br.controledeacesso.dao;

import java.util.List;

import br.controledeacesso.dto.VisitaDTO;



public interface VisitaDAO {

	public void salvar(VisitaDTO visitaDTO);

	public void atualizar(VisitaDTO visitaDTO);

	public void excluir(VisitaDTO visitaDTO);

	public VisitaDTO carregar(Integer id);

	public VisitaDTO buscarPorData(String data);

	public List<VisitaDTO> listar();

}
