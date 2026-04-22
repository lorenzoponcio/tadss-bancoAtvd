public class SimulacaoBanco {

    public static void main(String[] args) throws Exception {

        for (int n = 1; n <= 5; n++) {

            FilaClientes fila = new FilaClientes(1000);
            Estatisticas estatisticas = new Estatisticas();

            RelogioSimulacao.iniciar();

            GeradorClientes gerador = new GeradorClientes(fila);
            Thread tg = new Thread(gerador, "Gerador");
            tg.start();

            Thread[] atendentes = new Thread[n];

            for (int i = 0; i < n; i++) {
                Atendente a = new Atendente(fila, estatisticas);
                atendentes[i] = new Thread(a, "Atendente-" + (i + 1));
                atendentes[i].start();
            }

            tg.join();

            for (Thread t : atendentes) {
                t.join();
            }

            estatisticas.imprimir(n);
        }
    }
}