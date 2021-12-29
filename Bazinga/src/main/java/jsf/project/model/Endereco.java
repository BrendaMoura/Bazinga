package jsf.project.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Endereco implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idEndereco;
	
	@Column
	private String rua;
	
	@Column
	private String bairro;
	
	@Column
	private String CEP;
		
	@Column
	private String numero;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="CPF")
	private Usuario usuarioMorador;

	public long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(long idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cEP) {
		CEP = cEP;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Usuario getUsuarioMorador() {
		return usuarioMorador;
	}

	public void setUsuarioMorador(Usuario usuarioMorador) {
		this.usuarioMorador = usuarioMorador;
	}
	
	

}
