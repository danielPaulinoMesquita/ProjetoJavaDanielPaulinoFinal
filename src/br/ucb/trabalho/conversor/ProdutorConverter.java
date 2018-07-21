package br.ucb.trabalho.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ucb.trabalho.modelo.bean.Produtor;
import br.ucb.trabalho.modelo.dao.GenericDAO;

@FacesConverter(value="produtorConversor")
public class ProdutorConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String idProdutor) {
		GenericDAO<Produtor> produtorDao= new GenericDAO<Produtor>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
		};
		return (produtorDao.consultar(Integer.parseInt(idProdutor)));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object produtor) {
		
		return String.valueOf(((Produtor)produtor).getId());
				
	}

	
}
