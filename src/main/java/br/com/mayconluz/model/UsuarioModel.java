package br.com.mayconluz.model;

/**
 *
 * @author Maycon Luz @Date 19 de nov de 2016 às 14:47:42
 * Classe utilizada pelo Managed Beans para receber os dados que sao informados nas paginas
 */
import java.io.Serializable;

public class UsuarioModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigo; // varivel codigo do tipo String
	private String usuario; // variavel usuario do tipo String
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
	 * retorna um objeto do tipo senha
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