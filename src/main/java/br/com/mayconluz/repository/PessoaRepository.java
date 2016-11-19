package br.com.mayconluz.repository;

import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.mayconluz.model.PessoaModel;
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
}
