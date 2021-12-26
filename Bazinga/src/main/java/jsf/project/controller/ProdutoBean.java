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
import jsf.project.DAO.ProdutoDAO;
import jsf.project.model.Pedido;
import jsf.project.model.Produto;
import jsf.project.util.Persistencia;
import jsf.project.util.UsuarioLogado;

@Named
@SessionScoped
@ManagedBean(name="produtoBean")
public class ProdutoBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Produto produto = new Produto();
	
	@Inject
	private Pedido pedido = new Pedido();
	
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Inject
	private List<Produto> produtos = new ArrayList<>();

	public ProdutoBean() {
		adicionarProdutos();
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public void adicionarProdutos() {
		//Isso pega todos os produtos do banco de dados e adiciona na lista produtos
		Persistencia p = new Persistencia();
		p.beginTransaction();
		ProdutoDAO pgDAO = new ProdutoDAO(p.getEm());
		setProdutos(pgDAO.findAll());
		p.commit();
		p.close();
		
		if(produtos.size()<=0) {
			//Lista vazia, nenhum produto cadastrado
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Vazio",  "Sem produtos no estoque!") );
		}
	}
	
	public String produtoSelecionado() {
		return "/primefaces/produtoSelecionado.xhtml";
	}
	
	public String adicionarSacola(){
		Persistencia p = new Persistencia();
		p.beginTransaction();
		
		PedidoDAO pgDAO = new PedidoDAO(p.getEm());
		pedido.setIdPedido(produto.getIdProduto());
		
		pgDAO.save(pedido);
		p.commit();
		p.close();
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Successfull",  "Produto adicionado na sacola!") );
		
		return null;
	}
	
	public String cadastrarProduto() {
		Persistencia p = new Persistencia();
		p.beginTransaction();
		ProdutoDAO pgDAO = new ProdutoDAO(p.getEm());
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		try{
			produto.setUsuarioVendedor(UsuarioLogado.getUsuario());
			pgDAO.save(produto);
			p.commit();
			p.close();
			
			context.addMessage(null, new FacesMessage("Successful",  "Produto cadastrado com sucesso!") );
			return "/primefaces/produtos.xhtml";
		}catch(Exception e) {
			context.addMessage(null, new FacesMessage("Unsuccessful",  "Erro ao cadastrar produto!") );
		}
		return null;
	}
	
}
