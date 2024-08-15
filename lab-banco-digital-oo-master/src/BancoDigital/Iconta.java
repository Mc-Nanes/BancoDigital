package BancoDigital;

public interface Iconta {
    void depositar(double valor);
    void sacar(double valor) throws SaldoInsuficienteException;
    void transferir(double valor, Iconta destino) throws SaldoInsuficienteException;
    void mostrarHistoricoTransacoes();
    void mostrarDetalhes();
}
