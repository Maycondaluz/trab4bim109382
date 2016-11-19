package br.com.mayconluz.model;

import java.time.LocalDateTime;


/**
 *
 * @author Maycon Luz @Date 19 de nov de 2016 às 17:41:43
 * Classe de modelo do Objeto Pessoa.
 */
public class PessoaModel {

	private Integer codigo;
	private String nome;
	private String sexo;
	private LocalDateTime dataCadastro;
	private String email;
	private String endereco;
	private String origemCadastro;
	private UsuarioModel usuarioModel;

	/*
	 * retorna o valor do objeto codigo.
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/*
	 * é passado por paramentro o valor do objeto da classe
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/*
	 * retorna o valor do objeto nome.
	 */
	public String getNome() {
		return nome;
	}
	/*
	 * é passado por paramentro o valor do objeto da classe
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/*
	 * retorna o valor do objeto sexo.
	 */
	public String getSexo() {
		return sexo;
	}
	/*
	 * é passado por paramentro o valor do objeto da classe
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	/*
	 * retorna o valor do objeto dataCadastro.
	 */
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}
	/*
	 * é passado por paramentro o valor do objeto da classe
	 */
	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	/*
	 * retorna o valor do objeto email.
	 */
	public String getEmail() {
		return email;
	}
	/*
	 * é passado por paramentro o valor do objeto da classe
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/*
	 * retorna o valor do objeto endereco.
	 */
	public String getEndereco() {
		return endereco;
	}
	/*
	 * é passado por paramentro o valor do objeto da classe
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	/*
	 * retorna o valor do objeto origemCadastro
	 */
	public String getOrigemCadastro() {
		return origemCadastro;
	}
	/*
	 * é passado por paramentro o valor do objeto da classe
	 */
	public void setOrigemCadastro(String origemCadastro) {
		this.origemCadastro = origemCadastro;
	}
	/*
	 * retorna o valor do objeto usuarioModel
	 */

	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}
	/*
	 * é passado por paramentro o valor do objeto da classe
	 */
	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

}
