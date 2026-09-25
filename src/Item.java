public class Item {
    // Variáveis privadas

    private String nome; // Identificação do objeto
    private String tipo; // Tipo do item, ex: Cura, arma
    private int efeitosItem; // Efeitos do item ex: +50 de HP ou +20 de dano
    private int durabilidade // Resistência do item
    private String raridade // Classificação do item, ex: Comum, Raro, Épico

    // Construtor

    public Item(String nome, String tipo, int efeitosItem, int durabilidade, String raridade){

        this.nome = nome;
        this.tipo = tipo;
        this.efeitosItem = efeitosItem;
        this.durabilidade = durabilidade;
        this.raridade = raridade;
    }

    // Getter e Setter

    public String getNome(){
        return nome;
    }

    public String gettipo(){
        return tipo;
    }

    public int getefeitosItem () {
        return efeitosItem;
    }

    public int getdurabilidade(){
        return durabilidade;
    }

    public void setdurabilidade(int novadurabilidade){
        durabilidade = novadurabilidade;
    }

    public String getraridade(){
        return raridade;
    }

    // Ações

    // Desgaste do objeto

    public void perderdurabilidade(int pontos) {
        durabilidade = durabilidade - pontos;

        if (durabilidade < 0) {
            durabilidade = 0;
        }

        System.out.println(nome + " foi reparado em " + pontos + " ponto. durabilidade atual: " + durabilidade);
    }

}
