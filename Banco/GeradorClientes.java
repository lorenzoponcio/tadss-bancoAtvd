import java.util.Random;

public class GeradorClientes implements Runnable {

    private FilaClientes fila;
    private int duracao = 7200; // 2 horas em segundos
    private Random random = new Random();

    public GeradorClientes(FilaClientes fila) {
        this.fila = fila;
    }

    @Override
    public void run() {

        int id = 1;

        while (RelogioSimulacao.tempoAtual() < duracao) {

            int intervalo = 5 + random.nextInt(46); // 5 a 50

            try {
                Thread.sleep(intervalo * 10); // acelerado
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            Cliente c = new Cliente(id++, RelogioSimulacao.tempoAtual());
            fila.adicionar(c);

            //System.out.println("Cliente chegou: " + c.getId());
        }

        fila.setFim();
        System.out.println("Gerador terminou");
    }
}