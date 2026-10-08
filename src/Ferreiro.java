public class Ferreiro extends Npc{
    //atributo exclusivo da classe
    private double taxaDeSucesso;
//costrutor
    public Ferreiro (String nome,int nivelminimo, String fala, String missão, double taxaDeSucesso,String recompensa){
        super("Ferreiro Arg", 100 ,4 , 7, , , , 15, "o que queres por agora?");
        this.taxaDeSucesso = taxaDeSucesso;
    }
//metodos
    public void melhorarArma(String arma){
        System.out.println("O" + Getnome() + "terminará de melhorar sua arma em alguns instantes");
        if (Math.random() < taxaDeSucesso) {
            System.out.println("o melhoramento foi um sucesso!, aqui está sua arma.");
            System.out.println("desculpe, não funcionou dessa vez, aqui está sua arma.");

        }
    }
    //terminar
    public void forjarArma(String armaNova){

    }

}
