package atendimento;

/*
O uso da interface é para garantir que os métodos
da fila não sejam alterados
*/

public interface IFilaCliente {

    public void enqueue(int horaDeEntrada);
    public int dequeue();
    public int front();
    public boolean isEmpty();
    public int size();
    public void show();
}
