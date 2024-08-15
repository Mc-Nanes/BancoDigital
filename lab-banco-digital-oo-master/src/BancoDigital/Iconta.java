package BancoDigital;

public interface Iconta {
    Iconta depositar(double valor);
    Iconta depositar(double valor, boolean exibirMensagem);

    Iconta sacar(double valor) throws SaldoInsuficienteException;
    Iconta sacar(double valor, boolean exibirMensagem) throws SaldoInsuficienteException;

    Iconta transferir(double valor, Iconta destino) throws SaldoInsuficienteException;

    void mostrarHistoricoTransacoes();
}