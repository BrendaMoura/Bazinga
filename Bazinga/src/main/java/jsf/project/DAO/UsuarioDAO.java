package jsf.project.DAO;

import javax.persistence.EntityManager;

import jsf.project.model.Usuario;

public class UsuarioDAO extends GenericDAO<String, Usuario> {

	public UsuarioDAO(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}
	
	

}
