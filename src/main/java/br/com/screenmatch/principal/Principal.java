package br.com.screenmatch.principal;

import br.com.screenmatch.model.DadosEpisodio;
import br.com.screenmatch.model.DadosSerie;
import br.com.screenmatch.model.DadosTemporada;
import br.com.screenmatch.model.Episodio;
import br.com.screenmatch.service.ConsumoAPI;
import br.com.screenmatch.service.ConverteDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=60826722";

    private List<DadosSerie> series = new ArrayList<>();



    public void exibeMenu(){
        while (true) {
            var menu = """
                 \n
                 1 - Buscar séries 
                 2 - Buscar episódios 
                 
                 0 - Sair 
                 
                 Escolha sua opção: 
                """;
            System.out.println(menu);
            var opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1 -> buscarSeriesWeb();
                case 2 -> buscarEpisodiosPorSerie();
                case 0 -> System.exit(0);
                default -> System.out.println("Opção inválida");
            }
        }
    }

    private DadosSerie getDadosSerie() {
        System.out.println("\nSérie para pesquisa:");

        var nomeSerie = leitura.nextLine();
        System.out.println(nomeSerie);

        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ","+") + API_KEY);
        DadosSerie dados =  conversor.obterDados(json, DadosSerie.class);
        return dados;
    }

    private void buscarSeriesWeb() {
        DadosSerie dados = getDadosSerie();
        System.out.println(dados);
    }


    private void buscarEpisodiosPorSerie() {
        DadosSerie dadosSerie = getDadosSerie();
        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
            var json = consumo.obterDados(ENDERECO + dadosSerie.titulo().replace(" ","+") + "&Season=" + i + API_KEY);
            DadosTemporada temporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(temporada);
        }
        temporadas.forEach(System.out::println);
    }
}
