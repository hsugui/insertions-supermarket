package com.db.superm;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Pedido {
	
	public static boolean jaExisteInt(ArrayList<Integer> list, int n) {
		if (list.contains(n)) return true;
		return false;
	}
	
	public static boolean jaExisteLong(ArrayList<Long> list, long l) {
		if (list.contains(l)) return true;
		return false;
	}

	public static void insertDocFisc(int quantidadeDocFisc, int quantidadeItensPorDocFisc) {
		Produto produto = new Produto();
		Map<Integer, Double> map = produto.createMap();
		ArrayList<Integer> listaCdDFs = new ArrayList<>();
		ArrayList<Integer> listaNrDFs = new ArrayList<>();
		ArrayList<Long> listaChaves = new ArrayList<>();
		
		int quantidadeProdutosCadastrados = 110;
		int numeroSerieDocFisc = 1000;

		for (int i = 0; i < quantidadeDocFisc; i++) {
			double random = Math.random();
			int cdDocFiscal = (int) Math.floor(random * (9999-1000) + 1001);
			int nrDocFiscal = (int) Math.floor(random * (999999-100000) + 100001);
			long chave = ThreadLocalRandom.current().nextLong(10_000_000_000L, 100_000_000_000L);
			
			if (jaExisteInt(listaCdDFs, cdDocFiscal) || jaExisteInt(listaNrDFs, nrDocFiscal) || jaExisteLong(listaChaves, chave)) continue;
			
			listaCdDFs.add(cdDocFiscal);
			listaNrDFs.add(nrDocFiscal);
			listaChaves.add(chave);
			
			int cdCliente = (int) Math.floor(random * quantidadeProdutosCadastrados + 1);
			int cdLoja = (int) Math.floor(random * 5 + 1);
			int dia = (int) Math.floor(random * 26 + 1);
			
			int idItem = 1;
			int cdProduto;
			int qtdProd;
			double valorUni;
			double icms;
			double valorTotal = 0;
		
			ArrayList<String> list = new ArrayList<>();
			
			for(int j = 0; j < quantidadeItensPorDocFisc; j++) {
				double randomItem = Math.random();
				int produtoMapa = (int) (randomItem * quantidadeProdutosCadastrados + 1);
				int randomMap = (int) Math.floor(produtoMapa);
				cdProduto = (int) Math.floor(produtoMapa);
				qtdProd = (int) Math.floor(randomItem * 6 + 1);
				valorUni =  map.get(randomMap);
				icms = valorUni * qtdProd * 0.15;
				
				String itemCaixa = "INSERT INTO ITEM_CAIXA (ID_ITEM, CODIGO_DOC_FISCAL, CODIGO_PRODUTO,"
						+ "QUANTIDADE_PRODUTO, VALOR_UNITARIO, VALOR_ICMS, PERCENTUAL_ICMS) VALUES ("
						+ idItem + "," + cdDocFiscal + "," + cdProduto + "," + qtdProd + "," + valorUni + "," + String.format("%.2f", icms) + "," + "15.0);";
				
				list.add(itemCaixa);
				idItem++;
				valorTotal += valorUni * qtdProd;
			}
			
			String documentoFiscal = "INSERT INTO DOCUMENTO_FISCAL (CODIGO_DOC_FISCAL, NUMERO_DOC_FISCAL, SERIE_DOC_FISCAL,"
					+ "CODIGO_CLIENTE, CODIGO_LOJA, NUMERO_CHAVE_ACESSO, DATA_EMISSAO, VALOR_TOTAL) VALUES ("
					+ cdDocFiscal + "," + "'" + nrDocFiscal + "'," + "'" + numeroSerieDocFisc + "'," +
					cdCliente + "," + cdLoja + "," + "'" + chave + " " + chave + " " + chave + " " + chave + "'" + ","
					+ "'2021-08-" + dia + "'" + "," + String.format("%.2f", valorTotal) + ");";
			
			System.out.println(documentoFiscal);
			for (int k = 0; k < list.size(); k++) {
				System.out.println(list.get(k));
			}
//			System.out.println();
		}
	}
	
	public static void insertPedidoWeb(int quantidadeDocFisc, int quantidadeItensPorDocFisc) {
		Produto produto = new Produto();
		Map<Integer, Double> map = produto.createMap();
		ArrayList<Integer> listaCdDFs = new ArrayList<>();
		ArrayList<Integer> listaNrDFs = new ArrayList<>();
		ArrayList<Long> listaChaves = new ArrayList<>();
		
		int quantidadeProdutosCadastrados = 110;
		int numeroSerieDocFisc = 800;

		for (int i = 0; i < quantidadeDocFisc; i++) {
			double random = Math.random();
			int cdDocFiscal = (int) Math.floor(random * (9999-1000) + 1001);
			int nrDocFiscal = (int) Math.floor(random * (999999-100000) + 100001);
			long chave = ThreadLocalRandom.current().nextLong(10_000_000_000L, 100_000_000_000L);
			
			if (jaExisteInt(listaCdDFs, cdDocFiscal) || jaExisteInt(listaNrDFs, nrDocFiscal) || jaExisteLong(listaChaves, chave)) continue;
			
			listaCdDFs.add(cdDocFiscal);
			listaNrDFs.add(nrDocFiscal);
			listaChaves.add(chave);
			
			int cdCliente = (int) Math.floor(random * quantidadeProdutosCadastrados + 1);
			int dia = (int) Math.floor(random * 26 + 1);
			
			int idItem = 1;
			int cdProduto;
			int qtdProd;
			double valorUni;
			double icms;
			double valorTotal = 0;
			
			int cdPedWeb = (int) Math.floor(Math.random() * 9999 + 1);
			
			ArrayList<String> listCaixa = new ArrayList<>();
			ArrayList<String> listWeb = new ArrayList<>();
			
			for(int j = 0; j < quantidadeItensPorDocFisc; j++) {
				double randomItem = Math.random();
				int produtoMapa = (int) (randomItem * quantidadeProdutosCadastrados + 1);
				int randomMap = (int) Math.floor(produtoMapa);
				cdProduto = (int) Math.floor(produtoMapa);
				qtdProd = (int) Math.floor(randomItem * 2 + 1);
				valorUni =  map.get(randomMap);
				icms = valorUni * qtdProd * 0.12;
				
				String itemCaixa = "INSERT INTO ITEM_CAIXA (ID_ITEM, CODIGO_DOC_FISCAL, CODIGO_PRODUTO,"
						+ "QUANTIDADE_PRODUTO, VALOR_UNITARIO, VALOR_ICMS, PERCENTUAL_ICMS) VALUES ("
						+ idItem + "," + cdDocFiscal + "," + cdProduto + "," + qtdProd + "," + valorUni + "," + String.format("%.2f", icms) + "," + "12.0);";
				
			 	String itemWeb =  "INSERT INTO ITEM_WEB (ID_ITEM, CODIGO_DOC_FISCAL, CODIGO_PEDIDO_WEB) VALUES "
			 			+ "(" + idItem + ", " + cdDocFiscal + ", " + cdPedWeb + ");";
				
			 	listCaixa.add(itemCaixa);
			 	listWeb.add(itemWeb);
				idItem++;
				valorTotal += valorUni * qtdProd;
			}
			
			String documentoFiscal = "INSERT INTO DOCUMENTO_FISCAL (CODIGO_DOC_FISCAL, NUMERO_DOC_FISCAL, SERIE_DOC_FISCAL,"
					+ "CODIGO_CLIENTE, CODIGO_LOJA, NUMERO_CHAVE_ACESSO, DATA_EMISSAO, VALOR_TOTAL) VALUES ("
					+ cdDocFiscal + "," + "'" + nrDocFiscal + "'," + "'" + numeroSerieDocFisc + "'," +
					cdCliente + ", NULL," + "'" + chave + " " + chave + " " + chave + " " + chave + "'" + ","
					+ "'2021-08-" + dia + "'" + "," + String.format("%.2f", valorTotal) + ");";
			
			String pedidoWeb = "INSERT INTO PEDIDO_WEB (CODIGO_PEDIDO_WEB, DATA_PEDIDO, VALOR_TOTAL) VALUES "
					+ "(" + cdPedWeb + ", '2021-08-" + dia + "', " + String.format("%.2f", valorTotal) + ");";
			
			System.out.println(documentoFiscal);
			System.out.println(pedidoWeb);
			for (int k = 0; k < listCaixa.size(); k++) {
				System.out.println(listCaixa.get(k));
				System.out.println(listWeb.get(k));
			}
//			System.out.println();
		}
	}
	
}
