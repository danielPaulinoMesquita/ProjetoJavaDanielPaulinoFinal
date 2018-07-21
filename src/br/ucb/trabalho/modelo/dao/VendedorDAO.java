package br.ucb.trabalho.modelo.dao;

import java.util.List;

import javax.persistence.Query;
import br.ucb.trabalho.modelo.bean.Vendedor;

public class VendedorDAO extends GenericDAO<Vendedor> {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<Vendedor> filtrar(String filtroNome) {
		Query q = this.getEm().createQuery("SELECT v FROM Vendedor v WHERE v.nome LIKE :filtroNome");
		q.setParameter("filtroNome", filtroNome + "%");
		
		return q.getResultList();

	}

	public Vendedor maisVendeu() {
		Query q = this.getEm()
				.createQuery("select v from Vendedor v, Game g where v.id=g.vendedor.id group by v.nome");
		Query resultado = q.setFirstResult(1);

		return (Vendedor) resultado.getSingleResult();
	}

}
