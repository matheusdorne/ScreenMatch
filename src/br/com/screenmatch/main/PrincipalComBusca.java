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
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner (System.in);
        System.out.println("Digite um filme para busca: ");
        String busca  = leitura.nextLine();


        Conexao conecta;
        conecta = new Conexao();
        TituloOMDB meuTituloOMDB = conecta.conectar(busca);
        System.out.println(meuTituloOMDB);

        // try{
        // Convertendo a classe record na classe própria
        Titulo meuTitulo =  new Titulo(meuTituloOMDB);

        System.out.println("Titulo já convertido");
        System.out.println(meuTitulo);

        FileWriter write = new FileWriter("movies.txt");
        write.write(meuTitulo.toString());
        write.close();

        // multi-catch posso colocar multiplas exceçoes no mesmo parentese




        System.out.println("\nPrograma finalizado corretamente");






    }
}
