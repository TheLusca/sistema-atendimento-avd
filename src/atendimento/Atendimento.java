package atendimento;


import java.util.Random;

public class Atendimento {

    // Instanciando a classe Random para gerar números aleatórios
    public static Random randomico = new Random();

    static int valorAleatorio = gerarValorAleatorio(60, 120);
    static int valorAleatorio1 = gerarValorAleatorio(60, 120);
    static int valorAleatorio2 = gerarValorAleatorio(60, 120);

    public static int gerarValorAleatorio(int limiteMin, int limiteMax) {
        Random random = new Random();
        return random.nextInt(limiteMax - limiteMin + 1) + limiteMin;
    }

    public static void main(String[] args) {

        // Instanciando as estruturas que serão utilizadas
        FilaCliente fila = new FilaCliente();
        ListaGuiche lista = new ListaGuiche(5);

        // Criação das variáveis que serão utilizadas
        int totalClientes, saques, depositos, pagamentos, somaEspera, tempoTransacao;
        totalClientes = saques = depositos = pagamentos = somaEspera = tempoTransacao = 0;
        double mediaEspera = 0;

        // Criando e adicionando guichês numa lista
        Guiche g1 = new Guiche(true);
        Guiche g2 = new Guiche(true);
        Guiche g3 = new Guiche(true);
        Guiche g4 = new Guiche(true);
        Guiche g5 = new Guiche(true);
        lista.inserir(g1);
        lista.inserir(g2);
        lista.inserir(g3);
        lista.inserir(g4);
        lista.inserir(g5);

        int tempo = 0;
        int tempoExtra = 0;

        while (tempo <= 21600 || !fila.isEmpty()) { // Expediente

            if (tempo > 21600) { // Tempo extra
                tempoExtra++;
            }

            if (tempo <= 21600) { // Impedir a chegada de clientes depois do fim do expediente

                if (chegouCliente()) {
                    fila.enqueue(tempo); // Adiciona um cliente na fila com o segundo atual
                    totalClientes++;
                }
            }

            if (guicheLivre(lista) && !fila.isEmpty()) { // Verifica se tem um guichê livre e a fila não está vazia

                somaEspera += tempo - fila.dequeue(); // Soma o tempo de espera de cada cliente

                for (int i = 0; i <= 2; i++) { // Verifica qual guichê está livre
                    if (lista.obter(i).livre) {
                        lista.obter(i).livre = false; // Ocupa o guichê
                        int r = randomico.nextInt(3);
                        switch (r) {
                            case 0:
                                // Transação de saque
                                int valorAleatorio = gerarValorAleatorio(60, 120);
                                tempoTransacao = tempo + valorAleatorio;
                                System.out.println("saque");
                                System.out.println(tempoTransacao);
                                System.out.println(valorAleatorio);

                                saques++;
                                break;
                            case 1:
                                // Transação de depósito
                                int valorAleatorio1 = gerarValorAleatorio(121, 242);
                                tempoTransacao = tempo + valorAleatorio1;
                                System.out.println("deposito");
                                System.out.println(tempoTransacao);
                                System.out.println(valorAleatorio1);

                                depositos++;
                                break;
                            case 2:
                                // Transação de pagamento
                                int valorAleatorio2 = gerarValorAleatorio(485, 970);
                                tempoTransacao = tempo + valorAleatorio2;
                                System.out.println("pagamento");
                                System.out.println(tempoTransacao);
                                System.out.println(valorAleatorio2);

                                pagamentos++;
                                break;
                        }
                        lista.obter(i).tempoOcupado = tempoTransacao; // Indica o tempo que o guichê ficará ocupado
                        System.out.println(r);
                        break;
                    }
                }
            }

            for (int i = 0; i <= 2; i++) {
                if (lista.obter(i).livre == false && tempo == lista.obter(i).tempoOcupado) {
                    lista.obter(i).livre = true; // Se o cliente terminou a transação, o guichê fica livre
                }
            }

            tempo++;

        }

        if (totalClientes > 0) {
            mediaEspera = somaEspera / totalClientes;
        }

        // Cálculos utilizados para processar as saídas
        int segMedia = (int) mediaEspera % 60;
        mediaEspera /= 60;
        int minMedia = (int) mediaEspera % 60;

        int segExtra = (int) tempoExtra % 60;
        tempoExtra /= 60;
        int minExtra = (int) tempoExtra % 60;

        // Resultados
        System.out.println("Número total de clientes: " + totalClientes);
        System.out.println("Número de clientes que fizeram saque: " + saques);
        System.out.println("Número de clientes que fizeram depósito: " + depositos);
        System.out.println("Número de clientes que fizeram pagamento: " + pagamentos);
        System.out.println("Tempo médio de espera: " + minMedia + " minutos e " + segMedia + " segundos.");
        System.out.println("Tempo extra de expediente: " + minExtra + " minutos e " + segExtra + " segundos.");

    }

    // Retorna true se um cliente chegou
    public static boolean chegouCliente() {
        int r = randomico.nextInt(30);
        return r == 0;
    }

    // Verifica se há um guichê livre
    public static boolean guicheLivre(ListaGuiche lista) {
        boolean retorno = false;
        for (int i = 0; i <= 2; i++) {
            if (lista.checar(i)) {
                retorno = true;
                break;
            }
        }
        return retorno;

    }

}
