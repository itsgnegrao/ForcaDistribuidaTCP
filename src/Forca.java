
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
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

    private ArrayList<String> letrasCorretas = new ArrayList<>();
    private ArrayList<String> letrasErradas = new ArrayList<>();

    private String palavra;
    private String dica;
    private String letra;
    private String palavraChute;
    private volatile boolean temosLetra = false;
    private volatile boolean temosPalavra = false;

    private int erros = 0;
    private int pontosRodA = 0;
    private int pontosRodB = 0;
    private int pontosGeralA = 0;
    private int pontosGeralB = 0;
    private boolean acertouLetra = false;
    private boolean jogador = true;

    public Forca(GerenciaPar gerenc) {
        this.gerenpar = gerenc;
        this.start();
    }

    public void run() {
        int i = 0;
        while (i < 2) {
            try {
                rodada();
                i += 1;
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(Forca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            verificaFinal();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Forca.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void rodada() throws IOException, InterruptedException {
        inicializaRodada();

        sorteiaPalavraDica();

        desenhaForca(fazRisquinhosPalavra(palavra), erros);
        //this.sleep(100);
        gerenpar.printMsg("", "DICADOJOGO" + this.getDica());

        String palavraRisquinhos = fazRisquinhosPalavra(palavra);

        int vez = 0;

        while (erros < 7) {

            if (temosPalavra) {
                temosPalavra = false;
                if (acertouPalavra(palavraChute)) {
                    palavraRisquinhos = encontraLetraSubstitui(letra, palavraChute);
                    desenhaForca(palavraRisquinhos, erros);
                    this.sleep(500);
                    gerenpar.printMsg("", "areaMsgPalavra encontrada!!!\nProxima Rodada...\n");
                    palavraChute = null;
                    break;
                }
                palavraChute = null;
            }

            if (temosLetra) {
                System.out.println("LETRA: " + letra);

                if (vez % 2 == 0) {
                    System.out.println("Jogador A");
                    jogador = true;
                    vez++;
                } else {
                    System.out.println("Jogador B");
                    jogador = false;
                    vez++;
                }
                System.out.println("Dica: " + dica);

                //verifica se a letra já foi
                if (letrasCorretas.contains(letra) || letrasErradas.contains(letra)) {
                    continue;
                }

                palavraRisquinhos = encontraLetraSubstitui(letra, palavraRisquinhos);
                incrementaPontos(jogador);

                try {
                    desenhaForca(palavraRisquinhos, erros);
                    if (acertouPalavra(palavraRisquinhos)) {
                        gerenpar.printMsg("", "areaMsgPalavra encontrada!!!\nProxima Rodada...\n");

                        temosLetra = false;
                        letra = null;

                        break;
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Forca.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Forca.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.out.format("Pacar-> A: %d, B: %d", pontosRodA, pontosRodB);

                temosLetra = false;
                letra = null;
            }
        }

    }

    private void inicializaRodada() {
        erros = 0;
        pontosRodA = 0;
        pontosRodB = 0;
        letrasCorretas = new ArrayList<>();
        letrasErradas = new ArrayList<>();
        temosLetra = false;
        temosPalavra = false;
        acertouLetra = false;
        jogador = true;
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

    public void setPalavrachute(String palavra) {
        this.palavraChute = palavra.toUpperCase();
        this.temosPalavra = true;
    }

    private String encontraLetraSubstitui(String letra, String palavraRisquinhos) {
        String novaPalavra = "";
        System.out.println("letra: " + letra + " ,palrisq:" + palavraRisquinhos);
        for (int i = 0; i < palavra.length(); i++) {
            if (letra.equals(this.palavra.charAt(i) + "")) {
                novaPalavra += letra;
                acertouLetra = true;
            } else if (palavraRisquinhos.equals("_")) {
                novaPalavra += "_";
            } else {
                novaPalavra += palavraRisquinhos.charAt(i);
            }
        }
        return novaPalavra;
    }

    private void incrementaPontos(boolean jogador) {
        if (acertouLetra) {// encontrou a letra
            letrasCorretas.add(letra);
            if (jogador) {
                pontosRodA++;//contabiliza os pontos de A
                pontosGeralA += pontosRodA;
            } else {
                pontosRodB++;//contabiliza os pontos de B
                pontosGeralB += pontosRodB;
            }
            acertouLetra = false;
        } else {//não encontrou a letra
            letrasErradas.add(letra);
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

    private boolean acertouPalavra(String novaPalavra) throws IOException, InterruptedException {
        if (palavra.toUpperCase().equals(novaPalavra.toUpperCase())) {
            System.out.println(palavra.toUpperCase() + " é IGUAL a " + novaPalavra.toUpperCase());
            if (jogador) {
                pontosRodB += 10;
            } else {
                pontosRodA += 10;
            }
            return true;
        } else {
            System.out.println(palavra.toUpperCase() + " é DIFERENTE de " + novaPalavra.toUpperCase());
            return false;
        }
    }

    private boolean verificaFinal() throws IOException, InterruptedException {
        String pontos = "Placar Final:\n" + gerenpar.Adversario1 + ": " + pontosGeralA + "  " + gerenpar.Adversario2 + ": " + pontosGeralB + "\n";
        if (pontosGeralA > pontosGeralB) {
            gerenpar.printMsg("Forca", "\n\n\n\n"
                    + pontos + "\n"
                    + this.gerenpar.Adversario1.toUpperCase() + " VENCEU!!");
        } else if (pontosGeralB > pontosGeralA) {
            gerenpar.printMsg("Forca", "\n\n\n\n"
                    + pontos + "\n"
                    + this.gerenpar.Adversario2.toUpperCase() + " VENCEU!!");
        } else {
            gerenpar.printMsg("\n\n\n\n"
                    + pontos + "\n"
                    + "Forca", "EMPATOU!!");
        }
        return true;
    }

    private String stringLetrasErradas() {
        String s = "";
        for (String l : letrasErradas) {
            s += l + " ";
        }
        return s;
    }

    private void desenhaForca(String palavra, int erros) throws IOException, InterruptedException {
        String pontos = "   Placar Geral\n"
                + gerenpar.Adversario1 + ": " + pontosGeralA + "  " + gerenpar.Adversario2 + ": " + pontosGeralB + "\n\n"
                + "   Placar Rodada\n"
                + gerenpar.Adversario1 + ": " + pontosRodA + "  " + gerenpar.Adversario2 + ": " + pontosRodB + "\n";
        if (erros == 0) {
            gerenpar.printMsg("Forca", pontos + "\n"
                    + "+-------+\n"
                    + "|\n"
                    + "|\n"
                    + "|\n"
                    + "|\n"
                    + "|\n"
                    + "|" + palavraRiscos(palavra).toUpperCase());
        }

        if (erros == 1) {
            gerenpar.printMsg("Forca", pontos + "\n"
                    + "+-------+\n"
                    + "|          O\n"
                    + "|\n"
                    + "|\n"
                    + "|\n"
                    + "|\n"
                    + "|" + palavraRiscos(palavra).toUpperCase()
                    + "\n\nLetras já erradas: " + stringLetrasErradas()
            );
        }
        if (erros == 2) {
            gerenpar.printMsg("Forca", pontos + "\n"
                    + "+-------+\n"
                    + "|          O\n"
                    + "|           |\n"
                    + "|           |\n"
                    + "|\n"
                    + "|\n"
                    + "|" + palavraRiscos(palavra).toUpperCase()
                    + "\n\nLetras já erradas: " + stringLetrasErradas());
        }
        if (erros == 3) {
            gerenpar.printMsg("Forca", pontos + "\n"
                    + "+-------+\n"
                    + "|          O\n"
                    + "|          /|\n"
                    + "|           |\n"
                    + "|\n"
                    + "|\n"
                    + "|" + palavraRiscos(palavra).toUpperCase()
                    + "\n\nLetras já erradas: " + stringLetrasErradas());
        }
        if (erros == 4) {
            gerenpar.printMsg("Forca", pontos + "\n"
                    + "+-------+\n"
                    + "|          O\n"
                    + "|          /|\\ \n"
                    + "|           |\n"
                    + "|\n"
                    + "|\n"
                    + "|" + palavraRiscos(palavra).toUpperCase()
                    + "\n\nLetras já erradas: " + stringLetrasErradas());
        }
        if (erros == 5) {
            gerenpar.printMsg("Forca", pontos + "\n"
                    + "+-------+\n"
                    + "|          O\n"
                    + "|          /|\\ \n"
                    + "|           |\n"
                    + "|          /\n"
                    + "|\n"
                    + "|" + palavraRiscos(palavra).toUpperCase()
                    + "\n\nLetras já erradas: " + stringLetrasErradas());
        }
        if (erros == 6) {
            gerenpar.printMsg("Forca", pontos + "\n"
                    + "+-------+\n"
                    + "|          O\n"
                    + "|          /|\\ \n"
                    + "|           |\n"
                    + "|          / \\ \n"
                    + "|\n"
                    + "|" + palavraRiscos(palavra).toUpperCase()
                    + "\n\nLetras já erradas: " + stringLetrasErradas());
        }
        if (erros == 7) {
            gerenpar.printMsg("Forca", pontos + "\n"
                    + "+-------+\n"
                    + "|            |\n"
                    + "|          O-   VOCES FORAM\n"
                    + "|           /|\\  ENFORCADOS\n"
                    + "|            |       :(\n"
                    + "|           / \\ \n"
                    + "|\n"
                    + "| A PALAVRA ERA \n"
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
