public class Protagonista {

private String nome;
private static int vida;
private int dano;
private int defesa;
private double esquiva;
private Inventario inventario;
private final int vidamaxima;

public Protagonista(String nome, int vida, int dano,int defesa,double esquiva){
    this.nome=nome;
    this.vida=vida;
    this.dano=dano;
    this.defesa=defesa;
    this.esquiva=esquiva;
    this.vidamaxima=vida;
    this.inventario = new Inventario();
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
        public int recuperarVida(int valorCura) {
            return vida + valorCura;
        }

    public void equiparArma(int valorDano) {
        this.dano += valorDano;
    }

    // GETTERS E SETTERS

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public int getDefesa() {
        return defesa;
    }

    public void setDefesa(int defesa) {
        this.defesa = defesa;
    }

    public double getEsquiva() {
        return esquiva;
    }

    public void setEsquiva(double esquiva) {
        this.esquiva = esquiva;
    }

    public int getVidamaxima() {
        return vidamaxima;
    }

    public Inventario getInventario() {
        return inventario;
    }

}