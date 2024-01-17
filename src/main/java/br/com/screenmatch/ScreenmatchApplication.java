package br.com.screenmatch;

import br.com.screenmatch.service.ConsumoAPI;
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
		var json = consumoApi.obterDados("http://www.omdbapi.com/?t=gilmore+girls&Season=1&apikey=60826722");
		System.out.println(json);
		




	}
}
