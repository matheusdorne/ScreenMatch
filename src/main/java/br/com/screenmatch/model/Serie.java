package br.com.screenmatch.model;

import java.util.OptionalDouble;

public class Serie {
    private String titulo;
    private Integer totalTemporadas;
    private double avaliacao;
    private Categoria genero;
    private String sinopse;
    private String poster;
    private String atores;

    public Serie (DadosSerie dadosSerie){
        this.titulo = dadosSerie.titulo();
        this.totalTemporadas = dadosSerie.totalTemporadas();
        this.avaliacao = OptionalDouble.of(Double.valueOf(dadosSerie.avaliacao())).orElse(0);
        this.genero = Categoria.fromString(dadosSerie.genero().split(",")[0].trim());
        // split definido para separa pela virgula e o [0] indica pegar o primeiro valor
        // trim busca apenas caractere ignorando os espaços
        this.sinopse = dadosSerie.sinopse();
        this.poster = dadosSerie.poster();
        this.atores = dadosSerie.atores();




    }
}
