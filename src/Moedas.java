public class Moedas extends Item{

        private int quantidadeMoedas;

        public Moedas(int quantidadeMoedas){
            super("Moedas","Usadas para transações","MOEDA",0,0);
            this.quantidadeMoedas = quantidadeMoedas;
        }
// Getter e Setter
    public int getQuantidadeMoedas() {
        return quantidadeMoedas;
    }

    public void setQuantidadeMoedas(int quantidadeMoedas) {
        this.quantidadeMoedas = quantidadeMoedas;
    }
}








