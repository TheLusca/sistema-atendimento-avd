package atendimento;

//A FilaCliente armazena os nós de cada cliente

public class FilaCliente implements IFilaCliente {

    public int qtdEl;
    public No inicio;

    public FilaCliente() {
        this.qtdEl = 0;
        this.inicio = null;
    }

    @Override
    public void enqueue(int horaDeEntrada) {
        No novo = new No(horaDeEntrada);
        if (isEmpty()) {
            novo.next = null;
            this.inicio = novo;
            qtdEl++;

        } else {
            No auxiliar = inicio;
            while (auxiliar.next != null) {
                auxiliar = auxiliar.next;
            }
            auxiliar.next = novo;
            qtdEl++;
        }
    }

    @Override
    public int dequeue() {
        if (isEmpty()) {
            return -1;
        } else {
            No retorno = inicio;
            inicio = inicio.next;
            qtdEl--;
            return retorno.c.horaDeEntrada;
        }
    }

    @Override
    public int front() {
        if (isEmpty()) {
            return -1;
        } else {
            return this.inicio.c.horaDeEntrada;
        }
    }

    @Override
    public boolean isEmpty() {
        return this.qtdEl == 0;
    }

    @Override
    public int size() {
        return this.qtdEl;
    }

    @Override
    public void show() {
        No auxiliar = inicio;
        if (!isEmpty()) {
            while (auxiliar != null) {
                System.out.print(auxiliar.c.horaDeEntrada + ", ");
                auxiliar = auxiliar.next;
            }
        }
    }

}
