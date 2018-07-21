package br.ucb.trabalho.modelo.managerBean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import br.ucb.trabalho.modelo.bean.Cliente;
import br.ucb.trabalho.modelo.bean.Endereco;
import br.ucb.trabalho.modelo.dao.GenericDAO;
import br.ucb.trabalho.util.JSFMensageiro;

@SessionScoped
@ManagedBean(name="clienteMb")
public class ClienteMB implements Serializable{
	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	private List<Cliente> clientes;
	private GenericDAO<Cliente> clienteDao;
	private GenericDAO<Endereco> enderecoDao;
	private List<Endereco> enderecos;
	
	public ClienteMB() {
		this.cliente= null;
		this.clientes= null;
		this.enderecos= null;
		this.clienteDao= new GenericDAO<Cliente>() {
			private static final long serialVersionUID = 1L;
			};
		this.enderecoDao= new GenericDAO<Endereco>() {
			private static final long serialVersionUID = 1L;		
		};
	}
	public String listar() {
		this.enderecos= this.enderecoDao.listar();//JÁ CARREGA OS ENDEREÇOS ANTES DE ALTERAR
		this.clientes= this.clienteDao.listar();
		return "/entidades/clienteLista";
	}
	public String incluir() {	
		this.cliente= new Cliente();
		this.enderecos= this.enderecoDao.listar();
		return "/entidades/clienteForm";
	}
	public String gravar() {
		if(this.cliente.getId()!=0) {
			if(this.clienteDao.alterar(cliente)) {
				JSFMensageiro.info("Sucesso na alteração!!");
			}else {
				JSFMensageiro.error("Erro na alteração");
			}
		}else {
			if(this.clienteDao.incluir(cliente)) {
				JSFMensageiro.info("Sucesso na Gravação!!");
			}else {
				JSFMensageiro.error("Erro na Gravação");
			}
		}
		this.cliente= new Cliente();
		this.clientes= this.clienteDao.listar();
		return "/entidades/clienteLista";
	}
	public void excluir(Cliente cliente) {
		this.clienteDao.excluir(cliente);
		this.clientes= this.clienteDao.listar();
		
	}
	public void refresh() {
		FacesContext context = FacesContext.getCurrentInstance();
		Application application = context.getApplication();
		ViewHandler viewHandler = application.getViewHandler();
		UIViewRoot viewRoot = viewHandler.createView(context, context.getViewRoot().getViewId());
		context.setViewRoot(viewRoot);
		context.renderResponse();
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	public GenericDAO<Cliente> getClienteDao() {
		return clienteDao;
	}
	public void setClienteDao(GenericDAO<Cliente> clienteDao) {
		this.clienteDao = clienteDao;
	}
	public GenericDAO<Endereco> getEnderecoDao() {
		return enderecoDao;
	}
	public void setEnderecoDao(GenericDAO<Endereco> enderecoDao) {
		this.enderecoDao = enderecoDao;
	}
	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	
	
}
