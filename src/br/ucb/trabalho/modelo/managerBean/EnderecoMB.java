package br.ucb.trabalho.modelo.managerBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.ucb.trabalho.modelo.bean.Endereco;
import br.ucb.trabalho.modelo.dao.GenericDAO;
import br.ucb.trabalho.util.JSFMensageiro;


@SessionScoped
@ManagedBean(name="enderecoMb")
public class EnderecoMB implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Endereco endereco;
	private List<Endereco> enderecos;
	private GenericDAO<Endereco> enderecoDao;
	
	
	@SuppressWarnings("serial")
	public EnderecoMB() {
		this.endereco= null;
		this.enderecos= null;
		this.enderecoDao= new GenericDAO<Endereco>() {
		};		
	}
	public String listar() {
		this.endereco= new Endereco();
		this.enderecos= this.enderecoDao.listar();
		return "/entidades/enderecoLista";
	}
	public String incluir() {	
		this.endereco= new Endereco();
		return "/entidades/enderecoForm";
	}
	public String gravar() {
		if(this.endereco.getId()!=0) {
			if(this.enderecoDao.alterar(endereco)) {
				JSFMensageiro.info("Sucesso na alteração!!");
			}else {
				JSFMensageiro.error("Erro na alteração");
			}
		}else {
			if(this.enderecoDao.incluir(endereco)) {
				JSFMensageiro.info("Sucesso na Gravação!!");
			}else {
				JSFMensageiro.error("Erro na Gravação");
			}
		}
		this.endereco= new Endereco();
		this.enderecos= this.enderecoDao.listar();
		return "/entidades/enderecoLista";
	}
	public void excluir(Endereco endereco) {
		this.enderecoDao.excluir(endereco);
		this.enderecos= this.enderecoDao.listar();		
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	public GenericDAO<Endereco> getEnderecoDao() {
		return enderecoDao;
	}
	public void setEnderecoDao(GenericDAO<Endereco> enderecoDao) {
		this.enderecoDao = enderecoDao;
	}
}
