package br.com.ferramentas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "tb_fornecedor")
@NamedQueries({ @NamedQuery(name = "Fornecedor.listar", query = "select fornecedor from Fornecedor fornecedor"),
		@NamedQuery(name = "Fornecedor.buscarPorCodigo", query = "select fornecedor from Fornecedor fornecedor where fornecedor.codigo = :codigo") })
public class Fornecedor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "for_id")
	private Long codigo;

	@NotEmpty(message = "O campo nome é obrigatório!")
	@Size(min = 2, max = 50, message = "Informe um nome válido para o campo nome!")
	@Column(name = "for_nome", length = 50, nullable = false)
	private String nome;

	//@CNPJ(message = "CNPJ inválido!")
	@NotEmpty(message="O campo cnpj é obrigatório!")
	@Column(name = "for_cnpj", length = 25, unique = true)
	private String cnpj;

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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public String toString() {
		return "Fornecedor [codigo=" + codigo + ", nome=" + nome + ", cnpj=" + cnpj + "]";
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
		Fornecedor other = (Fornecedor) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	

}
