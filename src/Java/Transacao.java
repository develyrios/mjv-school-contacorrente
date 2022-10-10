package Java;

import java.math.BigDecimal;
import java.util.Date;

/**
 *  A lógica por traz desta classe é poder armazenar as transações dentro de um
 *  ArrayList, um wrapper de vetor, na classe Java.ContaCorrente.
 *  - Ciro
 */
public class Transacao {
    private Date data;
    private String descricao;
    private BigDecimal valor;

    public Transacao(Date data, String descricao, BigDecimal valor) {
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
