package br.com.screenmatch;

import br.com.screenmatch.model.DadosEpisodio;
import br.com.screenmatch.model.DadosSerie;
import br.com.screenmatch.model.DadosTemporada;
import br.com.screenmatch.service.ConsumoAPI;
import br.com.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {
	// "CommandLineRunner" é uma interface que implementa o uso da linha de comando no sprongboot

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	// Run se torna o método "main"
	@Override
	public void run(String... args) throws Exception {
        var consumoApi = new ConsumoAPI();
		var json = consumoApi.obterDados("http://www.omdbapi.com/?t=gilmore+girls&apikey=60826722");
		System.out.println(json);

		var conversor = new ConverteDados();
		var dados = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dados);


        json = consumoApi.obterDados("http://www.omdbapi.com/?t=gilmore+girls&season=2&episode=1&apikey=60826722");
        DadosEpisodio dadosEpisodio = conversor.obterDados(json,DadosEpisodio.class);
        System.out.println(dadosEpisodio);

		List<DadosTemporada> temporadas = new ArrayList<>();

		for (int i = 1; i<=dados.totalTemporadas(); i++) {

			json = consumoApi.obterDados("http://www.omdbapi.com/?t=gilmore+girls&season="+ i + "&apikey=60826722");
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);

		}

		temporadas.forEach(System.out::println);



	}
}
