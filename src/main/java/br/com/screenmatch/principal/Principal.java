package br.com.screenmatch.principal;

import br.com.screenmatch.service.ConsumoAPI;

import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();

    private final String ENDERECO = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=60826722";


    public void exibeMenu(){
        System.out.println("Série para pesquisa:");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ","_") + API_KEY);

        // "http://www.omdbapi.com/?t=gilmore+girls&season="+ i + "&apikey=60826722"


    }
}
