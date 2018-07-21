package br.ucb.trabalho.modelo.managerBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import br.ucb.trabalho.modelo.bean.Game;
import br.ucb.trabalho.modelo.bean.Vendedor;
import br.ucb.trabalho.modelo.dao.GenericDAO;
import br.ucb.trabalho.modelo.dao.VendedorDAO;
import br.ucb.trabalho.util.JSFMensageiro;

@SessionScoped
@ManagedBean(name = "vendedorMb")
public class VendedorMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Vendedor vendedor;
	private List<Vendedor> vendedores;
	private VendedorDAO vendedorDao;
	private String nomeConsulta;

	public VendedorMB() {
		this.vendedor = null;
		this.vendedores = null;
		this.nomeConsulta= null;
		this.vendedorDao = new VendedorDAO();
	}

	public String listar() {
		this.vendedor = new Vendedor();
		this.vendedores = this.vendedorDao.listar();
		return "/entidades/vendedorLista";
	}

	public String incluir() {
		this.vendedor = new Vendedor();
		return "/entidades/vendedorForm";
	}

	public String gravar() {
		if (this.vendedor.getId() != 0) {
			if (this.vendedorDao.alterar(vendedor)) {
				JSFMensageiro.info("Sucesso na alteração!!");
			} else {
				JSFMensageiro.error("Erro na alteração");
			}
		} else {
			if (this.vendedorDao.incluir(vendedor)) {
				JSFMensageiro.info("Sucesso na Gravação!!");
			} else {
				JSFMensageiro.error("Erro na Gravação");
			}
		}
		this.vendedor = new Vendedor();
		this.vendedores = this.vendedorDao.listar();
		return "/entidades/vendedorLista";
	}

	public void excluir(Vendedor vendedor) {
		this.vendedorDao.excluir(vendedor);
		this.vendedores = this.vendedorDao.listar();
	}

	public String vendedorDestaque() {
		this.vendedor = new Vendedor();
		this.vendedor = this.vendedorDao.maisVendeu();
		return "/entidades/vendedorDestaque";
	}

	public String consultar() {
		this.vendedores=new ArrayList<Vendedor>();
		this.vendedores = this.vendedorDao.filtrar(getNomeConsulta());
		if (this.vendedores != null) {
			JSFMensageiro.info("Encontrado com Sucesso");
		} else {
			JSFMensageiro.error("Não foi localizado vendedores com esse nome!!");
		}
		return "/entidades/vendedorLista";

	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public List<Vendedor> getVendedores() {
		return vendedores;
	}

	public void setVendedores(List<Vendedor> vendedores) {
		this.vendedores = vendedores;
	}

	public String getNomeConsulta() {
		return nomeConsulta;
	}

	public void setNomeConsulta(String nomeConsulta) {
		this.nomeConsulta = nomeConsulta;
	}

	

		
}
