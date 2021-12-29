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
public class Pedido implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idPedido;
	
	@Column
	private int quantidade;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "idPedido")
	private Produto produtoPedido;

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
