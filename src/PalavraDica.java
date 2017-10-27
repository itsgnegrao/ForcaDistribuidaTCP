/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author jennifer
 */
public class PalavraDica {
    private final String palavra;
    private final String dica;

    public PalavraDica(String palavra, String dica) {
        this.palavra = palavra;
        this.dica = dica;
    }

    public String getPalavra() {
        return palavra;
    }

    public String getDica() {
        return dica;
    }
    
    
}
