
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author jennifer
 */
public class Forca {

    private ListaPalavrasDicas lpd;
    private ArrayList<PalavraDica> listaPalavrasDicas;
    private int qtdPalavras;
   
    private String palavra;
    private String dica;

    public Forca() {
        this.lpd = new ListaPalavrasDicas();
        this.listaPalavrasDicas = lpd.getLista();
        this.qtdPalavras = listaPalavrasDicas.size();
        sorteiaPalavraDica();
    }
    
    private void sorteiaPalavraDica() {
        Random random =  new Random();
        
        int pos = random.nextInt(qtdPalavras);

        this.palavra = listaPalavrasDicas.get(pos).getPalavra();
        this.dica = listaPalavrasDicas.get(pos).getDica();
    }

    public String getPalavra() {
        return palavra;
        
    }

    public String getDica() {
        return dica;
    }
   
    
    

}
