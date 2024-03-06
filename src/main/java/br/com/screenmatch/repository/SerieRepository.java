package br.com.screenmatch.repository;

import br.com.screenmatch.model.Categoria;
import br.com.screenmatch.model.Episodio;
import br.com.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie,Long> {
    //Esse extends será reponsavel pelo CRUD <Quem é minha entidade, qual é o tipo da minha ID>
    Optional<Serie>findByTituloContainingIgnoreCase(String nomeSerie);
    // Jpa vai se basear no nome criado para criar uma consulta derivada no banco
    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAtor, double avaliacao);

   List<Serie> findTop5ByOrderByAvaliacaoDesc();

   List<Serie> findByGenero (Categoria categoria);

    List<Serie> findByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqual(int numeroTemporadas, double avaliacao);

// Abaixo substituimos um query padrão por um modelo baseado em atributos e entidades, o ":" endica o atributo que será passado
    @Query("select s from Serie s WHERE s.totalTemporadas <= :numeroTemporadas AND s.avaliacao >= :avaliacao ")
    List<Serie> seriesPorTemporadaEAvaliacao(int numeroTemporadas, double avaliacao);

    @Query("select e from Episodio e WHERE e.titulo LIKE %:trechoEpisodio%")
    List<Episodio> episodiosPorTrecho(String trechoEpisodio);
}
