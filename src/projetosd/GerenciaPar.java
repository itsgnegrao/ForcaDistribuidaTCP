/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetosd;

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
    
    
    void printMsg(String Cliente, String msg) throws IOException, InterruptedException{
        
            
        synchronized(this){//synchronized block
            if(msg.contains("FOMAR PAR")){
                if(Cliente.equals("Cliente 1")){
                    Adversario1 = msg.replace("FOMAR PAR", "");
                    c1.setName(Adversario1);
                    
                }
                else if(Cliente.equals("Cliente 2")){
                    Adversario2 = msg.replace("FOMAR PAR", "");
                    c2.setName(Adversario2);
                    
                    c2.out.writeUTF("Seu adversário é: "+Adversario1+"\nPara conversar com seu adversário utilize o chat..\nBom Jogo!\n");
                    c1.out.writeUTF("Seu adversário é: "+Adversario2+"\nPara conversar com seu adversário utilize o chat..\nBom Jogo!\n");
                }                
            }
            else{
                try{
                    System.out.println("Gerencia["+this.getName()+"]-"+msg);

                    c1.out.writeUTF(msg);
                    c2.out.writeUTF(msg);
                    
                }catch(Exception e){System.out.println(e);}
                
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
