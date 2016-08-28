package br.com.caelum.notasfiscais.mb;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Produto;
import br.com.caelum.notasfiscais.tx.Transactional;
import br.com.caelum.notasfiscais.util.EmailComercial;
import br.com.caelum.notasfiscais.util.EmailFinanceiro;

@Named @RequestScoped
public class ProdutoBean {
	private Long produtoId;

	@Inject @EmailComercial
	private String emailComercial;
	
	@Inject @EmailFinanceiro
	private String emailFinanceiro;
	
	private Produto produto = new Produto();
	private List<Produto> produtos;
	
	@Inject
	private ProdutoDao dao;

	public Produto getProduto() {
		return this.produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	@Transactional
	public String grava() {
		
		if (produto.getId() == null) {
			dao.adiciona(produto);
		}else{
			dao.atualiza(produto);
		}
		this.produto = new Produto();
		this.produtos = dao.listaTodos();
		
		System.out.println(emailFinanceiro+" - "+emailComercial);
		
		return "produto?faces-redirect=true";
	}

	public List<Produto> getProdutos() {
		if (produtos == null) {
			System.out.println("Carregando produtos...");
			produtos = dao.listaTodos();
		}
		return produtos;
	}
	
	@Transactional
	public void remove(Produto produto) {
		dao.remove(produto);
		this.produtos = dao.listaTodos();
	}
	
	public String cancela() {
		this.produto = new Produto();
		return "produto";
	}
	
	public void carregaProduto(){
		if(produtoId!=null && produtoId > 0){
			this.produto = this.dao.buscaPorId(produtoId);
		}
	}
	public Long getProdutoId() {
		return produtoId;
	}
	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}
}