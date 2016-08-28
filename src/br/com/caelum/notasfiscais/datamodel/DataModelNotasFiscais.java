package br.com.caelum.notasfiscais.datamodel;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.Entity;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.caelum.notasfiscais.dao.NotaFiscalDao;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;

@Entity
public class DataModelNotasFiscais extends LazyDataModel<NotaFiscal>{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private NotaFiscalDao dao;
	
	public List<NotaFiscal> load(int inicio, int quantidade, String campoOrdenacao,	SortOrder sentidoOrdenacao, Map<String, Object> filtro) {
		return dao.listaTodosPaginada(inicio,quantidade);
	}
	
	@PostConstruct
	public void init(){
		this.setRowCount(this.dao.contaTodos());
	}
	
}
