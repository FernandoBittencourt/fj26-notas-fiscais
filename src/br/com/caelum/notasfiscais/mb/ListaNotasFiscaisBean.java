package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.datamodel.DataModelNotasFiscais;

@Named
@ViewScoped
public class ListaNotasFiscaisBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private DataModelNotasFiscais dataModel;

	public DataModelNotasFiscais getDataModel() {
		return dataModel;
	}

	public void setDataModel(DataModelNotasFiscais dataModel) {
		this.dataModel = dataModel;
	}
	
}