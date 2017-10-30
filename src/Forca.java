
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
    private boolean flagLetra = false;

    public Forca(ForcaGUI forcaGUI) {
        this.forcaGUI = forcaGUI;
    }

    public Forca() {
        jogo();
    }

    private void jogo() {
        for (int i = 1; i <= 5; i++) {
            if (i % 2 != 0) {
                System.out.println("Vez do jogador A");
                rodada(true);
            } else {
                System.out.println("Vez do jogador B");
                rodada(false);
            }
        }
    }

    private void rodada(boolean jogador) {
        sorteiaPalavraDica();
        String palavraRisquinhos = fazRisquinhosPalavra(palavra);

        String letra;
        while (erros < 7) {
            letra = pedeLetra();
            palavraRisquinhos = encontraLetraSubstitui(letra, palavraRisquinhos);
            incrementaPontos(jogador);
            desenhaForca(palavraRisquinhos, erros);
            System.out.format("Pacar-> A: %d, B: %d", pontosA, pontosB);
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
            palavraRisquinhos += " _";
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
        for (int i = 1; i < palavraRisquinhos.length(); i += 2) {
            novaPalavra += " ";
            System.out.println("i/2 = "+(i/2));
            if (letra.equals((int) this.palavra.charAt(i / 2))) {
                novaPalavra += " " + letra;
                flagLetra = true;
                System.out.println("letra igual");
            } else {
                novaPalavra += " _";
            }
        }
        return novaPalavra;
    }

    private void incrementaPontos(boolean jogador) {
        if (flagLetra = true) {// encontrou a letra
            if (jogador) {
                pontosA++;//contabiliza os pontos de A
            } else {
                pontosB++;//contabiliza os pontos de B
            }
            flagLetra = false;
        } else {//não encontrou a letra
            erros++;
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
                    + "|" + palavra);
        }

        if (erros == 1) {
            System.out.println("+-------+\n"
                    + "|       O\n"
                    + "|\n"
                    + "|\n"
                    + "|\n"
                    + "|\n"
                    + "|" + palavra);
        }
        if (erros == 2) {
            System.out.println("+-------+\n"
                    + "|       O\n"
                    + "|       |\n"
                    + "|       |\n"
                    + "|\n"
                    + "|\n"
                    + "|" + palavra);
        }
        if (erros == 3) {
            System.out.println("+-------+\n"
                    + "|       O\n"
                    + "|      /|\n"
                    + "|       |\n"
                    + "|\n"
                    + "|\n"
                    + "|" + palavra);
        }
        if (erros == 4) {
            System.out.println("+-------+\n"
                    + "|       O\n"
                    + "|      /|\\ \n"
                    + "|       |\n"
                    + "|\n"
                    + "|\n"
                    + "|" + palavra);
        }
        if (erros == 5) {
            System.out.println("+-------+\n"
                    + "|       O\n"
                    + "|      /|\\ \n"
                    + "|       |\n"
                    + "|      /\n"
                    + "|\n"
                    + "|" + palavra);
        }
        if (erros == 6) {
            System.out.println("+-------+\n"
                    + "|       O\n"
                    + "|      /|\\ \n"
                    + "|       |\n"
                    + "|      / \\ \n"
                    + "|\n"
                    + "|" + palavra);
        }
        if (erros == 7) {
            System.out.println("+-------+\n"
                    + "|       O   VOCE FOI\n"
                    + "|       /|\\  ENFORCADO(A)\n"
                    + "|        |      :(\n"
                    + "|       / \\ \n"
                    + "|\n"
                    + "| " + palavra);
        }
    }

    public String getPalavra() {
        return palavra;
    }

    public String getDica() {
        return dica;
    }

}
