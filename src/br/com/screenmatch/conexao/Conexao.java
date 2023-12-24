package br.com.screenmatch.conexao;

import br.com.screenmatch.excecao.ErrorDeConversaoDeAnoException;
import br.com.screenmatch.modelos.TituloOMDB;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Conexao {


    public TituloOMDB conectar(String busca) {

        String endereco = "http://www.omdbapi.com/?apikey=60826722&t=" + busca.replace(" ", "+");
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

         return meuTituloOMDB;
        } catch (NumberFormatException e) {
            System.out.println("Erro: ");
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: ");
            System.out.println(e.getMessage());
        } catch (ErrorDeConversaoDeAnoException e) {
            System.out.println("Erro: ");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    return null;
    }
}
