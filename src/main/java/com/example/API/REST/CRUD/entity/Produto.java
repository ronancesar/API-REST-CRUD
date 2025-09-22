package com.example.API.REST.CRUD.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity //Avisa ao JPA que esta classe representa uma tabela no banco de dados.
@Data // Anotação de Lombok: cria automaticamente getters, setters, construtor, toString (), etc.

public class Produto {

    @Id // MArca este campo como a chave primaria da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura o Id pra ser autoincrementado pelo banco
    private Long id;

    private String nome;
    private double preco;
    private int quantidade;


}

