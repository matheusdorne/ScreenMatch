package br.com.screenmatch.principal;

import br.com.screenmatch.model.*;
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
                 1 - Buscar Séries 
                 2 - Buscar Episódios  
                 3 - Buscar Historico De Séries 
                 4 - Buscar Série Por Titulo 
                 5 - Buscar Série Por Ator 
                 6 - Top 5 Séries 
                 7 - Buscar Séries Por Categoria 
                 8 - Buscar por Máximo de Temporada e Availiaçao Mínima
                 
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
                case 5 -> buscarSeriePorAtor();
                case 6 -> buscarTop5Series();
                case 7 -> buscarSeriesPorCategoria();
                case 8 -> buscarTotalTemporadasEAvaliacao();
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


        Optional<Serie> serie = repositorio.findByTituloContainingIgnoreCase(nomeSerie);

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
        var nomeSerie = leitura.nextLine();
        Optional<Serie> serieBuscada = repositorio.findByTituloContainingIgnoreCase(nomeSerie);

        if (serieBuscada.isPresent()) {
            System.out.println("Dados da série: " + serieBuscada.get());

        } else {
            System.out.println("Série não encontrada!");
        }

    }

    private void buscarSeriePorAtor() {

        System.out.println("Qual o nome para busca?");
        var nomeAtor = leitura.nextLine();
        System.out.println("A partir de que avaliação?");
        var avaliacao = leitura.nextDouble();
        leitura.nextLine();
        List<Serie> seriesEncontradas = repositorio.findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(nomeAtor,avaliacao);
        System.out.println("Séries que " + nomeAtor + "participou" );
        seriesEncontradas.stream().forEach(s -> System.out.println(s.getTitulo() + " - Availiação: " + s.getAvaliacao()));
    }

    private void buscarTop5Series() {

        List<Serie>  serieTop = repositorio.findTop5ByOrderByAvaliacaoDesc();
        serieTop.forEach(s -> System.out.println(s.getTitulo() + " - Availiação: " + s.getAvaliacao()));
    }

    private void buscarSeriesPorCategoria() {
        System.out.println("Buscar por qual categoria?");
        var nomeGenero = leitura.nextLine();
        Categoria categoria = Categoria.fromPortugues(nomeGenero);
        List<Serie> seriesPorCategoria = repositorio.findByGenero(categoria);
        System.out.println("Séries da categoria " + categoria);
        seriesPorCategoria.forEach(s -> System.out.println(s.getTitulo() + " - Availiação: " + s.getAvaliacao()));
    }

    private void buscarTotalTemporadasEAvaliacao() {
        System.out.println("Buscar por quantas temporadas?");
        var numeroTemporadas = leitura.nextInt();
        leitura.nextLine();
        System.out.println("Buscar por avaliação maior ou igual a:");
        var avaliacao = leitura.nextDouble();
        leitura.nextLine();
        List<Serie> seriesPorTemporadasEAvaliacao = repositorio.seriesPorTemporadaEAvaliacao(numeroTemporadas,avaliacao);
        System.out.println("Séries com até " + numeroTemporadas + " temporadas e avaliação maior ou igual a " + avaliacao);
        seriesPorTemporadasEAvaliacao.forEach(s -> System.out.println(s.getTitulo() + " - Availiação: " + s.getAvaliacao()));

    }




}
