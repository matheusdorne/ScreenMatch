package br.com.screenmatch.controller;

import br.com.screenmatch.dto.SerieDTO;
import br.com.screenmatch.model.Serie;
import br.com.screenmatch.repository.SerieRepository;
import br.com.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController //Nota que indica que a classe é um controlador
public class SerieController {



    @Autowired
    private SerieService servico; //Injeção de dependência

    @GetMapping("/series") //Indica que o método será chamado quando a URL for /series
    public List<SerieDTO> obterSeries()  {
        return servico.obterTodasAsSeries();


    }

    @GetMapping("series/top5")
    public List<SerieDTO> obterTop5Series() {
        return servico.obterTop5();
    }



}
