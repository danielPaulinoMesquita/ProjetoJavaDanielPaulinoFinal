package br.ucb.trabalho.modelo.managerBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.ucb.trabalho.modelo.bean.Game;
import br.ucb.trabalho.modelo.bean.Produtor;
import br.ucb.trabalho.modelo.dao.GenericDAO;
import br.ucb.trabalho.util.JSFMensageiro;

@SessionScoped
@ManagedBean(name="produtorMb")
public class ProdutorMB implements Serializable{
	private static final long serialVersionUID = 1L;
	private Produtor produtor;
	private List<Produtor> produtores;
	private GenericDAO<Produtor> produtorDao;
	private GenericDAO<Game> gameDao;
	private List<Game> games;
	
	public ProdutorMB() {
		this.produtor= null;
		this.produtores=null;
		this.games= null;
		this.produtorDao= new GenericDAO<Produtor>() {
			private static final long serialVersionUID = 1L;
		};
		this.gameDao= new GenericDAO<Game>() {
			private static final long serialVersionUID = 1L;
		};
		
	}
	public String listar() {
		this.produtor= new Produtor();
		this.produtores= this.produtorDao.listar();
		return "/entidades/produtorLista";
	}
	public String incluir() {	
		this.produtor= new Produtor();
		this.games= this.gameDao.listar();
		return "/entidades/produtorForm";
	}
	public String gravar() {
		if(this.produtor.getId()!=0) {
			if(this.produtorDao.alterar(produtor)) {
				JSFMensageiro.info("Sucesso na alteração!!");
			}else {
				JSFMensageiro.error("Erro na alteração");
			}
		}else {
			if(this.produtorDao.incluir(produtor)) {
				JSFMensageiro.info("Sucesso na Gravação!!");
			
			}else {
				JSFMensageiro.error("Erro na Gravação");
			}
		}
		this.produtor= new Produtor();
		this.produtores= this.produtorDao.listar();
		return "/entidades/produtorLista";
	}
	public void excluir(Produtor produtor) {
		this.produtorDao.excluir(produtor);
		this.produtores=this.produtorDao.listar();
	}

	public Produtor getProdutor() {
		return produtor;
	}
	public void setProdutor(Produtor produtor) {
		this.produtor = produtor;
	}
	public List<Produtor> getProdutores() {
		return produtores;
	}
	public void setProdutores(List<Produtor> produtores) {
		this.produtores = produtores;
	}
	public GenericDAO<Produtor> getProdutorDao() {
		return produtorDao;
	}
	public void setProdutorDao(GenericDAO<Produtor> produtorDao) {
		this.produtorDao = produtorDao;
	}
	public GenericDAO<Game> getGameDao() {
		return gameDao;
	}
	public void setGameDao(GenericDAO<Game> gameDao) {
		this.gameDao = gameDao;
	}
	public List<Game> getGames() {
		return games;
	}
	public void setGames(List<Game> games) {
		this.games = games;
	}
		
}
