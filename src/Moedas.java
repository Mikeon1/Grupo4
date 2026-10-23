public class Moedas extends Item{

        private int tiposMoedas;

        public Moedas(String tipoMoeda, int valor) {
            super("Moeda de " + tipoMoeda, "Usada para transações e compras.", "MOEDA", 0, 0);
            this.tiposMoedas = valor;
        }

        public int getTiposMoedas() {
            return tiposMoedas;
        }
    }






