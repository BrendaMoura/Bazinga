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

import jsf.project.DAO.ProdutoDAO;
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
	private List<Produto> produtos = new ArrayList<Produto>();

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ProdutoBean() {
		//Verifica quais produtos carregar
		
		if(UsuarioLogado.getUsuario().getTipo()==1) {
			//Todos os produtos
			adicionarProdutos(1);
		}else {
			//Produtos apenas daquele vendedor
			adicionarProdutos(2);
		}
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public void adicionarProdutos(int tipo) {
		//Isso pega todos os produtos do banco de dados e adiciona na lista produtos
		Persistencia p = new Persistencia();
		p.beginTransaction();
		ProdutoDAO pgDAO = new ProdutoDAO(p.getEm());
		if(tipo==1) {
			setProdutos(pgDAO.findAll());
		}else {
			setProdutos(pgDAO.findAllOfVendedor());
		}
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
	
	
	public String sacola() {
		if(UsuarioLogado.getUsuario().getTipo()==1) {
			//Mostrar a sacola do usuario
			return "/primefaces/sacola.xhtml";
		}else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Sem permissão",  "Você não possui permissão para essa ação!") );
		}
		return null;
	}
	
	public String cadastrarNovoProduto() {
		if(UsuarioLogado.getUsuario().getTipo()==1) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Sem permissão",  "Você não possui permissão para essa ação!") );
		}else {
			return "/primefaces/cadastrarProduto.xhtml";
		}
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
