package br.ucb.trabalho.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ucb.trabalho.modelo.bean.Vendedor;
import br.ucb.trabalho.modelo.dao.GenericDAO;

@FacesConverter(value="vendedorConversor")
public class VendedorConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String idVendedor) {
		GenericDAO<Vendedor> vendedorDao= new GenericDAO<Vendedor>() {
			private static final long serialVersionUID = 1L;
		};
		return vendedorDao.consultar(Integer.parseInt(idVendedor));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object vendedor) {	
		return String.valueOf(((Vendedor) vendedor).getId());
	}

}
