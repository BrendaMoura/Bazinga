package jsf.project.DAO;

import javax.persistence.EntityManager;

import jsf.project.model.FormaPagamento;

public class FormaPagamentoDAO extends GenericDAO<Long, FormaPagamento> {

	public FormaPagamentoDAO(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

}
