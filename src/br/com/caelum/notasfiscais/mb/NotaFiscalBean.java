package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.NotaFiscalDao;
import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Item;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;
import br.com.caelum.notasfiscais.modelo.Produto;
import br.com.caelum.notasfiscais.tx.Transactional;

@Named
@ViewScoped
public class NotaFiscalBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private NotaFiscal notaFiscal = new NotaFiscal();
	
	private Item item = new Item();
	
	private Long idProduto;
	
	@Inject
	private ProdutoDao produtoDao;

	@Inject
	private NotaFiscalDao notaFiscalDao;

	@Transactional
	public void gravar() {

		this.notaFiscalDao.adiciona(notaFiscal);

		this.notaFiscal = new NotaFiscal();
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}
	
	public void guardaItem(){
		Produto produto = produtoDao.buscaPorId(idProduto);
		
		item.setProduto(produto);
		item.setValorUnitario(produto.getPreco());
		
		notaFiscal.getItens().add(item);
		item.setNotaFiscal(notaFiscal);
		
		item = new Item();
		idProduto = null;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}
	
	
}