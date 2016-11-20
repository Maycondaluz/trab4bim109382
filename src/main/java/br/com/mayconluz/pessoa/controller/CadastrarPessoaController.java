package br.com.mayconluz.pessoa.controller;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.primefaces.model.UploadedFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.com.mayconluz.model.PessoaModel;
import br.com.mayconluz.repository.PessoaRepository;
import br.com.mayconluz.usuario.controller.UsuarioController;
import br.com.mayconluz.uteis.Uteis;

@Named(value="cadastrarPessoaController")// annotation que muda a classe para um bean gerenciado pelo CDI
@RequestScoped// especifica que o bean é um escopo de solicitaçao.

/**
*
* @author Maycon Luz @Date 19 de nov de 2016 às 17:50:09
* Classe que controla o cadastro de Pessoa
*/
public class CadastrarPessoaController {

	@Inject // realiza a injecao de dependencia no bean
	PessoaModel pessoaModel;

	@Inject // realiza a injecao de dependencia no bean
	UsuarioController usuarioController;

	@Inject // realiza a injecao de dependencia no bean
	PessoaRepository pessoaRepository;


	private UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	/*
	 * retorna um objeto do tipo PessoaModel
	 */
	public PessoaModel getPessoaModel() {
		return pessoaModel;
	}

	/*
	 * é passado por parametro o valor do objeto da classe
	 */
	public void setPessoaModel(PessoaModel pessoaModel) {
		this.pessoaModel = pessoaModel;
	}

	/**
	 *Realiza o salvamento de um novo registro via input
	 */
	public void SalvarNovaPessoa(){

		pessoaModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());

		//informa que o cadastro foi via input
		pessoaModel.setOrigemCadastro("I");

		pessoaRepository.SalvarNovoRegistro(this.pessoaModel);

		this.pessoaModel = null;

		Uteis.MensagemInfo("Registro cadastrado com sucesso");// apresenta a mensagem na tela caso a operaçao realizada com sucesso.

	}

	/**
	 * REALIZANDO UPLOAD DE ARQUIVO
	 */
	 public void UploadRegistros() {

		 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		 try {


			 if(this.file.getFileName().equals("")){
				 Uteis.MensagemAtencao("Nenhum arquivo selecionado!");
				 return;
			 }

			 DocumentBuilder builder = factory.newDocumentBuilder();

	                 Document doc = builder.parse(this.file.getInputstream());

	                 Element element = doc.getDocumentElement();

	                 NodeList nodes = element.getChildNodes();

	                for (int i = 0; i < nodes.getLength(); i++) {

	        	     Node node  = nodes.item(i);

	        	    if(node.getNodeType() == Node.ELEMENT_NODE){

	        		 Element elementPessoa =(Element) node;

	        		 //PEGANDO OS VALORES DO ARQUIVO XML
	        		 String nome     = elementPessoa.getElementsByTagName("nome").item(0).getChildNodes().item(0).getNodeValue();
	        		 String sexo     = elementPessoa.getElementsByTagName("sexo").item(0).getChildNodes().item(0).getNodeValue();
	        		 String email    = elementPessoa.getElementsByTagName("email").item(0).getChildNodes().item(0).getNodeValue();
	        		 String endereco = elementPessoa.getElementsByTagName("endereco").item(0).getChildNodes().item(0).getNodeValue();

	        		 PessoaModel newPessoaModel = new PessoaModel();

	        		 newPessoaModel.setUsuarioModel(this.usuarioController.GetUsuarioSession());
	        		 newPessoaModel.setEmail(email);
	        		 newPessoaModel.setEndereco(endereco);
	        		 newPessoaModel.setNome(nome);
	        		 newPessoaModel.setOrigemCadastro("X");
	        		 newPessoaModel.setSexo(sexo);

	        		 //SALVANDO UM REGISTRO QUE VEIO DO ARQUIVO XML
	        		 pessoaRepository.SalvarNovoRegistro(newPessoaModel);
	        	   }
	              }



		     Uteis.MensagemInfo("Registros cadastrados com sucesso!");

		} catch (ParserConfigurationException e) {

			e.printStackTrace();

		} catch (SAXException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}


	 }

}