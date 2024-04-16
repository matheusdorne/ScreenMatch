package br.com.screenmatch.service;

import br.com.screenmatch.dto.SerieDTO;
import br.com.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private SerieRepository repositorio;

    public List<SerieDTO> obterTodasAsSeries() {
        return repositorio.findAll()
                .stream()
                .map(s -> new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(), s.getGenero(), s.getSinopse(), s.getPoster(), s.getAtores()))
                .collect(Collectors.toList());

    }

    public List<SerieDTO> obterTop5() {
        return repositorio.findTop5ByOrderByAvaliacaoDesc()
                .stream()
                .map(s -> new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(), s.getGenero(), s.getSinopse(), s.getPoster(), s.getAtores()))
                .collect(Collectors.toList());
    }
}
