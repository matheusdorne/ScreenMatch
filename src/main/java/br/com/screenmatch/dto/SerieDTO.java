package br.com.screenmatch.dto;

import br.com.screenmatch.model.Categoria;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record SerieDTO(Long id,
                       String titulo,
                       Integer totalTemporadas,
                       double avaliacao,
                       Categoria genero,
                       String sinopse,
                       String poster,
                       String atores) {


}
