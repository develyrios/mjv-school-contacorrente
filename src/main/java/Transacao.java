package main.java;

import java.util.Date;

/**
 *  A lógica por traz desta classe é poder armazenar as transações dentro de um
 *  ArrayList, um wrapper de vetor, na classe ContaCorrente.
 *  - Ciro
 */
public class Transacao {
    private Date data;
    private String tipo;
    private Double valor;

    public Transacao(Date data, String tipo, Double valor) {
        this.data = data;
        this.tipo = tipo;
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public String getTipo() {
        return tipo;
    }

    public Double getValor() {
        return valor;
    }
}
