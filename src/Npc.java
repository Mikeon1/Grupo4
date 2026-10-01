public class Npc {
//atributos
    private String nome;
    private int vida;
    private int dano;
    private int defesa;
    private double esquiva;
    private String missão;
    private String recompensa;
    private int nivelminimo;
    private final int vidamaxima;
    private String fala;

//construtor
     public Npc ( String nome, int vida, int dano, int defesa, double esquiva, String missão , String recompensa , int nivelminimo , String fala){
         this.nome=nome;
         this.vida=vida;
         this.dano=dano;
         this.defesa=defesa;
         this.esquiva=esquiva;
         this.missão=missão;
         this.recompensa=recompensa;
         this.nivelminimo=nivelminimo;
         this.vidamaxima=vida;
         this.fala=fala;
     }

     //métodos
     public String Getnome(){
         return nome;
     }
    public String Getmissão() {return missão}
    public int Getvida(){
        return vida;
    }
    public int Getdano(){
        return dano;
    }
    public int Getdefesa(){
        return defesa;
    }
    public double Getesquiva(){
        return esquiva;
    }
    public String Getrecompensa(){
        return recompensa;
    }
    public int Getnivelminimo(){
        return nivelminimo;
    }
    public void setmissão(String missãonova){
        missão=missãonova;
    }
    public void setdano(int danonovo){
        dano=danonovo;
    }

   public void perdervida(int vidaperdida){
         vida =vida - vidaperdida;
         if(vida < 0) {
             vida = 0;
         }
   }
   public void ganharvida(int vidaganhada){
         vida = vida + vidaganhada;
         if(vida > vidamaxima){
             vida= vidamaxima;
         }
   }
   public void falar(){
         System.out.println ( nome + ":" + fala);
   }

   public void mostrarinformaçoes(){
         System.out.println ("nome:" + nome);
   }

   public void darRecompensa(Protagonista){
         if(recompensa = null){
        System.out.println (" Não há nenhuma recompensa.");}
       else(Protagonista.receberItem(recompensa)){
           System.out.println (nome + ":" + "entregou" + recompensa.Getnome());
           recompensa = null;
       }
   }

   public void mudarFala,(String novafala){
         fala = novafala;
   }

   public void causarDano(int danoCausado){
         danoCausado = dano;// (+ dano arma + bonus)
   }

public boolean estarvivo(){
         return vida > 0;
}
}
