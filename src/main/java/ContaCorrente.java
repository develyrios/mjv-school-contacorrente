package main.java;

import java.util.ArrayList;
import java.util.Date;

/**
 * Ao meu ver o unicos atributo wrapper desta classe é o saldo e cancelada, seus outros dados são
 * providos das outras classes especializadas.
 * Boa parte dos métodos estão void, pois ao meu ver com o uso de exceptions é função destes métodos
 * notificarem se algo deu errado e não de que algo deu certo.
 * = Ciro
 */
public class ContaCorrente {
    private Pessoa titular;
    private ArrayList<Transacao> transacoes;
    private Double saldo;
    private Boolean cancelada;

    // Construtor padrão
    public ContaCorrente(Pessoa titular) {
        this.titular = titular;
        this.transacoes = new ArrayList<Transacao>();
        this.saldo = 0.00;
        this.cancelada = false;
    }

    // Construtor com saldo para testes
    public ContaCorrente(Pessoa titular, Double saldo) {
        this.titular = titular;
        this.transacoes = new ArrayList<Transacao>();
        this.saldo = saldo;
        this.cancelada = false;
    }

    public void depositar(Double valor) {
        // To do...
    }

    public void sacar(Double valor) {
        // To do...
    }

    public Double consultarSaldo() {
        return saldo;
    }

    public void transferir(ContaCorrente contaDestinataria, Double valor) {
        // To do...
    }

    // Precisa de revisão, fiz somente por exemplo - Ciro
    public void cancelarConta(String justificativa) {
        if(justificativa.isEmpty()) {
            throw new RuntimeException("Não foi declarada uma justificativa!");
        }

        this.cancelada = true;
    }

    public ArrayList<Transacao> consultarExtrato(Date dataInicio, Date dataFim) {
        // To do...
        return new ArrayList<Transacao>();
    }
}
