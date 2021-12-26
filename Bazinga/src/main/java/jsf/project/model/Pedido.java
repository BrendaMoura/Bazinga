package jsf.project.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Pedido implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idPedido;
	
	@Column
	private int quantidade;
	
	@OneToOne
	private Produto produtoPedido;
	
	@ManyToOne
	private Sacola sacola;

	public Sacola getSacola() {
		return sacola;
	}

	public void setSacola(Sacola sacola) {
		this.sacola = sacola;
	}

	public long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProdutoPedido() {
		return produtoPedido;
	}

	public void setProdutoPedido(Produto produtoPedido) {
		this.produtoPedido = produtoPedido;
	}

}
