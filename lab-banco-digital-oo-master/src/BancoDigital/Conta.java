package BancoDigital;

import java.util.ArrayList;
import java.util.List;

public abstract class Conta implements Iconta {
    private final int numeroConta;
    protected double saldo;
    private final Cliente cliente;
    private final List<Transacao> transacoes;

    public Conta(final int numeroConta, final Cliente cliente) {
        this.numeroConta = numeroConta;
        this.cliente = cliente;
        this.saldo = 0.0;
        this.transacoes = new ArrayList<>();
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    protected void adicionarTransacao(final String tipo, final double valor) {
        Transacao transacao = new Transacao(tipo, valor, this);
        transacoes.add(transacao);
    }

    @Override
    public void depositar(final double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de depósito deve ser positivo.");
        }
        saldo += valor;
        adicionarTransacao("Depósito", valor);
        System.out.println("Depósito de R$" + valor + " realizado com sucesso.");
    }

    @Override
    public void sacar(final double valor) throws SaldoInsuficienteException {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de saque deve ser positivo.");
        }
        if (saldo >= valor) {
            saldo -= valor;
            adicionarTransacao("Saque", valor);
            System.out.println("Saque de R$" + valor + " realizado com sucesso.");
        } else {
            throw new SaldoInsuficienteException("Saldo insuficiente.");
        }
    }

    @Override
    public void transferir(final double valor, final Iconta destino) throws SaldoInsuficienteException {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de transferência deve ser positivo.");
        }
        this.sacar(valor);
        destino.depositar(valor);
        System.out.println("Transferência de R$" + valor + " realizada com sucesso.");
    }

    @Override
    public void mostrarHistoricoTransacoes() {
        if (transacoes.isEmpty()) {
            System.out.println("Nenhuma transação registrada.");
        } else {
            System.out.println("Histórico de transações da conta " + numeroConta + ":");
            transacoes.forEach(Transacao::registrar);
        }
    }

    public abstract void mostrarDetalhes();
}
