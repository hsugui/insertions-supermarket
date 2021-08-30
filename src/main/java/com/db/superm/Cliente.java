package com.db.superm;

import com.github.javafaker.Faker;

public class Cliente {
	
	public static void insertCliente(int quantidadeClientes) {
		Faker faker = new Faker();
		
		for (int i = 0; i < quantidadeClientes; i++) {
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
}