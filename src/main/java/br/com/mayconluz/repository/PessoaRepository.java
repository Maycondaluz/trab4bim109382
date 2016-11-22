package br.com.mayconluz.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
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
 * @author Maycon Luz @Date 19 de nov de 2016 às 18:04:01 Classe responsavel por
 *         persistir o objeto na entidade PessoaEntity.
 */

public class PessoaRepository {

	@Inject // Realiza a injecao de dependencias.
	PessoaEntity pessoaEntity;

	EntityManager entityManager; // instancia o entityManager do tipo
									// EntityManager.

	/***
	 * Realiza a gravaçao de uma nova pessoa
	 *
	 * @param pessoaModel
	 */
	public void SalvarNovoRegistro(PessoaModel pessoaModel) {

		entityManager = Uteis.JpaEntityManager();
		/*
		 * criaçao de uma nova instancia do objeto PessoaEntity
		 */
		pessoaEntity = new PessoaEntity();
		/*
		 * atribui o objeto do tipo data para DataCadastro
		 */
		pessoaEntity.setDataCadastro(LocalDateTime.now());
		/*
		 * atriubui o objeto do tipo String para email
		 */
		pessoaEntity.setEmail(pessoaModel.getEmail());
		/*
		 * atriubui o objeto do tipo String para endereço
		 */
		pessoaEntity.setEndereco(pessoaModel.getEndereco());
		/*
		 * atriubui o objeto do tipo String para nome
		 */
		pessoaEntity.setNome(pessoaModel.getNome());
		/*
		 * atriubui o objeto do tipo String para OrigemCadastro
		 */
		pessoaEntity.setOrigemCadastro(pessoaModel.getOrigemCadastro());
		/*
		 * atriubui o objeto do tipo String para sexo
		 */
		pessoaEntity.setSexo(pessoaModel.getSexo());
		/*
		 * Guarda o objeto serializado na memoria
		 */
		UsuarioEntity usuarioEntity = entityManager.find(UsuarioEntity.class,
				pessoaModel.getUsuarioModel().getCodigo());
		/*
		 * atribui o objeto usuarioEntity
		 */
		pessoaEntity.setUsuarioEntity(usuarioEntity);
		/*
		 * persiste no banco de dados o objeto pessoaEntity
		 */
		entityManager.persist(pessoaEntity);

	}

	/***
	 * Método realiza a consulta da pessoa no banco de dados.
	 *
	 * @return
	 */
	public List<PessoaModel> GetPessoas() {

		List<PessoaModel> pessoasModel = new ArrayList<PessoaModel>();
		/*
		 * neste momento é o inicio da persistencia usando JPA
		 */
		entityManager = Uteis.JpaEntityManager();
		/*
		 * neste moemnto é passado o valor para query da consulta
		 */
		Query query = entityManager.createNamedQuery("PessoaEntity.findAll");
		@SuppressWarnings("unchecked")
		/*
		 * realiza a conversao da consulta para a collection do tipo PessoaModel
		 */
		Collection<PessoaEntity> pessoasEntity = (Collection<PessoaEntity>) query.getResultList();

		/*
		 * nesta parte somente é somente é isntanciado um objeto do tipo PessoaModel e atribuido o valor null
		 */
		PessoaModel pessoaModel = null;

		/**
		 * Neste momento é percorrido a Collection, e atribuido o valor para um
		 * objeto do tipor PessoaEntity
		 */
		for (PessoaEntity pessoaEntity : pessoasEntity) {
			/*
			 * instancia um objeto do tipo PessoaModel
			 */
			pessoaModel = new PessoaModel();
			/*
			 * atribui o valor da PessoaEntity para PessoaModel
			 */
			pessoaModel.setCodigo(pessoaEntity.getCodigo());
			/*
			 * atribui o valor do DataCadstro da PessoaEntity para DataCadastro da PessoaModel
			 */
			pessoaModel.setDataCadastro(pessoaEntity.getDataCadastro());
			/*
			 * atribui o valor do email da PessoaEntity para o email da PessoaModel
			 */
			pessoaModel.setEmail(pessoaEntity.getEmail());
			/*
			 * atribui o valor do email da PessoaEntity para o endereço da Pessoamodel
			 */
			pessoaModel.setEndereco(pessoaEntity.getEndereco());
			/*
			 * atribui o valor do email da PessoaEntity para  o nome da PessoaModel
			 */
			pessoaModel.setNome(pessoaEntity.getNome());

			/*
			 * neste momento é comparado se o cadastro é igual a X de XML caso
			 * nao seja é INPUT
			 */
			if (pessoaEntity.getOrigemCadastro().equals("X"))
				pessoaModel.setOrigemCadastro("XML");
			else
				pessoaModel.setOrigemCadastro("INPUT");

			/*
			 * Compara se o sexo informado é Masculino caso nao seja é atribuido
			 * Feminino
			 */
			if (pessoaEntity.getSexo().equals("M"))
				//atribui o valor "masculino" para o atributo sexo de PessoaModel
				pessoaModel.setSexo("Masculino");
			else
				/*
				 * atribui o valor "feminino" para o atributo sexo de PessoaModel
				 */
				pessoaModel.setSexo("Feminino");
			/*
			 * instancia da classe UsuarioEntity
			 */
			UsuarioEntity usuarioEntity = pessoaEntity.getUsuarioEntity();
			/*
			 * instancia um objeto do tipo UsuarioModel
			 */
			UsuarioModel usuarioModel = new UsuarioModel();
			/*
			 * seta o valor do usuario atual que esta requisitando o usuarioModel
			 */
			usuarioModel.setUsuario(usuarioEntity.getUsuario());

			pessoaModel.setUsuarioModel(usuarioModel);
			/*
			 * é adicionado na lista o objeto do tipo PessoaModel
			 */
			pessoasModel.add(pessoaModel);
		}
		/*
		 * nesta parte é retornado a lista contendo ou nao pessoa cadastrada
		 */
		return pessoasModel;

	}

	/**
	 * CONSULTA UMA PESSOA CADASTRADA PELO CÓDIGO
	 *
	 * @param codigo
	 *
	 * @return
	 */
	private PessoaEntity GetPessoa(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		return entityManager.find(PessoaEntity.class, codigo);
	}

	/**
	 * ALTERA UM REGISTRO CADASTRADO NO BANCO DE DADOS
	 *
	 * @param pessoaModel
	 */
	public void AlterarRegistro(PessoaModel pessoaModel) {

		entityManager = Uteis.JpaEntityManager();

		PessoaEntity pessoaEntity = this.GetPessoa(pessoaModel.getCodigo());

		pessoaEntity.setEmail(pessoaModel.getEmail());
		pessoaEntity.setEndereco(pessoaModel.getEndereco());
		pessoaEntity.setNome(pessoaModel.getNome());
		pessoaEntity.setSexo(pessoaModel.getSexo());

		entityManager.merge(pessoaEntity);
	}

	/*
	 * exclui um registro do banco de dados.
	 *
	 * @param codigo
	 */
	public void ExcluirRegistro(int codigo) {

		entityManager = Uteis.JpaEntityManager();

		PessoaEntity pessoaEntity = this.GetPessoa(codigo);

		entityManager.remove(pessoaEntity);
	}

	/***
	 * este metodo tem por objetivo de retornas os tipos de pessoas agrupados
	 *
	 * @return
	 */
	public Hashtable<String, Integer> GetOrigemPessoa() {

		Hashtable<String, Integer> hashtableRegistros = new Hashtable<String, Integer>();

		entityManager = Uteis.JpaEntityManager(); // inicia uma conexao com persistencia JPA

		Query query = entityManager.createNamedQuery("PessoaEntity.GroupByOrigemCadastro");

		@SuppressWarnings("unchecked")
		Collection<Object[]> collectionRegistros = (Collection<Object[]>) query.getResultList();

		/**
		 * Percorre a tabela pessoa
		 */
		for (Object[] objects : collectionRegistros) {

			String tipoPessoa = (String) objects[0];
			int totalDeRegistros = ((Number) objects[1]).intValue();

			if (tipoPessoa.equals("X"))
				tipoPessoa = "XML";
			else
				tipoPessoa = "INPUT";

			hashtableRegistros.put(tipoPessoa, totalDeRegistros);

		}
		// retorna a lista com os valores armazenados
		return hashtableRegistros;

	}

}