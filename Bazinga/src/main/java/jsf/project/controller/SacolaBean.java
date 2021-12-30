package jsf.project.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

import jsf.project.DAO.PedidoDAO;
import jsf.project.DAO.SacolaDAO;
import jsf.project.model.Pedido;
import jsf.project.model.Produto;
import jsf.project.model.Sacola;
import jsf.project.util.Persistencia;
import jsf.project.util.UsuarioLogado;

@Named
@SessionScoped
@ManagedBean(name="sacolaBean")
public class SacolaBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Produto produtoPedido = new Produto();

	@Inject
	private Pedido pedido = new Pedido();

	@Inject
	private List<Sacola> sacola = new ArrayList<Sacola>();
	
	@Inject
	private List<Pedido> produtosSacola = new ArrayList<>();
	
	public List<Pedido> getProdutosSacola() {
		return produtosSacola;
	}

	public void setProdutosSacola(List<Pedido> produtosSacola) {
		this.produtosSacola = produtosSacola;
	}

	public List<Sacola> getSacola() {
		return sacola;
	}

	public void setSacola(List<Sacola> sacola) {
		this.sacola = sacola;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public Produto getProdutoPedido() {
		return produtoPedido;
	}

	public void setProdutoPedido(Produto produtoPedido) {
		this.produtoPedido = produtoPedido;
	}
	
	public String adicionarItem(){
		//Pedido
		setProdutoPedido(UsuarioLogado.getProduto());
		
		Persistencia p = new Persistencia();
		p.beginTransaction();
		
		//Pega a sacola aberta do usuario
		SacolaDAO pgDAO = new SacolaDAO(p.getEm());
		setSacola(pgDAO.findAllOfComprador());
		
		PedidoDAO pgpDAO = new PedidoDAO(p.getEm());
		
		//Verifica se o usuario possui sacola aberta
		if(sacola.size()<=0) {
			//Cria uma sacola nova, pois o usuario nao tem
			//Cria uma nova sacola
			Sacola novaSacola = new Sacola();
			novaSacola.setStatus(1);
			novaSacola.setUsuarioComprador(UsuarioLogado.getUsuario());
			
			try {
				pgDAO.save(novaSacola);
				
				//Cria um novo pedido do usuario
				pedido.setProdutoPedido(produtoPedido);
				//Adiciona a sacola
				pedido.setSacola(novaSacola);
				
				pgpDAO.save(pedido);
				
				p.commit();
				p.close();
			}catch(Exception e) {
				System.out.println("Erro sacola nova: "+e);
			}
			
		}else {
			//Altera a sacola existente
			//Pega a sacola aberta e adiciona na classe util
			UsuarioLogado.setSacola(sacola.get(0));
			
			//Pega todos os pedidos abertos
			List<Pedido> pedidosExistentes = pgpDAO.findAllOpenPedidos();
			
			int itemNaSacola = 0;
			
			for(int i=0;i<pedidosExistentes.size();i++) {
				if(pedidosExistentes.get(i).getIdPedido()==produtoPedido.getIdProduto()) {
					itemNaSacola = 1;
					pgpDAO.update(pedido);
					//pedidosExistentes.get(i).setQuantidade(pedido.getQuantidade());
				}
			}
			
			if(itemNaSacola==0) {
				//Cria um novo pedido do usuario
				pedido.setProdutoPedido(produtoPedido);
				//Adiciona a sacola
				pedido.setSacola(sacola.get(0));
				
				pgpDAO.save(pedido);
				
				//pedidosExistentes.add(pedido);
			}
			
			p.commit();
			p.close();
		}
		
		return "/primefaces/produtos.xhtml";
	}
	
	public void pegarPedidos() {
		Persistencia p = new Persistencia();
		p.beginTransaction();
		
		//Pega a sacola aberta do usuario
		SacolaDAO pgDAO = new SacolaDAO(p.getEm());
		setSacola(pgDAO.findAllOfComprador());
		if(sacola.size()!=0) {
			UsuarioLogado.setSacola(sacola.get(0));
			
			PedidoDAO pgpDAO = new PedidoDAO(p.getEm());
			
			//Pega todos os pedidos abertos
			setProdutosSacola(pgpDAO.findAllOpenPedidos());
			
			p.commit();
			p.close();
		}
	}

	public SacolaBean() {
		pegarPedidos();
		// TODO Auto-generated constructor stub
	}
	
	

}
