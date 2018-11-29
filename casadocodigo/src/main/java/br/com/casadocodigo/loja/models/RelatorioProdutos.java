package br.com.casadocodigo.loja.models;

import java.util.Date;
import java.util.List;

public class RelatorioProdutos {
	
	private Date dataGeracao;
	private int quantidade;
	private List<Produto> produtos;
	
	public Date getDataGeracao() {
		return dataGeracao;
	}
	public void setDataGeracao(Date dataGeracao) {
		this.dataGeracao = dataGeracao;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}	

}
