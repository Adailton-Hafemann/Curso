package br.com.alura.screenmatch.util;

public interface IConverteDados {

	<T> T obterDados(String json, Class<T> classe);
}
