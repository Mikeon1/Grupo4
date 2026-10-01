import static java.lang.Math.max;

public class Item {

    // Atributos privados

    private String nome;
    private String descricao;
    private String tipo;
    private int valorCura;
    private int valorDano;
    private int quantidade;

    // Construtor

    public Item(String nomeRecebido, String descricaoRecebida, String tipoRecebido, int curaRecebida, int danoRecebido) {
        this.nome = nomeRecebido;
        this.descricao = descricaoRecebida;
        this.tipo = tipoRecebido;
        this.valorCura = max(0, curaRecebida);
        this.valorDano = max(0, danoRecebido);
        this.quantidade = 1;
    }
    // Métodos

    public String getNome() {
        return nome;
    }

    public void mostrarInformacoes() {
        System.out.println("Item: " + nome);
        System.out.println("Descrição: " + descricao);
        System.out.println("Tipo: " + tipo);
        System.out.println("Quantidade: " + quantidade);
    }

    public void aumentarQuantidade(int valor){
        if (valor > 0){
            quantidade = quantidade + valor;
        }
    }

    public boolean acabou() {
        return quantidade <= 0;
    }

    // Método usar

    public void usar() {
        if (acabou()) {
            System.out.println("Este item acabou.");
            return;
        }

    }

}