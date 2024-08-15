package BancoDigital;

import java.util.ArrayList;
import java.util.Collections;
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

    private void adicionarTransacao(final String tipo, final double valor) {
        transacoes.add(new Transacao(tipo, valor, this));
    }

    @Override
    public Conta depositar(final double valor) {
        return depositar(valor, true);
    }

    @Override
    public Conta depositar(final double valor, final boolean exibirMensagem) {
        validarValorPositivo(valor, "Valor de depósito deve ser positivo.");
        saldo += valor;
        adicionarTransacao("Depósito", valor);
        if (exibirMensagem) {
            System.out.println("Depósito de R$" + valor + " realizado com sucesso.");
        }
        return this;
    }

    @Override
    public Conta sacar(final double valor) throws SaldoInsuficienteException {
        return sacar(valor, true);
    }

    @Override
    public Conta sacar(final double valor, final boolean exibirMensagem) throws SaldoInsuficienteException {
        validarValorPositivo(valor, "Valor de saque deve ser positivo.");
        if (saldo >= valor) {
            saldo -= valor;
            adicionarTransacao("Saque", valor);
            if (exibirMensagem) {
                System.out.println("Saque de R$" + valor + " realizado com sucesso.");
            }
        } else {
            throw new SaldoInsuficienteException("Saldo insuficiente.");
        }
        return this;
    }

    @Override
    public Conta transferir(final double valor, final Iconta destino) throws SaldoInsuficienteException {
        boolean mensagemAnterior = true;
        try {
            mensagemAnterior = false; // Desativar exibição de mensagens
            sacar(valor, false); // Registrar saque sem mensagem
            destino.depositar(valor, false); // Registrar depósito sem mensagem
            System.out.println("Transferência de R$" + valor + " realizada com sucesso.");
        } finally {
            // Restaurar o estado original da exibição de mensagens
            mensagemAnterior = true;
        }
        return this;
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

    public List<Transacao> getTransacoes() {
        return Collections.unmodifiableList(transacoes);
    }

    public abstract void mostrarDetalhes();

    protected void validarValorPositivo(double valor, String mensagemErro) {
        if (valor <= 0) {
            throw new IllegalArgumentException(mensagemErro);
        }
    }
}
