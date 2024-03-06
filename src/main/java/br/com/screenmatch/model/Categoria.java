package br.com.screenmatch.model;

public enum Categoria {
    ACAO("Action","Ação"),
    AVENTURA("Adventure","Romance"),
    COMEDIA("Comedy","Comédia"),
    DRAMA ("Drama", "Drama"),
    FANTASIA ("Fantasy","Fantasia"),
    FICCAO ("Sci-Fi", "Ficção"),
    ROMANCE ("Romance", "Romance"),
    SUSPENSE ("Thriller","Suspense"),
    TERROR ("Horror","Terror"),
    CRIME ("Crime","Crime"),;

    private String categoriaOmdb;

    private String categoriaPortugues;

    Categoria(String categoriaOmdb, String categoriaPortugues) {
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaPortugues = categoriaPortugues;
    }

    public static  Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw  new IllegalArgumentException("Nenhuma categoria encontrada para essa serie");
    }

    public static  Categoria fromPortugues(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaPortugues.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw  new IllegalArgumentException("Nenhuma categoria encontrada");
    }
}
