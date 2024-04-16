package br.com.screenmatch.service;

import br.com.screenmatch.dto.SerieDTO;
import br.com.screenmatch.model.Serie;
import br.com.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository repositorio;

    private List<SerieDTO> converteParaDTO(List<Serie> series) {
        return series.stream()
                .map(s -> new SerieDTO(
                        s.getId(),
                        s.getTitulo(),
                        s.getTotalTemporadas(),
                        s.getAvaliacao(),
                        s.getGenero(),
                        s.getSinopse(),
                        s.getPoster(),
                        s.getAtores()))
                .collect(Collectors.toList());

        //Para não repetir código, foi criado um método que converte uma lista de séries para uma lista de DTOs
    }

    public List<SerieDTO> obterTodasAsSeries() {
        return converteParaDTO(repositorio.findAll());

    }

    public List<SerieDTO> obterTop5() {
        return converteParaDTO(repositorio.findTop5ByOrderByAvaliacaoDesc());

    }


    public List<SerieDTO> obterLancamentos() {
        return converteParaDTO(repositorio.encontrarEpisodiosMaisRecentes());
    }

    public SerieDTO obterPorId(Long id) { //Esse método é responsável por buscar uma série por ID
        Optional<Serie> serie = repositorio.findById(id);

        if (serie.isPresent()) {
            return new SerieDTO(
                    serie.get().getId(),
                    serie.get().getTitulo(),
                    serie.get().getTotalTemporadas(),
                    serie.get().getAvaliacao(),
                    serie.get().getGenero(),
                    serie.get().getSinopse(),
                    serie.get().getPoster(),
                    serie.get().getAtores());
        }
        return null;

    }
}

