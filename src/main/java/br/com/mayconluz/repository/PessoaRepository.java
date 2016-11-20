package br.com.mayconluz.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.mayconluz.model.PessoaModel;
import br.com.mayconluz.model.UsuarioModel;
import br.com.mayconluz.repository.entity.PessoaEntity;
import br.com.mayconluz.repository.entity.UsuarioEntity;
import br.com.mayconluz.uteis.Uteis;


 /**
  *
  * @author Maycon Luz @Date 19 de nov de 2016 às 18:04:01
  * Classe responsavel por persistir o objeto na entidade PessoaEntity.
  */

public class PessoaRepository {

	@Inject // Realiza a injecao de dependencias.
	PessoaEntity pessoaEntity;

	EntityManager entityManager; // instancia o entityManager do tipo EntityManager.

	/***
	 * Realiza a gravaçao de uma nova pessoa
	 * @param pessoaModel
	 */
	public void SalvarNovoRegistro(PessoaModel pessoaModel){

		entityManager =  Uteis.JpaEntityManager();

		pessoaEntity = new PessoaEntity(); //criaçao de uma nova instancia do objeto PessoaEntity
		pessoaEntity.setDataCadastro(LocalDateTime.now());// atribui o objeto do tipo data para DataCadastro
		pessoaEntity.setEmail(pessoaModel.getEmail());  // atribui o objeto do tipo String para email
		pessoaEntity.setEndereco(pessoaModel.getEndereco()); // atribui o objeto do tipo String para endereço
		pessoaEntity.setNome(pessoaModel.getNome()); // atribui o objeto do tipo String para nome
		pessoaEntity.setOrigemCadastro(pessoaModel.getOrigemCadastro()); // atribui o objeto do tipo String para OrigemCadastro
		pessoaEntity.setSexo(pessoaModel.getSexo()); // atribui o objeto do tipo String para sexo

		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class, pessoaModel.getUsuarioModel().getCodigo()); // guarda o objeto serializado na memoria

		pessoaEntity.setUsuarioEntity(usuarioEntity);// atriui o objeto usuarioEntity

		entityManager.persist(pessoaEntity); // persiste no banco de dados o objeto pessoaEntity.

	}

	/***
	 * Método realiza a consulta da pessoa no banco de dados.
	 * @return
	 */
	public List<PessoaModel> GetPessoas(){

		List<PessoaModel> pessoasModel = new ArrayList<PessoaModel>(); // neste momento é instanciado um list do tipo ArrayList<PessoaModel>

		entityManager =  Uteis.JpaEntityManager(); // neste momento é o inicio da persistencia usando JPA

		Query query = entityManager.createNamedQuery("PessoaEntity.findAll");// neste momento é passado o valor para query da consulta

		@SuppressWarnings("unchecked")
		Collection<PessoaEntity> pessoasEntity = (Collection<PessoaEntity>)query.getResultList(); // realiza a conversaro da consulta para a collection do tipo PessoaModel

		PessoaModel pessoaModel = null; // nesta parte somente é instanciado um objeto do tipo PessoaModel e atribuido valor Null.

		/*
		 * Neste momento é percorrido a Collection, e atribuido o valor para um objeto do tipor PessoaEntity
		 */
		for (PessoaEntity pessoaEntity : pessoasEntity) {

			pessoaModel = new PessoaModel(); // instancia um objeto do tipo PessoaModel
			pessoaModel.setCodigo(pessoaEntity.getCodigo()); // atribui o valor da PessoaEntity para PessoaModel
			pessoaModel.setDataCadastro(pessoaEntity.getDataCadastro()); // atribui o valor do DataCadastro da PessoaEntitu para DataCadastro da PessoaModel
			pessoaModel.setEmail(pessoaEntity.getEmail());// atribui o valor do email da PessoaEntity para o email da PessoaModel
			pessoaModel.setEndereco(pessoaEntity.getEndereco()); // atribui o valor do endereço armazenado na pessoaEntity para o endereco da PessoaModel
			pessoaModel.setNome(pessoaEntity.getNome());// atribui o valor do nome contido na pessoaEntity para o nome da pessoaModel.

			/*
			 * neste momento é comparado se o cadastro é igual a X de XML caso nao seja é INPUT
			 */
			if(pessoaEntity.getOrigemCadastro().equals("X"))
				pessoaModel.setOrigemCadastro("XML");
			else
				pessoaModel.setOrigemCadastro("INPUT");


			/*
			 * Compara se o sexo informado é Masculino caso nao seja é atribuido Feminino
			 */
			if(pessoaEntity.getSexo().equals("M"))
			pessoaModel.setSexo("Masculino"); // atribui o valor "Masculino" para o atributo sexo de PessoaModel
			else
				pessoaModel.setSexo("Feminino"); // atribui o Valaor "Feminino" para o atributo sexo de PessoaModel


			UsuarioEntity usuarioEntity =  pessoaEntity.getUsuarioEntity();// instancia da clase Usuario Entity

			UsuarioModel usuarioModel = new UsuarioModel(); // instancia um objeto do tipo UsuarioModel
			usuarioModel.setUsuario(usuarioEntity.getUsuario()); // seta o valor do usuario atual que esta requisitando o usuarioModel

			pessoaModel.setUsuarioModel(usuarioModel); // nesta parte é passado o usuarioEntity para o usuarioModel

			pessoasModel.add(pessoaModel); // é adicionado na lista o objeto do tipo PessoaModel
		}

		return pessoasModel; // nesta parte é retornado a lista contendo ou nao pessoas cadastradas.

	}

	/*
	 * CONSULTA UMA PESSOA CADASTRADA PELO CÓDIGO
	 * @param codigo
	 * @return
	 */
	private PessoaEntity GetPessoa(int codigo){

		entityManager =  Uteis.JpaEntityManager();

		return entityManager.find(PessoaEntity.class, codigo);
	}

	/*
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 * @param pessoaModel
	 */
	public void AlterarRegistro(PessoaModel pessoaModel){

		entityManager =  Uteis.JpaEntityManager();

		PessoaEntity pessoaEntity = this.GetPessoa(pessoaModel.getCodigo());

		pessoaEntity.setEmail(pessoaModel.getEmail());
		pessoaEntity.setEndereco(pessoaModel.getEndereco());
		pessoaEntity.setNome(pessoaModel.getNome());
		pessoaEntity.setSexo(pessoaModel.getSexo());

		entityManager.merge(pessoaEntity);
	}
}
