package jsf.project.DAO;

import javax.persistence.EntityManager;

import jsf.project.model.Pedido;

public class PedidoDAO extends GenericDAO<Long, Pedido> {

	public PedidoDAO(EntityManager em) {
		super(em);
		// TODO Auto-generated constructor stub
	}

}
