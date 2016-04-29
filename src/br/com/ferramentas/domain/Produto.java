package br.com.ferramentas.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tb_produtos")
@NamedQueries({ @NamedQuery(name = "Produto.listar", query = "select produto from Produto produto"),
		@NamedQuery(name = "Produto.buscarPorCodigo", query = "select produto from Produto produto where produto.codigo = :codigo") })
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pro_id")
	private Long codigo;

	@NotEmpty(message = "O campo nome é obrigatório!")
	@Size(min = 2, max = 50, message = "Informe um nome válido para o campo nome!")
	@Column(name = "pro_nome", length = 50, nullable = false)
	private String nome;

	@NotNull(message = "O campo preço é obrigatório!")
	@DecimalMin(value = "0.01", message = "Informe um valor válido!")
	@DecimalMax(value = "999999.99", message = "Informe um valor válido!")
	@Column(name = "pro_preco", precision = 8, scale = 2, nullable = false)
	private BigDecimal preco;

	@NotNull(message = "O campo quantidade é obrigatório")
	@Min(value = 1, message = "Informe um valor válido para o campo quantidade!")
	@Max(value = 100000, message = "Informe um valor válido para o campo quantidade!")
	@Column(name = "pro_quantidade", nullable = false)
	private Integer quantidade;

	@NotNull(message="O campo fornecedor é obrigatório!")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "tb_fornecedor_for_id", referencedColumnName = "for_id")
	private Fornecedor fornecedor;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", nome=" + nome + ", preco=" + preco + ", quantidade=" + quantidade
				+ ", fornecedor=" + fornecedor + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	

}
