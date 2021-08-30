package com.db.superm;

import com.github.javafaker.Faker;

public class Endereco {

	public static void insertclienteEndereco() {
		for (int i = 79; i < 111; i++) {
			String s = "INSERT INTO CLIENTE_ENDERECO (CODIGO_CLIENTE, CODIGO_ENDERECO) VALUES (" + i + "," + (i-65) + ");";
			System.out.println(s);
		}
	}
	
	public static void insertEndereco(int quantidadeEnderecos) {
		Faker faker = new Faker();
	
		for (int i = 0; i < quantidadeEnderecos; i++) {
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
	
}