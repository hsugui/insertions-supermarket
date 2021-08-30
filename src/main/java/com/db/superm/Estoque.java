package com.db.superm;

public class Estoque {
	
	public static void insertEstoque(int numeroInsercoes) {
		for (int i = 0; i < numeroInsercoes; i++) {
			int cdProduto = (int) Math.floor(Math.random() * 110 + 1);
			int cdLoja = (int) Math.floor(Math.random() * 5 + 1);
			int qtdEstoque = (int) Math.floor(Math.random() * 1000 + 1);
			
			String estoque = "INSERT INTO ESTOQUE (CODIGO_PRODUTO, CODIGO_LOJA, QUANTIDADE) VALUES (" + cdProduto + ", " + cdLoja + ", " + qtdEstoque + ");";
			
			System.out.println(estoque);	
		}
	}
	
}