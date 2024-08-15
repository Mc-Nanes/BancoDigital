package BancoDigital;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Banco {
    private final List<Conta> contas;

    public Banco() {
        this.contas = new ArrayList<>();
    }

    public void adicionarConta(final Conta conta) {
        if (conta != null && !contas.contains(conta)) {
            contas.add(conta);
            System.out.println("Conta adicionada com sucesso.");
        } else {
            System.out.println("A conta já existe ou é inválida.");
        }
    }

    public void removerConta(final Conta conta) {
        if (contas.remove(conta)) {
            System.out.println("Conta removida com sucesso.");
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    public Optional<Conta> buscarConta(final int numeroConta) {
        return contas.stream()
                .filter(conta -> conta.getNumeroConta() == numeroConta)
                .findFirst();
    }

    public void listarContas() {
        if (contas.isEmpty()) {
            System.out.println("Nenhuma conta registrada.");
        } else {
            contas.forEach(Conta::mostrarDetalhes);
        }
    }
}
