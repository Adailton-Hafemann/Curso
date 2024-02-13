package br.com.alura.screenmatch.enums;

public enum Categoria {

	ACAO("Action"),
	ROMANCE("Romance"),
	COMEDIA("Comedy"),
	DRAMA("Drama"),
	CRIME("Crime");
	
	private String categoriaOmdb;
	
	Categoria(String categoriaDmdb) {
		this.categoriaOmdb = categoriaDmdb;
	}
	
	public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaOmdb.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
