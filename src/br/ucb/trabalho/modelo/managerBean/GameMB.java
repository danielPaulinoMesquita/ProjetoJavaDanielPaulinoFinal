package br.ucb.trabalho.modelo.managerBean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.ucb.trabalho.modelo.bean.Cliente;
import br.ucb.trabalho.modelo.bean.Game;
import br.ucb.trabalho.modelo.bean.Vendedor;
import br.ucb.trabalho.modelo.dao.GenericDAO;
import br.ucb.trabalho.modelo.enums.Categoria;
import br.ucb.trabalho.util.JSFMensageiro;

@SessionScoped
@ManagedBean(name="gameMb")
public class GameMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private Game game;
	private List<Vendedor> vendedores;
	private List<Cliente> clientes;
	private List<Game> games;
	private GenericDAO<Game> gameDao;
	private GenericDAO<Vendedor> vendedorDao;
	private GenericDAO<Cliente> clienteDao;
	
	
	public GameMB() {
		this.game= null;
		this.games= null;
		this.clientes= null;
		this.vendedores= null;
		this.gameDao= new GenericDAO<Game>() {
			private static final long serialVersionUID = 1L;};
		this.clienteDao= new GenericDAO<Cliente>() {
			private static final long serialVersionUID = 1L;};
		this.vendedorDao= new GenericDAO<Vendedor>() {
			private static final long serialVersionUID = 1L;};
	}
	public String listar() {
		this.clientes= this.clienteDao.listar(); //JÁ CARREGA OS CLIENTES, CASO TENHA UMA FUTURA ALTERAÇÃO
		this.vendedores= this.vendedorDao.listar();//JÁ CARREGA OS VENDEDORES, CASO TENHA UMA FUTURA ALTERAÇÃO
		this.games= this.gameDao.listar(); 
		return "/entidades/gameLista";
	}
	public String incluir() {	
		this.game= new Game();
		this.clientes= this.clienteDao.listar();
		this.vendedores= this.vendedorDao.listar();
		return "/entidades/gameForm";
	}
	public String gravar() {
		if(this.game.getId()!=0) {
			if(this.gameDao.alterar(game)) {
				JSFMensageiro.info("Sucesso na alteração!!");
			}else {
				JSFMensageiro.error("Erro na alteração");
			}
		}else {
			if(this.gameDao.incluir(game)) {
				JSFMensageiro.info("Sucesso na Gravação!!");
				this.game.getVendedor().getGames().add(game);
			}else {
				JSFMensageiro.error("Erro na Gravação");
			}
		}
		this.game= new Game();
		this.games= this.gameDao.listar();
		return "/entidades/gameLista";
	}
	public void excluir(Game game) {
		this.game.getVendedor().getGames().remove(game);
		this.gameDao.excluir(game);
		
		this.games=this.gameDao.listar();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public GenericDAO<Game> getGameDao() {
		return gameDao;
	}

	public void setGameDao(GenericDAO<Game> gameDao) {
		this.gameDao = gameDao;
	}
	public List<Vendedor> getVendedores() {
		return vendedores;
	}
	public void setVendedores(List<Vendedor> vendedores) {
		this.vendedores = vendedores;
	}
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	public GenericDAO<Vendedor> getVendedorDao() {
		return vendedorDao;
	}
	public void setVendedorDao(GenericDAO<Vendedor> vendedorDao) {
		this.vendedorDao = vendedorDao;
	}
	public GenericDAO<Cliente> getClienteDao() {
		return clienteDao;
	}
	public void setClienteDao(GenericDAO<Cliente> clienteDao) {
		this.clienteDao = clienteDao;
	}
	
	public List<Categoria> getCategorias(){
		return Arrays.asList(Categoria.values());
	}
	
	
}
