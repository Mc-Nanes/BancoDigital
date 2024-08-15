package BancoDigital;

public class ContaCorrente extends Conta {
    private final double limiteCredito;

    public ContaCorrente(final int numeroConta, final Cliente cliente, final double limiteCredito) {
        super(numeroConta, cliente);
        this.limiteCredito = limiteCredito;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    @Override
    public ContaCorrente sacar(final double valor) throws SaldoInsuficienteException {
        validarValorPositivo(valor, "Valor de saque deve ser positivo.");
        if (getSaldo() + limiteCredito >= valor) {
            super.sacar(valor); // Método da classe pai, sem mensagem
        } else {
            throw new SaldoInsuficienteException("Saldo insuficiente, mesmo considerando o limite de crédito.");
        }
        return this;
    }

    @Override
    public ContaCorrente transferir(final double valor, final Iconta destino) throws SaldoInsuficienteException {
        if (getSaldo() + limiteCredito >= valor) {
            super.transferir(valor, destino); // Método da classe pai, sem mensagem
        } else {
            throw new SaldoInsuficienteException("Saldo insuficiente para a transferência, mesmo considerando o limite de crédito.");
        }
        return this;
    }

    @Override
    public void mostrarDetalhes() {
        System.out.println("Conta Corrente - Número: " + getNumeroConta() + ", Saldo: R$" + getSaldo() + ", Limite de Crédito: R$" + limiteCredito);
    }
}
