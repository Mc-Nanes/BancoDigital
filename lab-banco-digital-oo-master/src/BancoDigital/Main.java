package BancoDigital;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("João Silva", "123.456.789-00", "Rua A, 123");
        Cliente cliente2 = new Cliente("Maria Oliveira", "987.654.321-00", "Rua B, 456");

        ContaCorrente contaCorrente = new ContaCorrente(1, cliente1, 1000);
        ContaPoupanca contaPoupanca = new ContaPoupanca(2, cliente2, 0.05);

        Banco banco = new Banco();
        banco.adicionarConta(contaCorrente);
        banco.adicionarConta(contaPoupanca);

        System.out.println("=== Teste de Depósito ===");
        contaCorrente.depositar(500);
        System.out.println("Saldo após depósito: R$" + contaCorrente.getSaldo());

        System.out.println("\n=== Teste de Saque ===");
        contaCorrente.sacar(200);
        System.out.println("Saldo após saque: R$" + contaCorrente.getSaldo());

        System.out.println("\n=== Teste de Transferência ===");
        contaCorrente.transferir(100, contaPoupanca);
        System.out.println("Saldo da Conta Corrente após transferência: R$" + contaCorrente.getSaldo());
        System.out.println("Saldo da Conta Poupança após receber transferência: R$" + contaPoupanca.getSaldo());

        System.out.println("\n=== Histórico de Transações da Conta Corrente ===");
        contaCorrente.mostrarHistoricoTransacoes();

        System.out.println("\n=== Teste de Aplicação de Juros na Conta Poupança ===");
        contaPoupanca.aplicarRendimento();
        System.out.println("Saldo da Conta Poupança após aplicação de rendimento: R$" + contaPoupanca.getSaldo());

        System.out.println("\n=== Histórico de Transações da Conta Poupança ===");
        contaPoupanca.mostrarHistoricoTransacoes();

        System.out.println("\n=== Listagem de Contas no Banco ===");
        banco.listarContas();

        
        System.out.println("\n=== Buscando uma Conta ===");
        Optional<Conta> contaBuscada = banco.buscarConta(1);
        contaBuscada.ifPresentOrElse(
            conta -> System.out.println("Conta encontrada: " + conta.getNumeroConta()),
            () -> System.out.println("Conta não encontrada.")
        );
    }
}
