package br.com.screenmatch.main;

import br.com.screenmatch.modelos.Filme;
import br.com.screenmatch.modelos.Serie;
import br.com.screenmatch.modelos.Titulo;

import java.util.ArrayList;

public class PrincipalComListas {
    public static void main(String[] args) {
        var filmeDoMatheus = new Filme("Shrek", 2001);
        filmeDoMatheus.avalia(9);

        var outroFilme = new Filme("Clube da Luta", 1999);
        outroFilme.avalia(8);

        var meuFilme = new Filme("Poderoso Chefão", 1970);
        meuFilme.avalia(8);

        var lost = new Serie("Lost",2004);
        lost.avalia(10);


        ArrayList<Titulo> lista = new ArrayList<>();
        lista.add(filmeDoMatheus);
        lista.add(meuFilme);
        lista.add(outroFilme);
        lista.add(lost);

        for (Titulo item : lista) {
            System.out.println(item.getNome());
            
            Filme filme = (Filme) item;
            System.out.println("Classificação: " + filme.getClassificacao());
        }
    }
}
