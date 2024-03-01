package br.com.screenmatch.principal;

import br.com.screenmatch.model.DadosSerie;
import br.com.screenmatch.model.DadosTemporada;
import br.com.screenmatch.model.Episodio;
import br.com.screenmatch.model.Serie;
import br.com.screenmatch.repository.SerieRepository;
import br.com.screenmatch.service.ConsumoAPI;
import br.com.screenmatch.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    //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
    private Scanner leitura = new Scanner(System.in);
    private ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

    private final String ENDERECO = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=60826722";

    private List<DadosSerie> dadosSeries = new ArrayList<>();

    private SerieRepository repositorio;

    private List<Serie> series = new ArrayList<>();

    public Principal(SerieRepository repositorio) {
        this.repositorio = repositorio;
    }


    public void exibeMenu(){
        while (true) {
            var menu = """
                 \n
                 1 - Buscar séries 
                 2 - Buscar episódios  
                 3 - Buscar historico de séries 
                 4 - Buscar série por titulo;
                 
                 0 - Sair 
                 
                 Escolha sua opção: 
                """;
            System.out.println(menu);
            var opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1 -> buscarSeriesWeb();
                case 2 -> buscarEpisodiosPorSerie();
                case 3 -> listarHistoricoSerie();
                case 4 -> buscarSeriePorTitulo();
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
        dadosSeries.add(dados);
        //Injeção de dependência
        Serie serie = new Serie(dados);

        //Parte responsável pela injeção no banco
        repositorio.save(serie);

        System.out.println(dados);
    }


    private void buscarEpisodiosPorSerie() {
        listarHistoricoSerie();
        System.out.println("Escolha uma série pelo nome: ");
        var nomeSerie = leitura.nextLine();


        Optional<Serie> serie = series.stream()
                .filter(s -> s.getTitulo().toLowerCase().contains(nomeSerie.toLowerCase()))
                .findFirst();

        if (serie.isPresent()){

            var serieEncontrada = serie.get();

            List<DadosTemporada> temporadas = new ArrayList<>();

            for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
                var json = consumo.obterDados(ENDERECO + serieEncontrada.getTitulo().replace(" ","+") + "&Season=" + i + API_KEY);
                DadosTemporada temporada = conversor.obterDados(json, DadosTemporada.class);
                temporadas.add(temporada);
            }
            temporadas.forEach(System.out::println);

            List<Episodio> episodios = temporadas.stream()
                    .flatMap(d -> d.episodios().stream()
                            .map(e -> new Episodio(d.numero(), e)))
                    .collect(Collectors.toList());
            serieEncontrada.setEpisodios(episodios);

            repositorio.save(serieEncontrada);
        } else {
            System.out.println("Série não encontrada!");
        }

    }

    private void listarHistoricoSerie() {
        series = repositorio.findAll();
        //findAll busca no banco todos os dados

        series.stream().sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }

    private void buscarSeriePorTitulo() {
        System.out.println("Escolha uma série pelo nome: ");

    }
}
