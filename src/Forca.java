
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author jennifer
 */
public class Forca {

    private ForcaGUI forcaGUI;
    private ListaPalavrasDicas lpd = new ListaPalavrasDicas();
    private ArrayList<PalavraDica> listaPalavrasDicas = lpd.getLista();
    private int qtdPalavras = listaPalavrasDicas.size();

    private String palavra;
    private String dica;
    private int erros = 0;
    private int pontosA = 0;
    private int pontosB = 0;
    private boolean acertouLetra = false;
    private boolean jogador = true;

    public Forca(ForcaGUI forcaGUI) {
        this.forcaGUI = forcaGUI;
    }

    public Forca() {
        jogo();
    }

    private void jogo() {
        for (int i = 1; i <= 5; i++) {
            rodada();
        }
    }

    private void rodada() {
        sorteiaPalavraDica();
        String palavraRisquinhos = fazRisquinhosPalavra(palavra);

        String letra;
        int vez = 0;
        while (erros < 7) {
            if(vez%2 !=0){
                System.out.println("Jogador A");
                jogador = true;
                vez++;
            }else{
                System.out.println("Jogador B");
                jogador = false;
                vez++;
            }
            System.out.println("Dica: "+dica);
            letra = pedeLetra();
            palavraRisquinhos = encontraLetraSubstitui(letra, palavraRisquinhos);
            incrementaPontos(jogador);
            desenhaForca(palavraRisquinhos, erros);
            System.out.format("Pacar-> A: %d, B: %d", pontosA, pontosB);
            if(verificaFinal(palavraRisquinhos)){
                break;
            }
        }
        System.out.println("A palavra era: " + palavra);

    }

    public void sorteiaPalavraDica() {
        Random random = new Random();

        int pos = random.nextInt(qtdPalavras);

        this.palavra = listaPalavrasDicas.get(pos).getPalavra();
        this.dica = listaPalavrasDicas.get(pos).getDica();
    }

    private String fazRisquinhosPalavra(String palavra) {
        String palavraRisquinhos = "";
        for (int i = 0; i < palavra.length(); i++) {
            palavraRisquinhos += "_";
        }
        return palavraRisquinhos;
    }

    private String pedeLetra() {
        String letra;
        do {
            System.out.print("\n\nDigite uma letra: ");
            Scanner s = new Scanner(System.in);
            letra = s.next();
            if (letra.length() > 1) {
                System.out.println("\n\nDigite apenas UMA letra: ");
            }
        } while (letra.length() != 1);
        return letra;
    }

    private String encontraLetraSubstitui(String letra, String palavraRisquinhos) {
        String novaPalavra = "";
        System.out.println("letra: "+letra+" ,palrisq:"+palavraRisquinhos);
        for (int i = 0; i < palavra.length(); i ++) {
            if (letra.equals(this.palavra.charAt(i)+"")) {
                novaPalavra += letra;
                System.out.println("letra "+letra+" eh igual a "+this.palavra.charAt(i));
                acertouLetra = true;
            } else if(palavraRisquinhos.equals("_")) {
                novaPalavra += "_";
            } else{
                novaPalavra += palavraRisquinhos.charAt(i);
                System.out.println("letra "+letra+" eh diferente de "+this.palavra.charAt(i));
            }
        }
        return novaPalavra;
    }

    private void incrementaPontos(boolean jogador) {
        if (acertouLetra) {// encontrou a letra
            if (jogador) {
                pontosA++;//contabiliza os pontos de A
            } else {
                pontosB++;//contabiliza os pontos de B
            }
            acertouLetra = false;
        } else {//não encontrou a letra
            erros++;
        }
    }

    private String palavraRiscos(String palavra){
        String novaPalavra = "";
        for (int i = 0; i < palavra.length(); i++) {
            novaPalavra += " "+palavra.charAt(i);
        }
        return novaPalavra;
    }
    
    private boolean verificaFinal(String palavraRisquinhos) {
        if(palavra.equals(palavraRisquinhos)){
            System.out.println("VOCE VENCEU!!!!");
            return true;
        }else{
            return false;
        }
    }

    private void desenhaForca(String palavra, int erros) {
        if (erros == 0) {
            System.out.println("+-------+\n"
                    + "|\n"
                    + "|\n"
                    + "|\n"
                    + "|\n"
                    + "|\n"
                    + "|" + palavraRiscos(palavra));
        }

        if (erros == 1) {
            System.out.println("+-------+\n"
                    + "|       O\n"
                    + "|\n"
                    + "|\n"
                    + "|\n"
                    + "|\n"
                    + "|" + palavraRiscos(palavra));
        }
        if (erros == 2) {
            System.out.println("+-------+\n"
                    + "|       O\n"
                    + "|       |\n"
                    + "|       |\n"
                    + "|\n"
                    + "|\n"
                    + "|" + palavraRiscos(palavra));
        }
        if (erros == 3) {
            System.out.println("+-------+\n"
                    + "|       O\n"
                    + "|      /|\n"
                    + "|       |\n"
                    + "|\n"
                    + "|\n"
                    + "|" + palavraRiscos(palavra));
        }
        if (erros == 4) {
            System.out.println("+-------+\n"
                    + "|       O\n"
                    + "|      /|\\ \n"
                    + "|       |\n"
                    + "|\n"
                    + "|\n"
                    + "|" + palavraRiscos(palavra));
        }
        if (erros == 5) {
            System.out.println("+-------+\n"
                    + "|       O\n"
                    + "|      /|\\ \n"
                    + "|       |\n"
                    + "|      /\n"
                    + "|\n"
                    + "|" + palavraRiscos(palavra));
        }
        if (erros == 6) {
            System.out.println("+-------+\n"
                    + "|       O\n"
                    + "|      /|\\ \n"
                    + "|       |\n"
                    + "|      / \\ \n"
                    + "|\n"
                    + "|" + palavraRiscos(palavra));
        }
        if (erros == 7) {
            System.out.println("+-------+\n"
                    + "|       O   VOCE FOI\n"
                    + "|       /|\\  ENFORCADO(A)\n"
                    + "|        |      :(\n"
                    + "|       / \\ \n"
                    + "|\n"
                    + "| " + palavraRiscos(palavra));
        }
    }

    public String getPalavra() {
        return palavra;
    }

    public String getDica() {
        return dica;
    }


}
