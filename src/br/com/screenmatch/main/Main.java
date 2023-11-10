package br.com.screenmatch.main;

import br.com.screenmatch.calculos.CalculadoraDeTempo;
import br.com.screenmatch.calculos.FiltroRecomendacao;
import br.com.screenmatch.modelos.Episodio;
import br.com.screenmatch.modelos.Filme;
import br.com.screenmatch.modelos.Serie;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Filme meuFilme = new Filme("Poderoso Chefão", 1970);
       // meuFilme.setNome("O poderoso chefão");
        // meuFilme.setAnoDeLancamento(1970);
        meuFilme.setDuracaoEmMinutos(180);
        System.out.println("Duracao em minutos: " + meuFilme.getDuracaoEmMinutos());





        meuFilme.exibeFichaTecnica();
        meuFilme.avalia(8);
        meuFilme.avalia(5);
        meuFilme.avalia(10);
        System.out.println(meuFilme.getSomaDasAvaliacoes());
        System.out.println(meuFilme.getTotalDeAvaliacoes());
        System.out.println(meuFilme.pegaMedia());


        Serie lost = new Serie("Lost",2004);
        //lost.setNome("Lost");
        //lost.setAnoDeLancamento(2004);
        lost.exibeFichaTecnica();
        lost.setTemporadas(10);
        lost.setEpisodiosPorTemporada(10);
        lost.setMinutosPorEpisodio(50);
        System.out.println("Duracao em minutos: " + lost.getDuracaoEmMinutos());

        Filme outroFilme = new Filme("Clube da Luta", 1999);
        //outroFilme.setNome("Clube da Luta");
        //outroFilme.setAnoDeLancamento(1999);
        outroFilme.setDuracaoEmMinutos(139);


        CalculadoraDeTempo calc = new CalculadoraDeTempo();
        calc.inclui(meuFilme);
        calc.inclui(outroFilme);
        calc.inclui(lost);
        System.out.println(calc.getTempoTotal());

        FiltroRecomendacao filtro = new FiltroRecomendacao();
        filtro.filtra(meuFilme);

        Episodio episodio = new Episodio();
        episodio.setNumero(1);
        episodio.setSerie(lost);
        episodio.setTotalVisualizacoes(100);
        filtro.filtra(episodio);


        var filmeDoMatheus = new Filme("Shrek", 2001);
        //filmeDoMatheus.setNome("Shrek");
        //filmeDoMatheus.setAnoDeLancamento(2001);
        filmeDoMatheus.setDuracaoEmMinutos(90);
        filmeDoMatheus.avalia(10);

        ArrayList<Filme> listadeFilmes = new ArrayList<>();
        listadeFilmes.add(filmeDoMatheus);
        listadeFilmes.add(meuFilme);
        listadeFilmes.add(outroFilme);

        System.out.println("Tamanho da lista: " + listadeFilmes.size());
        System.out.println("Primeiro Filme: " + listadeFilmes.get(0).getNome());
        System.out.println(listadeFilmes);

       var novissimoFilme = new Filme("Dog Ville", 2003);





    }
}