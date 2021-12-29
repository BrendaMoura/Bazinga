package jsf.project.controller;


import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import jsf.project.DAO.UsuarioDAO;
import jsf.project.model.Usuario;
import jsf.project.util.Persistencia;
import jsf.project.util.UsuarioLogado;

@Named
@SessionScoped
@ManagedBean(name="usuarioBean")
public class UsuarioBean implements Serializable{
	//Essa classe interage com a view
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuario usuario = new Usuario();
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String cadastrarNovoUsuario() {
		return "/primefaces/cadastrarUsuario.xhtml";
	}
	
	public String cadastrar() {
		Persistencia p = new Persistencia();
		p.beginTransaction();
		UsuarioDAO pgDAO = new UsuarioDAO(p.getEm());
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		try{
			pgDAO.save(usuario);
			p.commit();
			p.close();
			
			//Loga o usuario
			UsuarioLogado.setUsuario(usuario);
			
			context.addMessage(null, new FacesMessage("Successful",  "Usuário cadastrado com sucesso!") );
			
			return "/primefaces/produtos.xhtml";
		}catch(Exception e) {
			context.addMessage(null, new FacesMessage("Unsuccessful",  "Erro ao cadastrar usuário!") );
		}
		return null;
	}
	
	public String excluir() {
		Persistencia p = new Persistencia();
		p.beginTransaction();
		UsuarioDAO pgDAO = new UsuarioDAO(p.getEm());
		pgDAO.delete(usuario);
		p.commit();
		p.close();
		
		return null;
	}
	
	public void alterar() {
		Persistencia p = new Persistencia();
		p.beginTransaction();
		UsuarioDAO pgDAO = new UsuarioDAO(p.getEm());
		pgDAO.update(usuario);
		p.commit();
		p.close();
		
	}
	
	public String entrar() {
		FacesContext context = FacesContext.getCurrentInstance();
		Persistencia p = new Persistencia();
		p.beginTransaction();
		UsuarioDAO pgDAO = new UsuarioDAO(p.getEm());
		int resultado = pgDAO.Login(usuario.getEmail(), usuario.getSenha());
		p.commit();
		p.close();
		
		if(resultado == 0) {
			//Adiciona uma mensagem na tela - popup
			context.addMessage(null, new FacesMessage("Unsuccessful",  "Usuario nao cadastrado!") );
		}
		else if(resultado==1) {
	        context.addMessage(null, new FacesMessage("Successful",  "Deu certo!") );
	        return "/primefaces/produtos.xhtml";
		}
		else {
			context.addMessage(null, new FacesMessage("Unsuccessful",  "Email e/ou senha incorretos") );
		}
		
		return null;
	}
	
	public String deslogar(){
		UsuarioLogado.setUsuario(null);
		return "/primefaces/index.xhtml";
	}
}
