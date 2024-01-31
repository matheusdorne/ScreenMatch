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



    public void exibeMenu(){
        System.out.println("Série para pesquisa:");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ","+") + API_KEY);
        var dados = conversor.obterDados(json, DadosSerie.class);

//        System.out.println(dados);

        		List<DadosTemporada> temporadas = new ArrayList<>();

		for (int i = 1; i<=dados.totalTemporadas(); i++) {

			json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ","+") + "&season=" + i + API_KEY);
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);

		}
//		temporadas.forEach(System.out::println);

//        for(int i = 0; i < dados.totalTemporadas(); i++ ) {
//            List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
//            for (int j = 0; j < episodiosTemporada.size(); j++ ) {
//                System.out.println(episodiosTemporada.get(j).titulo());
//
//            }
//
//        }
//      Utilizando LAMBDA


//        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

//    List<String> nomes = Arrays.asList("Jacque", "Iasmin", "Paulo","Rodrigo", "Nico");
//
//    nomes.stream()
//            .sorted()
//            .limit(3)
//            .filter(n -> n.startsWith("N"))
//            .map(n -> n.toUpperCase())
//            .forEach(System.out::println);


        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());
//Collectors = Cria um conjunto que pode ser alterado.
// toList = Cria um conjunto imútavel

        System.out.println("\n Top 10 episódios melhores avaliadas:");
        dadosEpisodios.stream()
                .filter(e -> ! e.avaliacao().equalsIgnoreCase("N/A"))
                .peek(e -> System.out.println("Primeiro filtro (N/A) " + e))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .peek(e -> System.out.println("Ordenação " + e))
                //Sorted().reversed() = Seleciona de forma decrescente
                .limit(10)
                .peek(e -> System.out.println("Limitador " + e))
                .map(e -> e.titulo().toUpperCase())
                .peek(e -> System.out.println("Mapeamento " + e))
                .forEach(System.out::println);

        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d))
                ).collect(Collectors.toList());

//       episodios.forEach(System.out::println);

        System.out.println("Visualizar a partir do ano:");
        var ano = leitura.nextInt();
        leitura.nextLine();

        LocalDate dataBusca = LocalDate.of(ano,1,1);

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        episodios.stream().filter(e -> e != null || e.getDataLancamento()
                .isAfter(dataBusca))
                .forEach(e -> System.out.println(
                        "Temporada: " + e.getTemporada() +
                                ", Episódio: " + e.getTitulo() +
                                ", Data Lançamento: " + e.getDataLancamento().format(formatador)
                ));

    }
}
