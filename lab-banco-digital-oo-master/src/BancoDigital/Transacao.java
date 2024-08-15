package BancoDigital;

import java.time.LocalDateTime;

public class Transacao {
    private String tipo;
    private double valor;
    private LocalDateTime dataHora;
    private Conta conta;

    public Transacao(String tipo, double valor, Conta conta) {
        this.tipo = tipo;
        this.valor = valor;
        this.conta = conta;
        this.dataHora = LocalDateTime.now();
    }

    public void registrar() {
        System.out.println(tipo + " de R$" + valor + " em " + dataHora + " na conta " + conta.getNumeroConta());
    }

    public String getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public Conta getConta() {
        return conta;
    }
}
