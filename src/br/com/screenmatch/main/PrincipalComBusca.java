package br.com.screenmatch.main;

import br.com.screenmatch.modelos.Titulo;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner (System.in);
        System.out.println("Digite um filme para busca: ");
        var busca  = leitura.nextLine();


       
        String endereco = "http://www.omdbapi.com/?apikey=60826722&t=" + busca;
        

// Comunicação com webservice
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        System.out.println(json);


        Gson gson = new Gson();
        Titulo meuTitulo = gson.fromJson(json, Titulo.class);

        System.out.println("Titulo: "+meuTitulo.getNome());






        

    }
}
