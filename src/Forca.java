
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author jennifer
 */
public class Forca {

    private ListaPalavrasDicas lpd = new ListaPalavrasDicas();
    private ArrayList<PalavraDica> listaPalavrasDicas = lpd.getLista();
    private int qtdPalavras = listaPalavrasDicas.size();
   
    private String palavra;
    private String dica;
    
    private void sorteiaPalavraDica() {
        Random random =  new Random();
        
        int pos = random.nextInt(qtdPalavras);

        this.palavra = listaPalavrasDicas.get(pos).getPalavra();
        this.dica = listaPalavrasDicas.get(pos).getDica();
    }
    
    

}
