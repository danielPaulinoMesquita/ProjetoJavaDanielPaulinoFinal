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
@ManagedBean(name="produtorGameMb")
public class ProdutorGameMB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<Produtor> produtores;
	private List<Game> games;
	private Produtor produtor;
	private Game game;
	private GenericDAO<Game> gameDao;
	private GenericDAO<Produtor> produtorDao;
	
	
	public ProdutorGameMB(){
		this.game= null;
		this.produtor= null;
		this.produtorDao= new GenericDAO<Produtor>() {
			private static final long serialVersionUID = 1L;
		};
		this.gameDao=new GenericDAO<Game>() {
			private static final long serialVersionUID = 1L;
		};
	}
	// DIRECIONAR AO FORMULÁRIO DE INCLUSÃO
	public String incluir() {
		this.produtor= new Produtor();
		this.game= new Game();
		this.produtores= produtorDao.listar();
		this.games= this.gameDao.listar();
		return "/entidades/produtorGameForm";
	}
	// EFETUAR GRAVAÇÃO DO RELACIONAMENTO produtor_game
	public String gravarProdutorEGame() {
		if(this.game!=null&&this.produtor!=null) {
			this.produtor.getGames().add(game);
			if(this.produtorDao.incluir(produtor)) {
				JSFMensageiro.info("Sucesso na Gravação! Relacionamento Prdutor e Jogo");
			}else {
				JSFMensageiro.error("Erro! na gravação do Relacionamento NxN ");
			}
		}else {
			JSFMensageiro.error("Erro!(na ManagedBean)Os objetos devem ser selecionados! ");
		}
		this.produtor= null;
		this.game= null;
		this.produtores= this.produtorDao.listar();
		return "/index";
	}
	public List<Produtor> getProdutores() {
		return produtores;
	}
	public void setProdutores(List<Produtor> produtores) {
		this.produtores = produtores;
	}
	public List<Game> getGames() {
		return games;
	}
	public void setGames(List<Game> games) {
		this.games = games;
	}
	public Produtor getProdutor() {
		return produtor;
	}
	public void setProdutor(Produtor produtor) {
		this.produtor = produtor;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	
	
	
	
	

}
