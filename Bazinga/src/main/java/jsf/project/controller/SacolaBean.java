package jsf.project.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
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
	
	public void adicionarPedido() {
		Persistencia p = new Persistencia();
		p.beginTransaction();
		
		//Cria um novo pedido do usuario
		PedidoDAO pgpDAO = new PedidoDAO(p.getEm());
		pedido.setProdutoPedido(produtoPedido);
		pgpDAO.save(pedido);
		
		p.commit();
		p.close();
	}
	
	public String adicionarSacola() {
		Persistencia p = new Persistencia();
		p.beginTransaction();
		
		//Pega a sacola aberta do usuario
		SacolaDAO pgDAO = new SacolaDAO(p.getEm());
		setSacola(pgDAO.findAllOfComprador());
		
		//Usuario nao possui sacola aberta
		if(sacola.size()<=0){
			//Cria um novo pedido
			adicionarPedido();
			
			//Cria uma nova sacola
			Sacola novaSacola = new Sacola();
			novaSacola.setStatus(1);
			novaSacola.setUsuarioComprador(UsuarioLogado.getUsuario());
			
			List<Pedido> novoPedido = new ArrayList<>();
			novoPedido.add(pedido);
			novaSacola.setPedidos(novoPedido);
			
			sacola.add(novaSacola);
			
			UsuarioLogado.setSacola(sacola.get(0));
			pgDAO.save(UsuarioLogado.getSacola());
		}else {
			//Altera a sacola existente
			List<Pedido> pedidosExistentes = sacola.get(0).getPedidos();
			int itemNaSacola = 0;
			for(int i=0;i<pedidosExistentes.size();i++){
				if(pedidosExistentes.get(i).getProdutoPedido().getIdProduto()==pedido.getIdPedido()){
					itemNaSacola = 1;
					pedidosExistentes.get(i).setQuantidade(pedido.getQuantidade());
					break;
				}
			}
			if(itemNaSacola==0) {
				List<Pedido> novoPedido = new ArrayList<>();
				novoPedido.add(pedido);
				sacola.get(0).setPedidos(novoPedido);
			}
			UsuarioLogado.setSacola(sacola.get(0));
			pgDAO.update(UsuarioLogado.getSacola());
		}
		p.commit();
		p.close();
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Successfull",  "Produto adicionado ou alterado na sacola!") );
		
		return null;
	}

	public SacolaBean() {
		// TODO Auto-generated constructor stub
	}

}
