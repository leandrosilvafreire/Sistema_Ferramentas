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
@Table(name = "tb_funcionario")
@NamedQueries({ @NamedQuery(name = "Funcionario.listar", query = "select funcionario from Funcionario funcionario"),
		@NamedQuery(name = "Funcionario.buscarPorCodigo", query = "select funcionario from Funcionario funcionario where funcionario.codigo = :codigo") })
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "fun_id")
	private Long codigo;

	@NotEmpty(message = "O campo nome é obrigatório!")
	@Size(min = 2, max = 50, message = "Informe um nome válido para o campo nome!")
	@Column(name = "fun_nome", length = 50, nullable = false)
	private String nome;

	// @CPF
	@NotEmpty(message = "O campo cpf é obrigatório!")
	@Column(name = "fun_cpf", length = 20, nullable = false, unique = true)
	private String cpf;

	@NotEmpty(message = "O campo senha é obrigatório!")
	@Size(min = 5, max = 20, message = "Tamanho inválido para o campo senha!")
	@Column(name = "fun_senha", length = 40, nullable = false)
	private String senha;

	@NotEmpty(message = "O campo função é obrigatório!")
	@Column(name = "fun_funcao", length = 50, nullable = false)
	private String funcao;

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	@Override
	public String toString() {
		return "Funcionario [codigo=" + codigo + ", nome=" + nome + ", cpf=" + cpf + ", senha=" + senha + ", funcao="
				+ funcao + "]";
	}

}
