package Java;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Date;

/**
 * Ao meu ver o unicos atributo wrapper desta classe é o saldo e cancelada, seus outros dados são
 * providos das outras classes especializadas.
 * Boa parte dos métodos estão void, pois ao meu ver com o uso de exceptions é função destes métodos
 * notificarem se algo deu errado e não de que algo deu certo.
 * = Ciro
 */


/**
 * substituímos todas as classes Double que envolvem transações de saldo/dinheiro para BigDecimal, para
 * evitar possíveis erros e variações de cálculo que a classe double pode gerar. Isso acontece pela forma que
 * a JVM interpreta valores double
 * (referência: https://www.devmedia.com.br/java-bigdecimal-trabalhando-com-mais-precisao/30286)
 * (Rodrigo)
 */
public class ContaCorrente {
    private Pessoa titular;
    private ArrayList<Transacao> transacoes;
    private BigDecimal saldo;
    private Boolean cancelada;

    // Construtor padrão
    public ContaCorrente(Pessoa titular) {
        this.titular = titular;
        this.transacoes = new ArrayList<Transacao>();
        this.saldo = new BigDecimal(0.00, new MathContext(3));
        this.cancelada = false;
    }

    // Construtor com saldo para testes
    public ContaCorrente(Pessoa titular, BigDecimal saldo) {
        this.titular = titular;
        this.transacoes = new ArrayList<Transacao>();

        // Validar saldo
        this.validarValor(saldo);
        this.saldo = new BigDecimal(0.00, new MathContext(3));

        this.saldo = this.saldo.add(saldo);
        this.cancelada = false;
    }

    // Mykaeli
    // revisar
    public void depositar(BigDecimal valor) {
      if(valor.doubleValue() <= 0.00 ) {
          throw new RuntimeException("Valor inválido");
      }

      //to do

    }

    // Parte Rodrigo
    public void sacar(BigDecimal valorSaque) {
        // Erros internos
        this.validarValor(valorSaque);

        // Erros de usuário
        if(valorSaque.compareTo(this.saldo) != -1) {
            throw new RuntimeException("Saldo insuficiente para essa transação!");
        }

        this.saldo = this.saldo.subtract(valorSaque);
    }

    // Bev
    public void transferir(ContaCorrente contaDestinataria, BigDecimal valor) {
        // To do...
    }

    // Ciro
    public BigDecimal consultarSaldo() {
        return saldo;
    }

    // Ciro
    public void cancelarConta(String justificativa) {
        if(justificativa.isEmpty()) {
            throw new RuntimeException("Não foi declarada uma justificativa!");
        }

        this.cancelada = true;
    }

    // Ciro
    public ArrayList<Transacao> consultarExtrato(Date dataInicio, Date dataFim) {
        // To do...
        return new ArrayList<Transacao>();
    }

    /**
     * Método para descobrir se o valor é um número positivo diferente de 0 e somente
     * duas casas decimais.
     */
    private void validarValor(BigDecimal valor) {
        if(valor.doubleValue() <= 0.00) {
            throw new RuntimeException("Valor inválido!");
        }

        if(valor.scale() > 2) {
            throw new RuntimeException("Valor não tratado!");
        }
    }
}
