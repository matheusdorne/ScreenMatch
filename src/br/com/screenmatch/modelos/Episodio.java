package br.com.screenmatch.modelos;

import br.com.screenmatch.calculos.Classificavel;

public class Episodio implements Classificavel {
    private int numero;
    private String nome;
    private Serie serie;

    public void setTotalVisualizacoes(int totalVisualizacoes) {
        this.totalVisualizacoes = totalVisualizacoes;
    }

    private int totalVisualizacoes;

    public int getTotalVisualizacoes() {
        return totalVisualizacoes;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    @Override
    public int getClassificacao() {
        if (totalVisualizacoes > 100)
            return 5;
        else if (totalVisualizacoes > 50)
            return 4;
        else if (totalVisualizacoes > 20)
            return 3;
        else if (totalVisualizacoes > 10)
            return 2;
        else if (totalVisualizacoes > 5)
            return 1;
        else
        return 0;
    }
}
