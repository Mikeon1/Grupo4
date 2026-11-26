import static java.lang.Math.max;

public class Item {

    private String nome;
    private String descricao;
    private String tipo;
    private int valorCura;
    private int valorDano;
    private int quantidade;

    public Item(String nomeRecebido, String descricaoRecebida, String tipoRecebido, int curaRecebida, int danoRecebido) {
        this.nome = nomeRecebido;
        this.descricao = descricaoRecebida;
        this.tipo = tipoRecebido;
        this.valorCura = max(0, curaRecebida);
        this.valorDano = max(0, danoRecebido);
        this.quantidade = 1;
    }

    public String getNome() {
        return nome;
    }

    public void mostrarInformacoes() {
        System.out.println("Item: " + nome);
        System.out.println("Descrição: " + descricao);
        System.out.println("Tipo: " + tipo);
        System.out.println("Quantidade: " + quantidade);
    }

    public void aumentarQuantidade(int valor) {
        if (valor > 0) {
            this.quantidade += valor;
        }
    }

    public boolean acabou() {
        return quantidade <= 0;
    }

    public void usar(Protagonista protagonista) {
        if (protagonista == null) {
            return;
        }
        if (acabou()) {
            System.out.println("Este item acabou.");
            return;
        }

        if ("CURA".equalsIgnoreCase(tipo)) {
            int vidaAntes = protagonista.getVida();
            int vidaDepois = protagonista.recuperarVida(valorCura);
            if (vidaDepois == vidaAntes) {
                System.out.println("A vida já está cheia.");
                return;
            }
            quantidade--;
            System.out.println("Vida recuperada: " + (vidaDepois - vidaAntes));
        } else if ("ARMA".equalsIgnoreCase(tipo)) {
            protagonista.equiparArma(valorDano);
            quantidade--;
            System.out.println("Arma equipada: " + nome);
        } else {
            System.out.println("Esse item não pode ser usado agora.");
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public int getValorCura() {
        return valorCura;
    }

    public int getValorDano() {
        return valorDano;
    }

    public int getQuantidade() {
        return quantidade;
    }
}