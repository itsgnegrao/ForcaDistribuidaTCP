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
            
            //incialização de um novo par
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
  
                    //cria a instância e thread do jogo
                    forca = new Forca(this);

                    //debug
                    System.out.println("Jogo Criando para: "+Adversario1+" x "+Adversario2);
                    
                    //chamar a vez do usuario 1
                    c1.out.writeUTF("suaVEZ");
                }                
            }
            //mensagens destinadas a Area de Mensagens, CHAT
            else if(msg.contains("areaMsg")){
                    String msgtst = msg.replace("areaMsg", "");
                    System.out.println("Gerencia["+this.getName()+"]-"+msgtst);

                    c1.out.writeUTF(msg);
                    c2.out.writeUTF(msg);
            }
            //Mensagens destinada ao Jogo contendo a letra
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
            //Chuta a palavra do jogo
            else if(msg.contains("chutarPalavra")){
                msg = msg.replace("chutarPalavra", "");
                forca.setPalavrachute(msg);
            }
            //Mensagens destinada a area da Forca
            else if(Cliente.equals("Forca")){
                c1.out.writeUTF("areaForca"+msg);
                c2.out.writeUTF("areaForca"+msg);
            }
            //Apenas Troca de Mensagens
            else{
                c1.out.writeUTF(msg);
                c2.out.writeUTF(msg);
                
            }
        }
    }

    public void setC1(TaskThread c1) throws IOException{
        this.c1 = c1;
    }
    
    public void setC2(TaskThread c2) throws IOException {
        this.c2 = c2;
    }
  
}
