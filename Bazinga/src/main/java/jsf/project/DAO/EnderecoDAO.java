package jsf.project.DAO;

import javax.persistence.EntityManager;

import jsf.project.model.Endereco;

public class EnderecoDAO extends GenericDAO<Long, Endereco>{

	public EnderecoDAO(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

}
