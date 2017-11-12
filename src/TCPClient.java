/** @TCPClient: Cliente para conexao TCP com Threads *               Envia uma informacao ao servidor e recebe informacoes sobre a mensagens processadas * * Universidade Tecnológica Federal do Paraná - UTFPR-CM * @Autor: Gabriel Negrão Silva * @Data: 29/09/2017 */import java.net.*;import java.io.*;import java.util.logging.Level;import java.util.logging.Logger;public class TCPClient {    private static ForcaGUI forcaGUI;    private static DataOutputStream out;    private static DataInputStream in;    private static Thread clienteEnvia;    private static Thread clienteRecebe;    private static Socket s;           public TCPClient( ForcaGUI forcaGUI, String ipServidor, int serverPort){        this.forcaGUI = forcaGUI;        try {            s = new Socket(ipServidor, serverPort);  /* conecta com o servidor */            TCPClientEnvia();            TCPClientRecebe();        } catch (IOException ex) {            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);        }    }        void EnviaMsg(String msg){                       try {             out.writeUTF(msg);            System.out.println ("Informacao enviada.");        } catch (IOException ex) {            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);        }    }        public static void TCPClientEnvia(){        clienteEnvia = (new Thread() {            @Override            public void run() {                    try{                    /* cria objetos de leitura e escrita */                    out = new DataOutputStream( s.getOutputStream());	                } catch (UnknownHostException e){                    System.out.println("Socket:"+e.getMessage());                } catch (EOFException e){                    System.out.println("EOF:"+e.getMessage());                } catch (IOException e){                    System.out.println("leitura:"+e.getMessage());                } //catch            }        });//Thread        clienteEnvia.start();   }//metodo        public static void TCPClientRecebe(){        clienteRecebe = (new Thread() {            @Override            public void run() {                try{                    /* cria objetos de leitura e escrita */                    in = new DataInputStream( s.getInputStream());                                   String data = " ";                    while(true){                                                                  data = in.readUTF();                        System.out.println ("Informacao recebida.");                        forcaGUI.exibeMsg(data);                    }                } catch (UnknownHostException e){                    System.out.println("Socket:"+e.getMessage());                } catch (EOFException e){                    System.out.println("EOF:"+e.getMessage());                } catch (IOException e){                    System.out.println("leitura:"+e.getMessage());                } catch (InterruptedException ex) {                    Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);                } //catch            }        });//Thread        clienteRecebe.start();   }//metodo} //class