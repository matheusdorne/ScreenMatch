package br.com.screenmatch;

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
		System.out.println("Primeiro Projeto Spring");

	}
}
