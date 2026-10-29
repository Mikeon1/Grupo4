import java.util.List;
import java.util.ArrayList;

public class Npc {
//atributos
    private String nome;
    private int vida;
    private int dano;
    private int defesa;
    private double esquiva;
    private String missao;
    private String recompensa;
    private int nivelminimo;
    private final int vidamaxima;
    private String fala;
    private List<Item> itensAVenda;
    private List<Integer> precosItens;

//construtor
     public Npc ( String nome, int vida, int dano, int defesa, double esquiva, String missão , String recompensa , int nivelminimo , String fala){
         this.nome=nome;
         this.vida=vida;
         this.dano=dano;
         this.defesa=defesa;
         this.esquiva=esquiva;
         this.missao=missão;
         this.recompensa=recompensa;
         this.nivelminimo=nivelminimo;
         this.vidamaxima=vida;
         this.fala=fala;
         this.itensAVenda = new ArrayList<>();
         this.precosItens = new ArrayList<>();
     }

     //métodos
     public String Getnome(){
         return nome;
     }
    public String Getmissão() {return missao;}
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
        missao=missãonova;
    }
    public void setdano(int danonovo){
        dano=danonovo;
    }
    public String Getfala(){
        return fala;
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

   // METODOS QUE DEPENDEM DE OUTRAS CLASSES!!

 /* public void darRecompensa(Protagonista){
         if(recompensa = null){
        System.out.println (" Não há nenhuma recompensa.");}
       else(Protagonista.receberItem(recompensa)){
           System.out.println (nome + ":" + "entregou" + recompensa.Getnome());
           recompensa = null;
       }
   }

  public void adicionarItemLoja(Item item, int preco) {
      this.itensAVenda.add(item);
      this.precosItens.add(preco);
  }
  public void informacoesLoja(){
      System.out.println("loja de itens do" + nome );
      if (itensAVenda.isEmpty()) {
          System.out.println("Não tenho nada para vender no momento.");
          return;
      }
      for (int a = 0; a < itensAVenda.size(); a++) {
          Item item = itensAVenda.get(a);
          int preco = precosItens.get(a);
          System.out.println("[" + (a + 1) + "] " + Item.GetNome() + " | Preço: " + preco + " moedas");
      }
      }
    }*/


    public void mudarFala(String novafala){
         fala = novafala;
   }

   public void causarDano(int danoCausado ,int valorDano){
         danoCausado = dano + valorDano;
   }

public boolean estarvivo(){
         return vida > 0;
}
}
