package br.com.screenmatch.model;

import br.com.screenmatch.service.ConsultaChatGPT;

import java.io.IOException;
import java.util.OptionalDouble;

public class Serie {
    private String titulo;
    private Integer totalTemporadas;
    private double avaliacao;
    private Categoria genero;
    private String sinopse;
    private String poster;
    private String atores;

    public Serie (DadosSerie dadosSerie) {
        this.titulo = dadosSerie.titulo();
        this.totalTemporadas = dadosSerie.totalTemporadas();
        this.avaliacao = OptionalDouble.of(Double.valueOf(dadosSerie.avaliacao())).orElse(0);
        this.genero = Categoria.fromString(dadosSerie.genero().split(",")[0].trim());
        // split definido para separa pela virgula e o [0] indica pegar o primeiro valor
        // trim busca apenas caractere ignorando os espaços
        this.sinopse = ConsultaChatGPT.obterTraducao(dadosSerie.sinopse().trim());
        this.poster = dadosSerie.poster();
        this.atores = dadosSerie.atores();

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTotalTemporadas() {
        return totalTemporadas;
    }

    public void setTotalTemporadas(Integer totalTemporadas) {
        this.totalTemporadas = totalTemporadas;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getAtores() {
        return atores;
    }

    public void setAtores(String atores) {
        this.atores = atores;
    }

    @Override
    public String toString() {
        return
                "\nGenero= " + genero +
                "\nTitulo= " + titulo +
                "\nTotalTemporadas= " + totalTemporadas +
                "\nAvaliacao= " + avaliacao +
                "\nSinopse= " + sinopse +
                "\nPoster= " + poster +
                "\nAtores= " + atores + "\n";
    }
}
