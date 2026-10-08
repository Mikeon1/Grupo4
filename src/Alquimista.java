public class Alquimista extends Npc {
    //atributo exclusivo
    private double taxaDeSucesso;
    //construtor
    public Alquimista (String nome ,int nivelminimo , String fala, String missão ,String recompensa , double taxaDeSucesso){
        super("Fulano de tal",100, 7,4, ,  , , 28,"traga-me os ingredientes e por algumas moedas te faço uma poção");
        this.taxaDeSucesso = taxaDeSucesso;
    }
    //metodos
    public void criarPoção(String poção){
        System.out.println("okk,só um momento e sua poção estará pronta");
        if (Math.random() < taxaDeSucesso){
            System.out.println("Deu certo!, aqui sua poção");
            System.out.println("ahh, explodiu!,desculpa.");
        }
        }
}
