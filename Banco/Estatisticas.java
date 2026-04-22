public class Estatisticas {

    private int clientesAtendidos = 0;
    private long somaEspera = 0;
    private long somaTotal = 0;
    private long maxEspera = 0;
    private long maxAtendimento = 0;

    public synchronized void registrar(Cliente c) {

        long espera = c.getTempoInicioAtendimento() - c.getTempoChegada();
        long atendimento = c.getTempoFimAtendimento() - c.getTempoInicioAtendimento();
        long total = c.getTempoFimAtendimento() - c.getTempoChegada();

        clientesAtendidos++;
        somaEspera += espera;
        somaTotal += total;

        if (espera > maxEspera) maxEspera = espera;
        if (atendimento > maxAtendimento) maxAtendimento = atendimento;
    }

    public void imprimir(int nAtendentes) {

        double mediaEspera = clientesAtendidos > 0 ? (double) somaEspera / clientesAtendidos : 0;
        double leadTime = clientesAtendidos > 0 ? (double) somaTotal / clientesAtendidos : 0;

        System.out.println("\n-RESULTADO COM " + nAtendentes + " ATENDENTES-");
        System.out.println("Clientes atendidos: " + clientesAtendidos);
        System.out.println("Tempo médio de espera: " + mediaEspera + " s");
        System.out.println("Tempo máximo de espera: " + maxEspera + " s");
        System.out.println("Tempo máximo de atendimento: " + maxAtendimento + " s");
        System.out.println("Lead time médio: " + leadTime + " s");

        if (mediaEspera <= 120) {
            System.out.println("Meta atingida");
        } else {
            System.out.println("Meta NÃO atingida");
        }
    }

    public double getMediaEspera() {
        return clientesAtendidos > 0 ? (double) somaEspera / clientesAtendidos : 0;
    }
}