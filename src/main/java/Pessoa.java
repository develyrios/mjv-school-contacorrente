package java;

import java.util.Date;

/**
 *  Criei a classe por conta das dicas do professor nos comunicados.
 *  - Ciro
 */

public class Pessoa {
    private String nome;
    private String cpf;
    private Date dataNascimento;

    public Pessoa(String nome, String cpf, Date dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }
}
