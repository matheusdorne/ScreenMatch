package br.com.screenmatch.repository;

import br.com.screenmatch.model.Categoria;
import br.com.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie,Long> {
    //Esse extends será reponsavel pelo CRUD <Quem é minha entidade, qual é o tipo da minha ID>
    Optional<Serie>findByTituloContainingIgnoreCase(String nomeSerie);
    // Jpa vai se basear no nome criado para criar uma consulta derivada no banco
    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAtor, double avaliacao);

   List<Serie> findTop5ByOrderByAvaliacaoDesc();

   List<Serie> findByGenero (Categoria categoria);
}
