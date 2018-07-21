package br.ucb.trabalho.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ucb.trabalho.modelo.bean.Game;
import br.ucb.trabalho.modelo.dao.GenericDAO;

@FacesConverter(value="gameConversor")
public class GameConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String idGame) {
		GenericDAO<Game> gameDao= new GenericDAO<Game>() {
			private static final long serialVersionUID = 1L;	
		};
		return gameDao.consultar(Integer.parseInt(idGame));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object game) {
		
		return String.valueOf(((Game) game).getId());
	}

}
