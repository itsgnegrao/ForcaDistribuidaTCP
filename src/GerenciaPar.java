/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author itsgnegrao
 */
public class GerenciaPar extends Thread{
    
    TaskThread c1;
    String Adversario1 = "";
    TaskThread c2;
    String Adversario2 = "";
    
    Forca forca; //instancia do jogo
    
    
    void printMsg(String Cliente, String msg) throws IOException, InterruptedException{
        synchronized(this){//synchronized block
            System.out.println("MSGGG: "+msg);
            if(msg.contains("FOMAR PAR")){
                if(Cliente.equals("Cliente 1")){
                    Adversario1 = msg.replace("FOMAR PAR", "");
                    c1.setName(Adversario1);
                    
                }
                else if(Cliente.equals("Cliente 2")){
                    Adversario2 = msg.replace("FOMAR PAR", "");
                    c2.setName(Adversario2);
                    
                    //envia o nome do adversário, apenas debug
                    c2.out.writeUTF("areaMsg"+"Seu adversário é: "+Adversario1+"\nPara conversar com seu adversário utilize o chat..\nBom Jogo!\n\n");
                    c1.out.writeUTF("areaMsg"+"Seu adversário é: "+Adversario2+"\nPara conversar com seu adversário utilize o chat..\nBom Jogo!\n\n");
  
                    forca = new Forca(this);
                     /*
                    String dica = "DICADOJOGO"+forca.getDica();

                    c1.out.writeUTF(dica);
                    c2.out.writeUTF(dica);*/
                    
                    //debug
                    System.out.println("Jogo Criando para: "+Adversario1+" x "+Adversario2);
                    
                    //chamar a vez do usuario 1
                    c1.out.writeUTF("suaVEZ");
                }                
            }
            else if(msg.contains("areaMsg")){
                    String msgtst = msg.replace("areaMsg", "");
                    System.out.println("Gerencia["+this.getName()+"]-"+msgtst);

                    c1.out.writeUTF(msg);
                    c2.out.writeUTF(msg);
            }
            else if(msg.contains("areaForcaletra")){
                msg = msg.replace("areaForcaletra", "");
                char letra = msg.charAt(0);
                System.out.println("Gerencia["+this.getName()+"]-"+msg);
                
                forca.setLetra(letra+"");
                
                msg = msg.substring(1);
                
                if(msg.contains("suaVEZ")){
                    
                    String cliente = msg.replace("suaVEZ", "");
                    
                    if(cliente.equals(Adversario1)){
                        c2.out.writeUTF("suaVEZ");
                    }
                    if(cliente.equals(Adversario2)){
                        c1.out.writeUTF("suaVEZ");
                    }
                }
            }
            else if(msg.contains("chutarPalavra")){
                msg = msg.replace("chutarPalavra", "");
                forca.setPalavrachute(msg);
            }
            else if(Cliente.equals("Forca")){
                c1.out.writeUTF("areaForca"+msg);
                c2.out.writeUTF("areaForca"+msg);
            }
            else{
                c1.out.writeUTF(msg);
                c2.out.writeUTF(msg);
                
            }
        }
    }

    public void setC1(TaskThread c1) {
        this.c1 = c1;
    }
    
    public void setC2(TaskThread c2) throws IOException {
        this.c2 = c2;
    }
  
}
