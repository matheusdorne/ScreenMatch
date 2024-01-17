package br.com.screenmatch;

import br.com.screenmatch.model.DadosSerie;
import br.com.screenmatch.service.ConsumoAPI;
import br.com.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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





	}
}
