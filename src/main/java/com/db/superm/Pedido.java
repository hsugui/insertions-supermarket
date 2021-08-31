package com.db.superm;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Pedido {
	
	static int quantidadeProdutosCadastrados = 110;
	
	public static boolean jaExisteInt(ArrayList<Integer> list, int n) {
		if (list.contains(n)) return true;
		return false;
	}
	
	public static boolean jaExisteLong(ArrayList<Long> list, long l) {
		if (list.contains(l)) return true;
		return false;
	}
	
	public static String insertItemWeb(int idItem, int cdDocFiscal, int cdPedWeb) {
	 	String itemWeb =  "INSERT INTO ITEM_WEB (ID_ITEM, CODIGO_DOC_FISCAL, CODIGO_PEDIDO_WEB) VALUES "
	 			+ "(" + idItem + ", " + cdDocFiscal + ", " + cdPedWeb + ");";
		
		return itemWeb;
	}

	public static String insertPedidoWeb(int cdPedWeb, int idItem, int cdDocFiscal, int dia, double valorTotal) {
		String pedidoWeb = "INSERT INTO PEDIDO_WEB (CODIGO_PEDIDO_WEB, DATA_PEDIDO, VALOR_TOTAL) VALUES "
				+ "(" + cdPedWeb + ", '2021-08-" + dia + "', " + String.format("%.2f", valorTotal) + ");";
		
		return pedidoWeb;
	}

	public static void insertDocFisc(int quantidadeDocFisc, int quantidadeItensPorDocFisc, boolean isPedidoWeb) {
		Produto produto = new Produto();
		Map<Integer, Double> map = produto.createMap();
		ArrayList<Integer> listaCdDFs = new ArrayList<>();
		ArrayList<Integer> listaNrDFs = new ArrayList<>();
		ArrayList<Long> listaChaves = new ArrayList<>();

		int numeroSerieDocFisc = 1000;

		for (int i = 0; i < quantidadeDocFisc; i++) {
			double random = Math.random();
			int cdDocFiscal = (int) Math.floor(random * (9999 - 1000) + 1001);
			int nrDocFiscal = (int) Math.floor(random * (999999 - 100000) + 100001);
			long chave = ThreadLocalRandom.current().nextLong(10_000_000_000L, 100_000_000_000L);

			if (jaExisteInt(listaCdDFs, cdDocFiscal) || jaExisteInt(listaNrDFs, nrDocFiscal) || jaExisteLong(listaChaves, chave)) continue;

			listaCdDFs.add(cdDocFiscal);
			listaNrDFs.add(nrDocFiscal);
			listaChaves.add(chave);

			int cdCliente = (int) Math.floor(random * quantidadeProdutosCadastrados + 1);
			int cdLoja = (int) Math.floor(random * 5 + 1);
			String lojaString = String.valueOf(cdLoja);
			int dia = (int) Math.floor(random * 26 + 1);
			int cdPedWeb = (int) Math.floor(Math.random() * 9999 + 1);

			int idItem = 1;
			int cdProduto;
			int qtdProd;
			double valorUni;
			double icms;
			double valorTotal = 0;

			ArrayList<String> listCaixa = new ArrayList<>();
			ArrayList<String> listWeb = new ArrayList<>();

			for (int j = 0; j < quantidadeItensPorDocFisc; j++) {
				double randomItem = Math.random();
				int produtoMapa = (int) (randomItem * quantidadeProdutosCadastrados + 1);
				int randomMap = (int) Math.floor(produtoMapa);
				cdProduto = (int) Math.floor(produtoMapa);
				qtdProd = (int) Math.floor(randomItem * 6 + 1);
				valorUni = map.get(randomMap);
				icms = valorUni * qtdProd * 0.15;

				String itemCaixa = "INSERT INTO ITEM_CAIXA (ID_ITEM, CODIGO_DOC_FISCAL, CODIGO_PRODUTO,"
						+ "QUANTIDADE_PRODUTO, VALOR_UNITARIO, VALOR_ICMS, PERCENTUAL_ICMS) VALUES (" + idItem + ","
						+ cdDocFiscal + "," + cdProduto + "," + qtdProd + "," + valorUni + ","
						+ String.format("%.2f", icms) + "," + "15.0);";
				
				if (isPedidoWeb == true) {
					lojaString = "NULL";
					listWeb.add(insertItemWeb(idItem, cdDocFiscal, cdPedWeb));
				}

				listCaixa.add(itemCaixa);
				idItem++;
				valorTotal += valorUni * qtdProd;
			}

			String documentoFiscal = "INSERT INTO DOCUMENTO_FISCAL (CODIGO_DOC_FISCAL, NUMERO_DOC_FISCAL, SERIE_DOC_FISCAL,"
					+ "CODIGO_CLIENTE, CODIGO_LOJA, NUMERO_CHAVE_ACESSO, DATA_EMISSAO, VALOR_TOTAL) VALUES ("
					+ cdDocFiscal + "," + "'" + nrDocFiscal + "'," + "'" + numeroSerieDocFisc + "'," + cdCliente + ","
					+ lojaString + "," + "'" + chave + " " + chave + " " + chave + " " + chave + "'" + "," + "'2021-08-"
					+ dia + "'" + "," + String.format("%.2f", valorTotal) + ");";
			
			System.out.println(documentoFiscal);
			
			if (isPedidoWeb == true) {
				System.out.println(insertPedidoWeb(cdPedWeb, idItem, cdDocFiscal, dia, valorTotal));
				for (int k = 0; k < listCaixa.size(); k++) {
					System.out.println(listCaixa.get(k));
					System.out.println(listWeb.get(k));
				}
			} else {
				for (int k = 0; k < listCaixa.size(); k++) {
					System.out.println(listCaixa.get(k));
				}
			}
			System.out.println();
		}
	}
}
