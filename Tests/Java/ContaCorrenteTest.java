package Java;

import JavaErrors.InsufficientFundsException;
import JavaErrors.InvalidDateException;
import JavaErrors.MissingMotiveException;
import JavaErrors.ValueNotHandledException;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContaCorrenteTest {
    static Pessoa titular;
    static ContaCorrente contaCorrente;

    String ERRO_NAO_DESEJADO = "Erro dejado não foi lançado!";

    @BeforeAll
    static void antesDeTudo() {
        titular = new Pessoa("Fulano", "123.123.123.12", new Date());
    }

    @BeforeEach
    void antesDeCada() {
        contaCorrente = new ContaCorrente("123", titular);
    }

    // Construtores
    @Test
    @DisplayName("Deve ser possível criar uma conta corrente com saldo")
    void criarContaCorrente() {
        BigDecimal valorTratado = new BigDecimal(50.00);
        ContaCorrente contaCorrenteComSaldo;

        contaCorrenteComSaldo = new ContaCorrente("123", titular, valorTratado);

        assert(contaCorrenteComSaldo.consultarSaldo()).equals(valorTratado);
    }

    @Test
    @DisplayName("Não deve ser possível criar uma conta corrente com saldo de valor inválido")
    void criarContaCorrenteComValorInvalido() {
        BigDecimal valorTratado = new BigDecimal(50.003);

        ValueNotHandledException thrown = assertThrows(
                ValueNotHandledException.class,
                () -> new ContaCorrente("123", titular, valorTratado),
                ERRO_NAO_DESEJADO
        );
    }

    // Consultar Saldo
    @Test
    @DisplayName("Deve ser possivel consultar o saldo")
    void consultandoSaldo() {
        BigDecimal valorTratado = new BigDecimal(50.00);
        ContaCorrente contaCorrenteComSaldo = new ContaCorrente("123", titular, valorTratado);

        assert(contaCorrenteComSaldo.consultarSaldo()).equals(valorTratado);
    }

    // Deposito
    @Test
    @DisplayName("Deve ser possível depositar um valor tratado")
    void depositoValido() {
        ContaCorrente contaCorrente = new ContaCorrente("123", titular);
        BigDecimal valorTratado = new BigDecimal(50.00);

        contaCorrente.depositar(valorTratado);

        assert(contaCorrente.consultarSaldo()).equals(valorTratado);
    }

    @Test
    @DisplayName("Não deve ser possível depositar um valor igual a 0")
    void depositoValorIgualA0() {
        BigDecimal valor0 = new BigDecimal(0);

        ValueNotHandledException thrown = assertThrows(
                ValueNotHandledException.class,
                () -> contaCorrente.depositar(valor0),
                ERRO_NAO_DESEJADO
        );
    }

    @Test
    @DisplayName("Não deve ser possível depositar um valor menor que 0")
    void depositoValorMenorQue0() {
        BigDecimal valorMenorQue0 = new BigDecimal(-10);

        ValueNotHandledException thrown = assertThrows(
                ValueNotHandledException.class,
                () -> contaCorrente.depositar(valorMenorQue0),
                ERRO_NAO_DESEJADO
        );
    }

    @Test
    @DisplayName("Não deve ser possível depositar um valor não tratado")
    void depositoValorNaoTratado() {
        BigDecimal valorNaoTratado = new BigDecimal(50.003);


        ValueNotHandledException thrown = assertThrows(
                ValueNotHandledException.class,
                () -> contaCorrente.depositar(valorNaoTratado),
                ERRO_NAO_DESEJADO
        );
    }

    // Saque
    @Test
    @DisplayName("Deve ser possivel sacar um valor")
    void saqueValido() {
        BigDecimal valorTratado = new BigDecimal(50.00);
        ContaCorrente contaCorrenteComSaldo = new ContaCorrente("123", titular, valorTratado);

        contaCorrenteComSaldo.sacar(valorTratado);

        assert(contaCorrenteComSaldo.consultarSaldo()).equals(new BigDecimal(0));
    }

    @Test
    @DisplayName("Não deve ser possivel sacar com um valor invalido")
    void saqueComValorInvalido() {
        BigDecimal valorTratado = new BigDecimal(50.00);
        ContaCorrente contaCorrenteComSaldo = new ContaCorrente("123", titular, valorTratado);

        ValueNotHandledException thrown = assertThrows(
                ValueNotHandledException.class,
                () -> contaCorrenteComSaldo.sacar(new BigDecimal(49.999)),
                ERRO_NAO_DESEJADO
        );
    }

    @Test
    @DisplayName("Não deve ser possivel sacar com um valor maior que o saldo da conta")
    void saqueComValorMaiorQueSaldo() {
        BigDecimal valorTratado = new BigDecimal(50.00);

        InsufficientFundsException thrown = assertThrows(
                InsufficientFundsException.class,
                () -> contaCorrente.sacar(valorTratado),
                ERRO_NAO_DESEJADO
        );
    }

    // Transferencia
    @Test
    @DisplayName("Deve ser possivel realizar uma tranferencia de valor entre contas")
    void tranferenciaValida() {
        BigDecimal valorTratado = new BigDecimal(50.00);
        ContaCorrente contaCorrenteComSaldo = new ContaCorrente("123", titular, valorTratado);

        contaCorrenteComSaldo.transferir(contaCorrente, valorTratado);

        assert(contaCorrenteComSaldo.consultarSaldo()).equals(new BigDecimal(0.00));
        assert(contaCorrente.consultarSaldo()).equals(valorTratado);
    }

    @Test
    @DisplayName("Não deve ser possivel realizar uma tranferencia se o valor for inválido")
    void tranferenciaValorInvalido() {
        BigDecimal valorTratado = new BigDecimal(50.00);
        ContaCorrente contaCorrenteComSaldo = new ContaCorrente("123", titular, valorTratado);

        ValueNotHandledException thrown = assertThrows(
                ValueNotHandledException.class,
                () -> contaCorrenteComSaldo.transferir(contaCorrente, new BigDecimal(49.999)),
                ERRO_NAO_DESEJADO
        );
    }

    @Test
    @DisplayName("Não deve ser possivel realizar uma tranferencia se o valor for maior que o saldo do remetente")
    void tranferenciaSaldoInsuficiente() {
        BigDecimal valorTratado = new BigDecimal(50.00);
        ContaCorrente contaCorrenteDestinataria = new ContaCorrente("123", titular);

        InsufficientFundsException thrown = assertThrows(
                InsufficientFundsException.class,
                () -> contaCorrente.transferir(contaCorrenteDestinataria, valorTratado),
                ERRO_NAO_DESEJADO
        );
    }

    // Cancelar conta
    @Test
    @DisplayName("Deve ser possível cancelar conta")
    void cancelandoConta() {
        contaCorrente.cancelarConta("Não gostei do banco :p");

        assert(contaCorrente.getCancelada()).equals(true);
    }

    @Test
    @DisplayName("Não deve ser possível cancelar uma conta sem justificativa")
    void cancelarContaSemJustificativa() {
        MissingMotiveException thrown = assertThrows(
                MissingMotiveException.class,
                () -> contaCorrente.cancelarConta(""),
                ERRO_NAO_DESEJADO
        );
    }

    // Consultar extrato
    @Test
    @DisplayName("Deve ser possível consultar extrato")
    void consultandoExtrato() {
    }

    @Test
    @DisplayName("Não deve ser possível consultar extrato com uma incial posterior a data final")
    void consultaExtratoComDataInicialInvalida() {
        ArrayList<Transacao> extrato = contaCorrente.getTransacoes();

        Calendar calendario = Calendar.getInstance();
        calendario.set(2023, Calendar.SEPTEMBER, 10);
        Date dataInicial = calendario.getTime();

        InvalidDateException thrown = assertThrows(
                InvalidDateException.class,
                () -> contaCorrente.consultarExtrato(dataInicial, new Date()),
                ERRO_NAO_DESEJADO
        );
    }
}
