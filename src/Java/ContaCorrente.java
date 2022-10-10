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
    private String numeroConta;
    private Pessoa titular;
    private ArrayList<Transacao> transacoes;
    private BigDecimal saldo;
    private Boolean cancelada;

    // Construtor padrão
    public ContaCorrente(String numeroConta, Pessoa titular) {
        this.numeroConta = numeroConta;
        this.titular = titular;
        this.transacoes = new ArrayList<Transacao>();
        this.saldo = new BigDecimal(0.00, new MathContext(3));
        this.cancelada = false;
    }

    // Construtor com saldo para testes
    public ContaCorrente(String numeroConta, Pessoa titular, BigDecimal saldo) {
        this.numeroConta = numeroConta;
        this.titular = titular;
        this.transacoes = new ArrayList<Transacao>();

        // Validar saldo
        this.validarValor(saldo);
        this.saldo = new BigDecimal(0.00, new MathContext(3));

        this.saldo = this.saldo.add(saldo);
        this.cancelada = false;
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    // Mykaeli
    public void depositar(BigDecimal valorDeposito) {
        this.validarValor(valorDeposito);

        this.saldo = this.saldo.add(valorDeposito);

        this.transacoes.add(new Transacao(new Date(), "Depósito", valorDeposito));
    }

    // Parte Rodrigo
    public void sacar(BigDecimal valorSaque) {
        // Erros internos
        this.validarValor(valorSaque);

        // Erros de usuário
        if(valorSaque.compareTo(this.saldo) == 1) {
            throw new RuntimeException("Saldo insuficiente para essa transação!");
        }

        this.saldo = this.saldo.subtract(valorSaque);

        this.transacoes.add(new Transacao(new Date(), "Saque", valorSaque));
    }

    // Bev
    public void transferir(ContaCorrente contaDestinataria, BigDecimal valorTransferencia) {
        this.validarValor(valorTransferencia);

        if(valorTransferencia.compareTo(this.saldo) == 1) {
            throw new RuntimeException("Saldo insuficiente para essa transação!");
        }

        //Depósito do valor transferido na conta que recebeu
        contaDestinataria.depositarTranferencia(valorTransferencia, this);

        this.saldo = this.saldo.subtract(valorTransferencia);

        this.transacoes.add(new Transacao(new Date(), ("Tranferência para " + contaDestinataria.getNumeroConta()),
                valorTransferencia));
    }

    // Bev
    private void depositarTranferencia(BigDecimal valorTransferencia, ContaCorrente remetente) {
        this.saldo = this.saldo.add(valorTransferencia);

        this.transacoes.add(new Transacao(new Date(), ("Tranferência de " + remetente.getNumeroConta()),
                valorTransferencia));
    }

    // Ciro
    public String consultarSaldo() {
        return this.saldo.toString();
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
        Date hoje = new Date();

        // Erros de usuário
        if(dataInicio.after(dataFim)) {
            throw new RuntimeException("Data inicial é mais recente que a data final!");
        }

        if((dataInicio.after(hoje)) || (dataFim.after(hoje))) {
            throw new RuntimeException("Não de pode escolher uma data no futuro!");
        }

        ArrayList<Transacao> extrato = new ArrayList<Transacao>();

        for(int i = 0; i < this.transacoes.size(); i++) {
            Date dataTransacao = this.transacoes.get(i).getData();

            if((dataTransacao.after(dataInicio)) && (dataTransacao.before(dataFim))) {
                extrato.add(this.transacoes.get(i));
            }
        }

        return extrato;
    }

    /**
     * Método para descobrir se o valor é um número positivo diferente de 0 e somente
     * duas casas decimais.
     */
    // Lucas Silva
    private void validarValor(BigDecimal valor) {
        if(valor.doubleValue() <= 0.00) {
            throw new RuntimeException("Valor inválido!");
        }

        if(valor.scale() > 2) {
            throw new RuntimeException("Valor não tratado!");
        }
    }
}
