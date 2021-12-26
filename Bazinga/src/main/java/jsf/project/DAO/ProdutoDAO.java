package jsf.project.DAO;

import javax.persistence.EntityManager;

import jsf.project.model.Produto;

public class ProdutoDAO extends GenericDAO<Long, Produto> {

	public ProdutoDAO(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

}
