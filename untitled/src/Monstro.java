import static java.lang.Math.max;

public class Monstro {

    private String nome;
    private int vida;
    private int dano;
    private int defesa;
    public String tipo;
    private double esquiva;
    private final int VIDAMAX;

    public Monstro(String nome, int vida, int dano, int defesa, String tipo, double esquiva) {
        this.nome = nome;
        this.vida = vida;
        this.dano = dano;
        this.defesa = defesa;
        this.tipo = tipo;
        this.esquiva = esquiva;
        this.VIDAMAX = vida;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void perderVida(int vidaPerdida) {
        vida = vida - vidaPerdida;
        if (vida < 0) {
            vida = 0;
        }
    }

    public boolean estarVivo(){
        return vida > 0;
    }
    public void atacar(protagonista) {
          if (estarVivo()){
            return;
          }
          System.out.println(nome + " atacou!");
              protagonista.receberDano(dano);

          }
    public void receberDano(int danoRecebido){
            if(estarVivo()){
                return;
            }
          int danoFinal = max(0, danoRecebido - defesa);
                    vida = max(0, vida - danoFinal);
        System.out.println(nome + " recebeu" + danoFinal + " de dano");
        derrotado();
          }
    public void derrotado(){
        if(estarVivo());
        System.out.println(nome + " foi derrotado!");
    }
    public void mastrarStatus(){
        System.out.println(" Monstro: " + nome);
        System.out.println(" Vida: " + vida + " /" + VIDAMAX);
        System.out.println(" Dano: " + dano + " /" + " | Defesa: " + defesa);
    }
      }
