package BancoDigital;

import java.time.LocalDateTime;

public final class Transacao {
    private final String tipo;
    private final double valor;
    private final LocalDateTime dataHora;
    private final int numeroConta;

    public Transacao(String tipo, double valor, Conta conta) {
        this.tipo = tipo;
        this.valor = valor;
        this.numeroConta = conta.getNumeroConta();
        this.dataHora = LocalDateTime.now();
    }

    public String getTipo() {
        return tipo;
    }
    
    public double getValor() {
        return valor;
    }
    
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    
    public int getNumeroConta() {
        return numeroConta;
    }

    public void registrar() {
        System.out.println(getTipo() + " de R$" + getValor() + " em " + getDataHora() + " na conta " + getNumeroConta());
    }
}
