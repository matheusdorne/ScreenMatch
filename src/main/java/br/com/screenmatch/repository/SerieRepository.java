package br.com.screenmatch.repository;

import br.com.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie,Long> {
    //Esse extends será reponsavel pelo CRUD <Quem é minha entidade, qual é o tipo da minha ID>
    Optional<Serie>findByTituloContainingIgnoreCase(String nomeSerie);
}
