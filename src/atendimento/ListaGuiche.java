package atendimento;

/*
A lista de guichês é utilizada para auxiliar o
gerenciamento do conjunto de guichês.
Como o número de guichês é fixo e conhecido, é necessário
apenas uma lista estática
*/

public class ListaGuiche {

    public int qtdEl;
    public Guiche lista[];
    public int inicio;
    public int fim;

    public ListaGuiche(int tamanho) {
        this.qtdEl = 0;
        this.lista = new Guiche[tamanho];
        this.inicio = 0;
        this.fim = 0;
    }

    public boolean inserir(Guiche valor) {
        if (listaCheia()) {
            return false;

        } else if (listaVazia()) {

            this.lista[inicio] = valor;
            this.qtdEl++;
            this.inicio++;
            this.fim++;
            return true;

        } else {

            this.lista[qtdEl] = valor;
            this.qtdEl++;
            this.fim++;
            return true;
        }
    }

    public boolean checar(int indice) {
        return lista[indice].livre;
    }

    public Guiche obter(int indice) {
        return lista[indice];
    }

    public int tamanho() {
        return this.qtdEl;
    }

    public boolean listaVazia() {
        return this.qtdEl == 0;
    }

    public boolean listaCheia() {
        return this.qtdEl == lista.length;
    }

}
