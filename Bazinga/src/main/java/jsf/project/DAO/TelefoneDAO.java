package jsf.project.DAO;

import javax.persistence.EntityManager;

import jsf.project.model.Telefone;

public class TelefoneDAO extends GenericDAO<Long, Telefone> {

	public TelefoneDAO(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

}
