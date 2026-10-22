public class Ferreiro extends Npc{
    //atributo exclusivo da classe
    private double taxaDeSucesso;
//costrutor
    public Ferreiro (String nome,int nivelminimo, String fala, String missão, double taxaDeSucesso,String recompensa){
        super("Ferreiro Arg", 100 ,4 , 7,9,"nenhuma","nenhuma", 15, "o que queres por agora?");
        this.taxaDeSucesso = taxaDeSucesso;
    }
//metodos
    public void melhorarArma(String arma){
        System.out.println("O " + Getnome() + " terminará de melhorar sua arma em alguns instantes...");
        if (Math.random() < 0.5) {
            System.out.println("o melhoramento foi um sucesso!, aqui está sua arma.");
        }
        else {System.out.println("desculpa,não deu certo dessa vez");
        }
    } public void forjarArma(Protagonista protagonista, String nomeArmaNova, int danoArma, String materialNecessario) {
        System.out.println( Getnome() + "está forjando sua nova arma ");
        if (Math.random() < 0.3) {
            System.out.println("o ferreiro terminou a forja!, aqui está sua aram");
        }
        else {
            System.out.println("o ferreiro não consegio forjar sua aram");
        }
    }



}
