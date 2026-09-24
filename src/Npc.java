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
//construtor
     public Npc ( String nome, int vida, int dano, int defesa, double esquiva, String missão , String recompensa , int nivelminimo){
         this.nome=nome;
         this.vida=vida;
         this.dano=dano;
         this.defesa=defesa;
         this.esquiva=esquiva;
         this.missão=missão;
         this.recompensa=recompensa;
         this.nivelminimo=nivelminimo;
         this.vidamaxima=vida;

     }
     //métodos
     public String Getnome(){
         return nome;
     }
    public String Getmissão(){
        return missão;
    }
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
//ações
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
   //causardano n ta finalizado
   public void causardano(){

   }
public boolean estarvivo(){
         return vida > 0;
}
}
