public class Protagonista {

private String nome;
private static int vida;
private int dano;
private int defesa;
private double esquiva;
private final int vidamaxima;

public Protagonista(String nome, int vida, int dano,int defesa,double esquiva){
    this.nome=nome;
    this.vida=vida;
    this.dano=dano;
    this.defesa=defesa;
    this.esquiva=esquiva;
    this.vidamaxima=vida;
}

    public void causarDano(int danoCausado){
        danoCausado = dano;// (+ dano arma + bonus)
    }

    public boolean estarvivo(){
        return vida > 0;
    }

    public void perderVida(int vidaperdida) {
        vida = vida - vidaperdida;
        if (vida < 0) {
            vida = 0;
        }
    }
        public static int recuperarVida(int valorCura) {
            return vida + valorCura;

        }
        public static void equiparArma(int valorDano){
// precisa esperar terminar o inventario.
        }
    }