package br.com.screenmatch.main;

import br.com.screenmatch.conexao.Conexao;
import br.com.screenmatch.excecao.ErrorDeConversaoDeAnoException;
import br.com.screenmatch.modelos.Titulo;
import br.com.screenmatch.modelos.TituloOMDB;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        String busca = "";
        List<Titulo> titulos = new ArrayList<>();

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create();

        Scanner leitura = new Scanner (System.in);

        while (!busca.equalsIgnoreCase("sair")){
        System.out.println("Digite um filme para busca: ");
        busca  = leitura.nextLine();

        if (busca.equalsIgnoreCase("sair")) {
            break;
        }

        Conexao conecta;
        conecta = new Conexao();
        TituloOMDB meuTituloOMDB = conecta.conectar(busca);
        System.out.println(meuTituloOMDB);

        // Convertendo a classe record na classe própria
        Titulo meuTitulo =  new Titulo(meuTituloOMDB);

        System.out.println("Titulo já convertido");
        System.out.println(meuTitulo);
        titulos.add(meuTitulo);

        }
        System.out.println(titulos);

        FileWriter escrita = new FileWriter("movies.json");
        escrita.write(gson.toJson(titulos));
        escrita.close();
        System.out.println("\nPrograma finalizado corretamente");






    }
}
