package br.com.screenmatch.model;

public enum Categoria {
    ACAO("Action"),
    AVENTURA("Adventure"),
    COMEDIA("Comedy"),
    DRAMA ("Drama"),
    FANTASIA ("Fantasy"),
    FICCAO ("Sci-Fi"),
    ROMANCE ("Romance"),
    SUSPENSE ("Thriller"),
    TERROR ("Horror"),
    CRIME ("Crime");

    private String categoriaOmdb;

    Categoria(String categoriaOmdb) {
        this.categoriaOmdb = categoriaOmdb;
    }

    public static  Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw  new IllegalArgumentException("Nenehuma ctegoria encontrada para essa serie");
    }
}
