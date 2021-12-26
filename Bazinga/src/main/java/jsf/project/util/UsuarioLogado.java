package jsf.project.util;

import jsf.project.model.Usuario;

public class UsuarioLogado {
	
	private static Usuario usuario;
	
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
