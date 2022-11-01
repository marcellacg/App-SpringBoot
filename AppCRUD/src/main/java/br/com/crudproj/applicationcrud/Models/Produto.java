package br.com.crudproj.applicationcrud.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private long id;

    @Column(name = "nome_produto")
    private String nome;

    @Column(name = "preco_produto")
    private double preco;

    @Column(name = "qnt_produto")
    private int qnt;


}
