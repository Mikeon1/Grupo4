public class Texte {

    public static void main(String[] Args) {

        Ferreiro arg = new Ferreiro("arg", 20, "nenhuma", "nenhuma", 1.1, "nenhuma");

        String nome = arg.Getnome();
        String fala = arg.Getfala();

        System.out.println(nome);
        System.out.println(fala);
        arg.melhorarArma(nome);


        Alquimista fulano = new Alquimista("fulano de tal", 30, "nenhuma", "nenhuma", "nenhuma", 1.1);

        String Nome = fulano.Getnome();
        String Fala = fulano.Getfala();
        System.out.println(Nome);
        System.out.println(Fala);
        fulano.criarPoção(nome);
    }

    }