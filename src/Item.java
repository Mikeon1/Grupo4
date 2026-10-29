import static java.lang.Math.max;

public class Item {
        // Atributos privados

        private String Nome;
        private String descricao;
        private String tipo;
        private int valorCura;
        private int valorDano;
        private int quantidade;

        // Construtor

        public Item(String nomeRecebido, String descricaoRecebida, String tipoRecebido, int curaRecebida, int danoRecebido) {
            this.Nome = nomeRecebido;
            this.descricao = descricaoRecebida;
            this.tipo = tipoRecebido;
            this.valorCura = max(0, curaRecebida);
            this.valorDano = max(0, danoRecebido);
            this.quantidade = 1;
        }
        // Métodos

        public String GetNome() {
            return Nome;
        }

        public void mostrarInformacoes() {
            System.out.println("Item: " + Nome);
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

   /* public void usar( Protagonista) {
        if (acabou()) {
            System.out.println("Este item acabou.");
            return;
        }

        if (tipo.equals("CURA")) {
            int curaReal = Protagonista.recuperarVida(valorCura);
            if (curaReal == 0){
                System.out.println("A vida já está cheia.");
                return;
            }
            quantidade = quantidade - 1;
            System.out.println("Vida recuperada: " + curaReal);
        } else if (tipo.equals("ARMA")) {
            Protagonista.equiparArma(valorDano);
            System.out.println("Arma equipada: " + Nome);
        }
        else {
            System.out.println("Esse item não pode ser usado agora.");
        }
    }*/

    }

