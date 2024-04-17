package br.com.screenmatch.controller;

import br.com.screenmatch.dto.EpisodioDTO;
import br.com.screenmatch.dto.SerieDTO;
import br.com.screenmatch.model.Episodio;
import br.com.screenmatch.model.Serie;
import br.com.screenmatch.repository.SerieRepository;
import br.com.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController //Nota que indica que a classe é um controlador
@RequestMapping("/series") //Indica que a URL base para os métodos dessa classe será /series
public class SerieController {



    @Autowired
    private SerieService servico; //Injeção de dependência

    @GetMapping() //Indica que o método será chamado quando a URL for /series
    public List<SerieDTO> obterSeries()  {
        return servico.obterTodasAsSeries();


    }

    @GetMapping("/top5")
    public List<SerieDTO> obterTop5Series() {
        return servico.obterTop5();
    }

    @GetMapping("/lancamentos")
    public List<SerieDTO> obterLancamentos() {
        return servico.obterLancamentos();
    }


    @GetMapping("/{id}")
    public SerieDTO obterPorId(@PathVariable Long id) {
        return servico.obterPorId(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDTO> obterTodasTemporadas(@PathVariable Long id) {
        return servico.obterTodasTemporadas(id);
    }
}
