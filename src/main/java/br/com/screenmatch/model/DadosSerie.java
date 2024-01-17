package br.com.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosSerie(@JsonAlias("Title") String titulo,
                         @JsonAlias("totalSeaons") Integer totalTemporadas,
                         @JsonAlias("imdbRating ") String avaliacao){
}
