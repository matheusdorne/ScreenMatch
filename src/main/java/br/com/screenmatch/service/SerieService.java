package br.com.screenmatch.service;

import br.com.screenmatch.dto.SerieDTO;
import br.com.screenmatch.model.Serie;
import br.com.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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
        return converteParaDTO(repositorio.findTop5ByOrderByEpisodiosDataLancamentoDesc());
    }
}
