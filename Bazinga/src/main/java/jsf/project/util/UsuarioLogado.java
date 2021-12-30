package jsf.project.util;

import jsf.project.model.Produto;
import jsf.project.model.Sacola;
import jsf.project.model.Usuario;

public class UsuarioLogado {
	
	private static Usuario usuario;
	private static Sacola sacola;
	private static Produto produto;
	
	public static Produto getProduto() {
		return produto;
	}

	public static void setProduto(Produto produto) {
		UsuarioLogado.produto = produto;
	}

	public static Sacola getSacola() {
		return sacola;
	}

	public static void setSacola(Sacola sacola) {
		UsuarioLogado.sacola = sacola;
	}

	public static Usuario getUsuario() {
		return usuario;
	}

	public static void setUsuario(Usuario usuario) {
		UsuarioLogado.usuario = usuario;
	}

	public UsuarioLogado() {
		// TODO Auto-generated constructor stub
	}

}
