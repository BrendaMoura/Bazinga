package jsf.project.DAO;

import javax.persistence.EntityManager;

import jsf.project.model.Sacola;

public class SacolaDAO extends GenericDAO<Long, Sacola> {

	public SacolaDAO(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

}
