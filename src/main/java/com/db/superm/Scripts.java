package com.db.superm;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import com.github.javafaker.Faker;

public class Scripts {
	
	public static void insertEstoque() {
		for (int i = 0; i < 100; i++) {
			int cdProduto = (int) Math.floor(Math.random() * 108 + 1);
			int cdLoja = (int) Math.floor(Math.random() * 5 + 1);
			int qtdEstoque = (int) Math.floor(Math.random() * 1000 + 1);
			
			String estoque = "INSERT INTO ESTOQUE (CODIGO_PRODUTO, CODIGO_LOJA, QUANTIDADE) VALUES (" + cdProduto + ", " + cdLoja + ", " + qtdEstoque + ");";
			
			System.out.println(estoque);	
		}
	}
	
	public static void insertclienteEndereco() {
		for (int i = 79; i < 111; i++) {
			String s = "INSERT INTO CLIENTE_ENDERECO (CODIGO_CLIENTE, CODIGO_ENDERECO) VALUES (" + i + "," + (i-65) + ");";
			System.out.println(s);
		}
	}
	
	public static void insertEndereco() {
		Faker faker = new Faker();
	
		for (int i = 0; i < 50; i++) {
			int c1 = (int) Math.floor(Math.random() * (99999-10000) + 10001);
			int c2 = (int) Math.floor(Math.random() * (999-100) + 101);
			
			String bairro = faker.address().cityName().toUpperCase() ;
			String rua = faker.address().streetName().toUpperCase();
			String cep = String.format("%d-%d", c1, c2);
			String numero = faker.address().streetAddressNumber();

			String endereco = "INSERT INTO ENDERECO (ESTADO, CIDADE, BAIRRO, RUA, CEP, NUMERO, COMPLEMENTO) VALUES"
					+ "('SP', 'SÃO PAULO', '" + bairro + "', '" + rua + "', '" + cep + "', '" + numero + "', NULL);";

			System.out.println(endereco);
		}	
	}
	
	public static void insertCliente() {
		Faker faker = new Faker();
		
		for (int i = 0; i < 50; i++) {
			int pf1 = (int) Math.floor(Math.random() * (999-100) + 101);
			int pf2 = (int) Math.floor(Math.random() * (999-100) + 101);
			int pf3 = (int) Math.floor(Math.random() * (999-100) + 101);
			int pf4 = (int) Math.floor(Math.random() * (99-10) + 11);
			int pj1 = (int) Math.floor(Math.random() * (99-10) + 11);
			int pj2 = (int) Math.floor(Math.random() * (999-100) + 101);
			int pj3 = (int) Math.floor(Math.random() * (999-100) + 101);
			int pj4 = (int) Math.floor(Math.random() * (99-10) + 11);
			
			String nomePF = faker.name().fullName();
			String nomePJ = faker.app().name();
			
			String cpf = String.format("%d.%d.%d-%d", pf1, pf2, pf3, pf4);
			String cnpj = String.format("%d.%d.%d/0001-%d", pj1, pj2, pj3, pj4);
			
			String pf = "INSERT INTO CLIENTE (NOME, CPF_CNPJ, CODIGO_TIPO_CLIENTE) VALUES ('" + nomePF + "', '" + cpf +"', 1);";
			String pj = "INSERT INTO CLIENTE (NOME, CPF_CNPJ, CODIGO_TIPO_CLIENTE) VALUES ('" + nomePJ + "', '" + cnpj +"', 2);";
			System.out.println(pf);
			System.out.println(pj);	
		}
	}
	
	public static void insertDocFisc() {
		Produto produto = new Produto();
		Map<Integer, Double> map = produto.createMap();

		for (int i = 0; i < 10; i++) {
			double random = Math.random();
			int cdDocFiscal = (int) Math.floor(random * (9999-1000) + 1001);
			int nrDocFiscal = (int) Math.floor(random * (999999-100000) + 100001);
			int cdCliente = (int) Math.floor(random * 110 + 1);
			int cdLoja = (int) Math.floor(random * 5 + 1);
			long chave = ThreadLocalRandom.current().nextLong(10_000_000_000L, 100_000_000_000L);
			int dia = (int) Math.floor(random * 26 + 1);
			
			int idItem = 1;
			int cdProduto;
			int qtdProd;
			double valorUni;
			double icms;
			double valorTotal = 0;
		
			ArrayList<String> list = new ArrayList<>();
			
			for(int j = 0; j < 2; j++) {
				double randomItem = Math.random();
				int produtoMapa = (int) (randomItem * 110 + 1);
				int randomMap = (int) Math.floor(produtoMapa);
				cdProduto = (int) Math.floor(produtoMapa);
				qtdProd = (int) Math.floor(randomItem * 4 + 1);
				valorUni =  map.get(randomMap);
				icms = valorUni * qtdProd * 0.25;
				
				String itemCaixa = "INSERT INTO ITEM_CAIXA (ID_ITEM, CODIGO_DOC_FISCAL, CODIGO_PRODUTO,"
						+ "QUANTIDADE_PRODUTO, VALOR_UNITARIO, VALOR_ICMS, PERCENTUAL_ICMS) VALUES ("
						+ idItem + "," + cdDocFiscal + "," + cdProduto + "," + qtdProd + "," + valorUni + "," + String.format("%.2f", icms) + "," + "25.0);";
				
				list.add(itemCaixa);
				idItem++;
				valorTotal += valorUni * qtdProd;
			}
			
			String documentoFiscal = "INSERT INTO DOCUMENTO_FISCAL (CODIGO_DOC_FISCAL, NUMERO_DOC_FISCAL, SERIE_DOC_FISCAL,"
					+ "CODIGO_CLIENTE, CODIGO_LOJA, NUMERO_CHAVE_ACESSO, DATA_EMISSAO, VALOR_TOTAL) VALUES ("
					+ cdDocFiscal + "," + "'" + nrDocFiscal + "'," + "'" + 600 + "'," +
					cdCliente + "," + cdLoja + "," + "'" + chave + " " + chave + " " + chave + " " + chave + "'" + ","
					+ "'2021-08-" + dia + "'" + "," + String.format("%.2f", valorTotal) + ");";
			
			System.out.println(documentoFiscal);
			for (int k = 0; k < list.size(); k++) {
				System.out.println(list.get(k));
			}
			System.out.println();
		}
	}
	
	public static void insertPedidoWeb() {
		Produto produto = new Produto();
		Map<Integer, Double> map = produto.createMap();

		for (int i = 0; i < 5; i++) {
			double random = Math.random();
			int cdDocFiscal = (int) Math.floor(random * (9999-1000) + 1001);
			int nrDocFiscal = (int) Math.floor(random * (999999-100000) + 100001);
			int cdCliente = (int) Math.floor(random * 110 + 1);
			long chave = ThreadLocalRandom.current().nextLong(10_000_000_000L, 100_000_000_000L);
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
			
			for(int j = 0; j < 5; j++) {
				double randomItem = Math.random();
				int produtoMapa = (int) (randomItem * 110 + 1);
				int randomMap = (int) Math.floor(produtoMapa);
				cdProduto = (int) Math.floor(produtoMapa);
				qtdProd = (int) Math.floor(randomItem * 4 + 1);
				valorUni =  map.get(randomMap);
				icms = valorUni * qtdProd * 0.2;
				
				String itemCaixa = "INSERT INTO ITEM_CAIXA (ID_ITEM, CODIGO_DOC_FISCAL, CODIGO_PRODUTO,"
						+ "QUANTIDADE_PRODUTO, VALOR_UNITARIO, VALOR_ICMS, PERCENTUAL_ICMS) VALUES ("
						+ idItem + "," + cdDocFiscal + "," + cdProduto + "," + qtdProd + "," + valorUni + "," + String.format("%.2f", icms) + "," + "20.0);";
				
			 	String itemWeb =  "INSERT INTO ITEM_WEB (ID_ITEM, CODIGO_DOC_FISCAL, CODIGO_PEDIDO_WEB) VALUES "
			 			+ "(" + idItem + ", " + cdDocFiscal + ", " + cdPedWeb + ");";
				
			 	listCaixa.add(itemCaixa);
			 	listWeb.add(itemWeb);
				idItem++;
				valorTotal += valorUni * qtdProd;
			}
			
			String documentoFiscal = "INSERT INTO DOCUMENTO_FISCAL (CODIGO_DOC_FISCAL, NUMERO_DOC_FISCAL, SERIE_DOC_FISCAL,"
					+ "CODIGO_CLIENTE, CODIGO_LOJA, NUMERO_CHAVE_ACESSO, DATA_EMISSAO, VALOR_TOTAL) VALUES ("
					+ cdDocFiscal + "," + "'" + nrDocFiscal + "'," + "'" + 500 + "'," +
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
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
//		insertclienteEndereco();
//		insertEndereco();
//		insertCliente();
//		insertDocFisc();
//		insertPedidoWeb();
//		insertEstoque();
	}
}