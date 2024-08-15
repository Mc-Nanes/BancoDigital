package BancoDigital;

public class ContaPoupanca extends Conta {
    private final double taxaJuros;

    public ContaPoupanca(final int numeroConta, final Cliente cliente, final double taxaJuros) {
        super(numeroConta, cliente);
        this.taxaJuros = taxaJuros;
    }

    public ContaPoupanca aplicarRendimento() {
        double rendimento = getSaldo() * taxaJuros;
        depositar(rendimento); // Método da classe pai, sem mensagem
        System.out.println("Rendimento aplicado. Novo saldo: R$" + getSaldo());
        return this;
    }

    @Override
    public void mostrarDetalhes() {
        System.out.println("Conta Poupança - Número: " + getNumeroConta() + ", Saldo: R$" + getSaldo() + ", Taxa de Juros: " + taxaJuros * 100 + "%");
    }
}
