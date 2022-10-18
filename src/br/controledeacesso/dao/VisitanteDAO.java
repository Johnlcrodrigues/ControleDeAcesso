package br.controledeacesso.dao;

import java.util.List;

import br.controledeacesso.dto.VisitanteDTO;

public interface VisitanteDAO {

	public void salvar(VisitanteDTO visitanteDTO);

	public void atualizar(VisitanteDTO visitanteDTO);

	public void excluir(VisitanteDTO visitanteDTO);

	public VisitanteDTO carregar(Integer id);

	public VisitanteDTO buscaPorCpf(String cpf);
	
	public VisitanteDTO buscaPorId(Integer id);

	public List<VisitanteDTO> listar();

}
