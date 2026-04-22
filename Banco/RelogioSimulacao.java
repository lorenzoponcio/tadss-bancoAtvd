public class RelogioSimulacao {

    private static long inicio;
    private static int fator = 10; // 1 segundo simulado = 10 ms reais

    public static void iniciar() {
        inicio = System.currentTimeMillis();
    }

    public static long tempoAtual() {
        return (System.currentTimeMillis() - inicio) / fator;
    }
}