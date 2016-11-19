package br.com.mayconluz.repository.entity;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name="tb_usuario")// nome da tabela no banco de dados
@Entity // declaração da entidade

/*
 * query onde o usuario sera autenticado no banco
 */
@NamedQuery(name = "UsuarioEntity.findUser",
		    query= "SELECT u FROM UsuarioEntity u WHERE u.usuario = :usuario AND u.senha = :senha")

/***
*
* @author Maycon Luz @Date 19 de nov de 2016 às 14:18:54
*
* Esta classe é a entidade para persistir a nossa tabela de usuarios no banco de dados
* esta classe tambem trabalha com a arquitetura JPA.
*
*/

public class UsuarioEntity implements Serializable {

	private static final long serialVersionUID = 1L; //versao da classe

	@Id // annotation para identificar a primaey key da tabela
	@GeneratedValue // uma chave é gerada toda vez que for inserido algo no banco
	@Column(name="id_usuario")// identificaçao da coluna no banco referente a ID do usuario
	private String codigo; // variavel codigo do tipo String

	@Column(name="ds_login")//nome da coluna login do banco
	private String usuario; // varivael usuario do tipo String

	@Column(name="ds_senha") // nome da coluna senha do banco
	private String senha; // variavel senha do tipo String

	/*
	 * retorna um objeto do tipo codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/*
	 * é passado por paramentro o valor do objeto da classe
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/*
	 * retorna um objeto do tipo usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/*
	 * é passado por parametro o valor do objeto da classe
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/*
	 retorna um objeto do tipo senha
	 */
	public String getSenha() {
		return senha;
	}

	/*
	 * é passado por parametro o valor do objeto da classe
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

}
