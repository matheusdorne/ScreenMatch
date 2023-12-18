package br.com.screenmatch.main;

import br.com.screenmatch.modelos.Titulo;
import br.com.screenmatch.modelos.TituloOMDB;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
        try {
// Comunicação com webservice
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();
            System.out.println(json);
            // Ajusta a primeira letra maiuscula que vem do site,
            // já que o padrão de boa prática é letra minuscula nos atributos
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                    .create();


            TituloOMDB meuTituloOMDB = gson.fromJson(json,TituloOMDB.class);
            System.out.println(meuTituloOMDB);

            // try{
            // Convertendo a classe record na classe própria
            Titulo meuTitulo =  new Titulo(meuTituloOMDB);

            System.out.println("Titulo já convertido");
            System.out.println(meuTitulo);
        } catch (NumberFormatException e) {
            System.out.println("Erro: ");
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: ");
            System.out.println(e.getMessage());
        }



        System.out.println("\nPrograma finalizado corretamente");






    }
}
