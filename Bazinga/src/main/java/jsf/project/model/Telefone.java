package jsf.project.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Telefone implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idTelefone; 
	
	@Basic(optional=false)
	@Column(unique=true)
	private String telefone;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="CPF")
	private Usuario usuarioTelefone;

	public long getIdTelefone() {
		return idTelefone;
	}

	public void setIdTelefone(long idTelefone) {
		this.idTelefone = idTelefone;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Usuario getUsuarioTelefone() {
		return usuarioTelefone;
	}

	public void setUsuarioTelefone(Usuario usuarioTelefone) {
		this.usuarioTelefone = usuarioTelefone;
	}
	
	
}
