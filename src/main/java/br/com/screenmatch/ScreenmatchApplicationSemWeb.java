package br.com.screenmatch;

import br.com.screenmatch.principal.Principal;
import br.com.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
//public class ScreenmatchApplicationSemWeb implements CommandLineRunner {
//	// "CommandLineRunner" é uma interface que implementa o uso da linha de comando no sprongboot
//	@Autowired
//	private SerieRepository repositorio;
//	//Foi declarado aqui para seu gerenciamento ser responsavel pelo Spring
//	//
//
//	public static void main(String[] args) {
//		SpringApplication.run(ScreenmatchApplicationSemWeb.class, args);
//	}
//
//
//	// Run se torna o método "main"
//	@Override
//	public void run(String... args) throws Exception {
//		Principal principal = new Principal(repositorio);
//		//Aqui estamos lançando o repositório para dentro do Principal para ser usado
//		principal.exibeMenu();
//	}
//}
