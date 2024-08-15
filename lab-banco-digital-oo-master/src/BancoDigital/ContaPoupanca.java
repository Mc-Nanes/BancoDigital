package BancoDigital;

public class ContaPoupanca extends Conta {
    private final double taxaJuros;

    public ContaPoupanca(final int numeroConta, final Cliente cliente, final double taxaJuros) {
        super(numeroConta, cliente);
        this.taxaJuros = taxaJuros;
    }

    public void aplicarRendimento() {
        double rendimento = getSaldo() * taxaJuros;
        if (rendimento > 0) {
            depositar(rendimento);
            adicionarTransacao("Rendimento", rendimento);
            System.out.println("Rendimento aplicado. Novo saldo: R$" + getSaldo());
        } else {
            throw new IllegalArgumentException("Rendimento inválido.");
        }
    }

    @Override
    public void mostrarDetalhes() {
        System.out.println("Conta Poupança - Número: " + getNumeroConta() + ", Saldo: R$" + getSaldo() + ", Taxa de Juros: " + taxaJuros * 100 + "%");
    }
}
