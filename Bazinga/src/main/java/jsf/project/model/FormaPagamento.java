package jsf.project.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class FormaPagamento implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idFormaPagamento;
	
	@Basic(optional = false)
	@Column
	private int formaPagamento;

	@OneToOne
	private Usuario usuarioFormaPagamento;
	
	public long getIdFormaPagamento() {
		return idFormaPagamento;
	}

	public void setIdFormaPagamento(long idFormaPagamento) {
		this.idFormaPagamento = idFormaPagamento;
	}

	public int getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(int formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public Usuario getUsuarioFormaPagamento() {
		return usuarioFormaPagamento;
	}

	public void setUsuarioFormaPagamento(Usuario usuarioFormaPagamento) {
		this.usuarioFormaPagamento = usuarioFormaPagamento;
	}
	
	
}
