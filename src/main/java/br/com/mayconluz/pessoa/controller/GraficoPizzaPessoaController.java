package br.com.mayconluz.pessoa.controller;

import java.util.Hashtable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;

import br.com.mayconluz.repository.PessoaRepository;

/**
 *
 * @author Maycon Luz @Date 22 de nov de 2016 às 20:21:49
 * Classe tem como objetivo gerar o grafico dos registros cadastrados no banco
 */
@Named(value="graficoPizzaPessoaController")
@RequestScoped
public class GraficoPizzaPessoaController {

	@Inject
	private PessoaRepository pessoaRepository;


	private PieChartModel pieChartModel;

	public PieChartModel getPieChartModel() {
		return pieChartModel;
	}

	@PostConstruct
	public  void init(){

		this.pieChartModel = new PieChartModel();

		this.MontaGrafico();
	}

	/***
	 * apresenta o grafico na tela
	 */
	private void MontaGrafico(){


		//consulta na tabela os dados para gerar o grafico
		Hashtable<String, Integer> hashtableRegistros = pessoaRepository.GetOrigemPessoa();


		//informa os valores para gerar o grafico
		hashtableRegistros.forEach((chave,valor) -> {


			pieChartModel.set(chave, valor);

		});

		pieChartModel.setTitle("Total de Pessoas cadastrado por Tipo");
		pieChartModel.setShowDataLabels(true);
		pieChartModel.setLegendPosition("e");


	}
}
