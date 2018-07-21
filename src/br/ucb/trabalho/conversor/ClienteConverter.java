package br.ucb.trabalho.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import br.ucb.trabalho.modelo.bean.Cliente;

import br.ucb.trabalho.modelo.dao.GenericDAO;

@FacesConverter(value="clienteConversor")
public class ClienteConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String idCliente) {
		GenericDAO<Cliente> clienteDao= new GenericDAO<Cliente>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
		};
		
		return clienteDao.consultar(Integer.parseInt(idCliente));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object cliente) {
		
		return String.valueOf(((Cliente) cliente).getId());
	}
	
}
