package BancoDigital;

public class ContaCorrente extends Conta {
    private final double limiteCredito;

    public ContaCorrente(final int numeroConta, final Cliente cliente, final double limiteCredito) {
        super(numeroConta, cliente);
        this.limiteCredito = limiteCredito;
    }

    @Override
    public void sacar(final double valor) throws SaldoInsuficienteException {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor de saque deve ser positivo.");
        }
        if (getSaldo() + limiteCredito >= valor) {
            super.sacar(valor);
        } else {
            throw new SaldoInsuficienteException("Saldo insuficiente, mesmo considerando o limite de crédito.");
        }
    }

    @Override
    public void transferir(final double valor, final Iconta destino) throws SaldoInsuficienteException {
        if (getSaldo() + limiteCredito >= valor) {
            this.sacar(valor);
            destino.depositar(valor);
            System.out.println("Transferência de R$" + valor + " realizada com sucesso.");
        } else {
            throw new SaldoInsuficienteException("Saldo insuficiente para a transferência, mesmo considerando o limite de crédito.");
        }
    }

    @Override
    public void mostrarDetalhes() {
        System.out.println("Conta Corrente - Número: " + getNumeroConta() + ", Saldo: R$" + getSaldo() + ", Limite de Crédito: R$" + limiteCredito);
    }
}
