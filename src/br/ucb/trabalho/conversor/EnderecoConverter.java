package br.ucb.trabalho.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ucb.trabalho.modelo.bean.Endereco;
import br.ucb.trabalho.modelo.dao.GenericDAO;

@FacesConverter(value="enderecoConversor")
public class EnderecoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String idEndereco) {
		GenericDAO<Endereco> enderecoDao= new GenericDAO<Endereco>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
		};	
		
		return (enderecoDao.consultar(Integer.parseInt(idEndereco)));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object endereco) {
		
		return String.valueOf(((Endereco)endereco).getId());
	}

}
