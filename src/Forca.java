
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jennifer
 */
public class Forca extends Thread {

    private GerenciaPar gerenpar;
    private ListaPalavrasDicas lpd = new ListaPalavrasDicas();
    private ArrayList<PalavraDica> listaPalavrasDicas = lpd.getLista();
    private int qtdPalavras = listaPalavrasDicas.size();

    private String palavra;
    private String dica;
    private String letra;
    private volatile Boolean temosLetra = false;

    private int erros = 0;
    private int pontosA = 0;
    private int pontosB = 0;
    private boolean acertouLetra = false;
    private boolean jogador = true;

    public Forca(GerenciaPar gerenc) {
        this.gerenpar = gerenc;
        this.start();
    }

    public void run() {
        rodada();
        String palavraRisquinhos = fazRisquinhosPalavra(palavra);

        int vez = 0;

        while (erros < 7) {

            if (temosLetra) {
                System.out.println("LETRA: " + letra);

                if (vez % 2 != 0) {
                    System.out.println("Jogador A");
                    jogador = true;
                    vez++;
                } else {
                    System.out.println("Jogador B");
                    jogador = false;
                    vez++;
                }
                System.out.println("Dica: " + dica);

                palavraRisquinhos = encontraLetraSubstitui(letra, palavraRisquinhos);
                incrementaPontos(jogador);

                try {
                    desenhaForca(palavraRisquinhos, erros);
                } catch (IOException ex) {
                    Logger.getLogger(Forca.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Forca.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.out.format("Pacar-> A: %d, B: %d", pontosA, pontosB);
                if (verificaFinal(palavraRisquinhos)) {
                    break;
                }

                temosLetra = false;
                letra = null;
            }
        }

        System.out.println("A palavra era: " + palavra);
    }

    public void nova_rodada() {
        rodada();
    }

    private void rodada() {
        sorteiaPalavraDica();
    }

    public void sorteiaPalavraDica() {
        Random random = new Random();

        int pos = random.nextInt(qtdPalavras);

        this.palavra = listaPalavrasDicas.get(pos).getPalavra().toUpperCase();
        this.dica = listaPalavrasDicas.get(pos).getDica().toUpperCase();
    }

    private String fazRisquinhosPalavra(String palavra) {
        String palavraRisquinhos = "";
        for (int i = 0; i < palavra.length(); i++) {
            palavraRisquinhos += "_";
        }
        return palavraRisquinhos;
    }

    public void setLetra(String letra) {
        this.letra = letra.toUpperCase();
        this.temosLetra = true;
    }

    private String encontraLetraSubstitui(String letra, String palavraRisquinhos) {
        String novaPalavra = "";
        System.out.println("letra: " + letra + " ,palrisq:" + palavraRisquinhos);
        for (int i = 0; i < palavra.length(); i++) {
            if (letra.equals(this.palavra.charAt(i) + "")) {
                novaPalavra += letra;
                //System.out.println("letra "+letra+" eh igual a "+this.palavra.charAt(i));
                acertouLetra = true;
            } else if (palavraRisquinhos.equals("_")) {
                novaPalavra += "_";
            } else {
                novaPalavra += palavraRisquinhos.charAt(i);
                //System.out.println("letra "+letra+" eh diferente de "+this.palavra.charAt(i));
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

    private String palavraRiscos(String palavra) {
        String novaPalavra = "";
        for (int i = 0; i < palavra.length(); i++) {
            novaPalavra += " " + palavra.charAt(i);
        }
        return novaPalavra;
    }

    private boolean verificaFinal(String palavraRisquinhos) {
        if (palavra.equals(palavraRisquinhos)) {
            if (pontosA > pontosB) {
                System.out.println(this.gerenpar.Adversario1.toUpperCase() + " VENCEU!!");
            } else if (pontosB > pontosA) {
                System.out.println(this.gerenpar.Adversario2.toUpperCase() + " VENCEU!!");
            } else {
                System.out.println("EMPATOU!!");
            }
            return true;
        } else {
            return false;
        }
    }

    private void desenhaForca(String palavra, int erros) throws IOException, InterruptedException {
        String pontos = gerenpar.Adversario1 + ": " + pontosA + "  " + gerenpar.Adversario2 + ": " + pontosB+"\n";
        if (erros == 0) {
            gerenpar.printMsg("Forca",pontos
                    + "+-------+\n"
                    + "|\n"
                    + "|\n"
                    + "|\n"
                    + "|\n"
                    + "|\n"
                    + "|" + palavraRiscos(palavra).toUpperCase());
        }

        if (erros == 1) {
            gerenpar.printMsg("Forca",pontos
                    + "+-------+\n"
                    + "|          O\n"
                    + "|\n"
                    + "|\n"
                    + "|\n"
                    + "|\n"
                    + "|" + palavraRiscos(palavra).toUpperCase()
            );
        }
        if (erros == 2) {
            gerenpar.printMsg("Forca",pontos
                    + "+-------+\n"
                    + "|          O\n"
                    + "|           |\n"
                    + "|           |\n"
                    + "|\n"
                    + "|\n"
                    + "|" + palavraRiscos(palavra).toUpperCase());
        }
        if (erros == 3) {
            gerenpar.printMsg("Forca",pontos
                    +"+-------+\n"
                    + "|          O\n"
                    + "|          /|\n"
                    + "|           |\n"
                    + "|\n"
                    + "|\n"
                    + "|" + palavraRiscos(palavra).toUpperCase());
        }
        if (erros == 4) {
            gerenpar.printMsg("Forca",pontos
                    +"+-------+\n"
                    + "|          O\n"
                    + "|          /|\\ \n"
                    + "|           |\n"
                    + "|\n"
                    + "|\n"
                    + "|" + palavraRiscos(palavra).toUpperCase());
        }
        if (erros == 5) {
            gerenpar.printMsg("Forca",pontos
                    +"+-------+\n"
                    + "|          O\n"
                    + "|          /|\\ \n"
                    + "|           |\n"
                    + "|          /\n"
                    + "|\n"
                    + "|" + palavraRiscos(palavra).toUpperCase());
        }
        if (erros == 6) {
            gerenpar.printMsg("Forca",pontos
                    +"+-------+\n"
                    + "|          O\n"
                    + "|          /|\\ \n"
                    + "|           |\n"
                    + "|          / \\ \n"
                    + "|\n"
                    + "|" + palavraRiscos(palavra).toUpperCase());
        }
        if (erros == 7) {
            gerenpar.printMsg("Forca",pontos
                    + "+-------+\n"
                    + "|            |\n"
                    + "|          O-   VOCES FORAM\n"
                    + "|           /|\\  ENFORCADOS\n"
                    + "|            |       :(\n"
                    + "|           / \\ \n"
                    + "|  A PALAVRA ERA \n"
                    + "| " + this.palavra.toUpperCase());
        }
    }

    public String getPalavra() {
        return palavra;
    }

    public String getDica() {
        return dica;
    }

}
