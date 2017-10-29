/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 *
 * @author jennifer
 */
public class ListaPalavrasDicas {

    ArrayList<PalavraDica> lista;

    public ListaPalavrasDicas() {
        lista.add(new PalavraDica("hinoceronte", "animal"));
        lista.add(new PalavraDica("hinoceronte", "animal"));
        lista.add(new PalavraDica("cavalo", "animal"));
        lista.add(new PalavraDica("tartaruga", "animal"));
        lista.add(new PalavraDica("raposa", "animal"));
        lista.add(new PalavraDica("boizinho", "pequeno animal"));
        lista.add(new PalavraDica("cachorrao", "grande animal"));
        lista.add(new PalavraDica("jacare", "reptil"));
        lista.add(new PalavraDica("morango", "fruta"));
        lista.add(new PalavraDica("melancia", "fruta"));
        lista.add(new PalavraDica("maracuja", "fruta"));
        lista.add(new PalavraDica("carambola", "fruta"));
        lista.add(new PalavraDica("abacaxi", "fruta"));
        lista.add(new PalavraDica("felicidade", "sentimento"));
        lista.add(new PalavraDica("tristeza", "sentimento"));
        lista.add(new PalavraDica("alegria", "sentimento"));
        lista.add(new PalavraDica("raiva", "sentimento"));
        lista.add(new PalavraDica("piloto", "profissao"));
        lista.add(new PalavraDica("professor", "profissao"));
        lista.add(new PalavraDica("jornalista", "profissao"));
        lista.add(new PalavraDica("jogador", "profissao"));
        lista.add(new PalavraDica("dinossauro", "reptil pre-historico"));
        lista.add(new PalavraDica("albatroz", "ave procelariidia"));
        lista.add(new PalavraDica("yugoslavia", "pais"));
        lista.add(new PalavraDica("uzbequistao", "ex-republica sovietica da asia central"));
        lista.add(new PalavraDica("artico", "norte"));
        lista.add(new PalavraDica("antartica", "sul"));
        lista.add(new PalavraDica("vida social", "nao tem quando se estuda na UTF"));
        lista.add(new PalavraDica("algema", "prendedor"));
        lista.add(new PalavraDica("aerodromo", "aviao"));
    }

    public ArrayList<PalavraDica> getLista() {
        return lista;
    }
    
}
