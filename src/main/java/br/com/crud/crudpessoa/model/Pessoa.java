package br.com.crud.crudpessoa.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pessoa")
@Data
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "nome", length = 255)
    String nome;

    @Column(name = "cpf", length = 11)
    String cpf;

    @Column(name = "idade", columnDefinition = "INT")
    int idade;

}
