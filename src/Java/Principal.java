package Java;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Principal {
    public static void main(String[] args) {
        Pessoa titular = new Pessoa("Fulano", "123.123.123.12", new Date());
        BigDecimal valorTratado = new BigDecimal(50.00, new MathContext(4));
        BigDecimal valorNaoTratado = new BigDecimal(50.003);

        String numeroConta403508 = "4035-08";
        ContaCorrente contaCorrente403508;

        String numeroConta264700 = "2647-00";
        ContaCorrente contaCorrente2264700;

        // Exemplo Mykaeli
        try {
            contaCorrente403508 = new ContaCorrente(numeroConta403508, titular);

            contaCorrente403508.depositar(valorTratado);

            System.out.println("Valor depositado: " + contaCorrente403508.consultarSaldo() + "\n");
        } catch(Exception exception) {
            System.out.println(exception.getMessage());
        }

        // Exemplo Lucas Silva
        try {
            contaCorrente403508 = new ContaCorrente(numeroConta403508, titular);

            contaCorrente403508.depositar(valorNaoTratado);
        } catch(Exception exception) {
            System.out.println("Erro: " + exception.getMessage() + "\n");
        }

        // Exemplo Rodrigo
        try {
            contaCorrente403508 = new ContaCorrente(numeroConta403508, titular, valorTratado);

            contaCorrente403508.sacar(valorTratado);

            System.out.println("Valor após o saque: " + contaCorrente403508.consultarSaldo() + "\n");
        } catch(Exception exception) {
            System.out.println(exception.getMessage());
        }

        // Exemplo Bev
        try {
            contaCorrente403508 = new ContaCorrente(numeroConta403508, titular, valorTratado);
            contaCorrente2264700 = new ContaCorrente(numeroConta264700, titular);

            System.out.println("Valor do saldo da conta " + contaCorrente403508.getNumeroConta() + ": " + contaCorrente403508.consultarSaldo());
            System.out.println("Valor do saldo  da conta " + contaCorrente2264700.getNumeroConta() + ": " + contaCorrente2264700.consultarSaldo());

            contaCorrente403508.transferir(contaCorrente2264700, valorTratado);

            System.out.println("Valor do saldo  da conta " + contaCorrente403508.getNumeroConta() + ": " + contaCorrente403508.consultarSaldo());
            System.out.println("Valor do saldo  da conta " + contaCorrente2264700.getNumeroConta() + ": " + contaCorrente2264700.consultarSaldo());
        } catch(Exception exception) {
            System.out.println(exception.getMessage());
        }

        // Ciro
        try {
            contaCorrente403508 = new ContaCorrente(numeroConta403508, titular);

            contaCorrente403508.depositar(valorTratado);
            contaCorrente403508.sacar(valorTratado);

            Calendar calendario = Calendar.getInstance();
            calendario.set(2022, Calendar.SEPTEMBER, 10);
            Date dataInicial = calendario.getTime();

            ArrayList<Transacao> extrato = contaCorrente403508.consultarExtrato(dataInicial, new Date());

            System.out.println("\nTransações:");
            for(int i = 0; i < extrato.size(); i++) {
                Transacao transacao = extrato.get(i);

                System.out.println("Data/Hora: " + transacao.getData() + " Valor: " + transacao.getValor() +
                        "\nDescrição:" + transacao.getDescricao());
            }
        } catch(Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
