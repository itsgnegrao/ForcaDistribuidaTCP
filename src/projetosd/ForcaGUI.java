/** @ForcaGUI: Interface Swing para o jogo da Forca
 *
 * Universidade Tecnológica Federal do Paraná - UTFPR-CM
 * @Autor: Gabriel Negrão Silva
 * @Data: 29/09/2017
 */

package projetosd;

import java.awt.Color;
import static java.awt.event.KeyEvent.*;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ForcaGUI extends javax.swing.JFrame {

    private static Socket socketCli; //socket do cliente
    private static String ip; //ip fornecido pela Interface
    private static int porta; // porta fornecida pela interface
    private static TCPClient client; //cliente para alocação da classe controladora
    private static ArrayList<String> reserved; //palavras de requisições reservadads
    private String Apelido;
    
    public ForcaGUI() throws InterruptedException {
        
        Apelido = JOptionPane.showInputDialog(rootPane, "Digite o seu Apelido:", "Adicionar Apelido", 3);
        
        client = new TCPClient(this, "127.0.0.1", 7896);  // armazena conexão do cliente
 
        
        initComponents();
        
        
        labelApelido.setText(Apelido);
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.exibeMsg("Bem vindo: "+Apelido+"\nAguardando Adversário...\n");
        
        textMsg.setEnabled(false);
        btnEnviar.setEnabled(false);
        textLetra.setEnabled(false);
        btnEnviar1.setEnabled(false);
        areaForca.setEnabled(false);
       
        //inicializa a lista de palavras reservadas
        reserved = new ArrayList<>();
        
        //adiciona palavras reservadas a lista
        reserved.add("EXIT");
        reserved.add("TIME");
        reserved.add("DATA");
        reserved.add("DOWN");
        reserved.add("FILES");
    }

    //Funcao para exibir uma mensagens na tela
    public synchronized void exibeMsg(String msg){
        if(msg.equals("Par encontrado!\n")){
            client.EnviaMsg("FOMAR PAR"+Apelido);
            textMsg.setEnabled(true);
            btnEnviar.setEnabled(true);
            textLetra.setEnabled(true);
            btnEnviar1.setEnabled(true);
            areaForca.setEnabled(true);
        }
        
        areaMsg.append(msg);             
        textMsg.setText("");
        textMsg.requestFocus();
    }
    
     //funcao de formatacao da string de mensagem para adicionar apelido
     //variavel 'flag' corresponde ao formato
    public synchronized String formatString(String apelido, String msg, boolean flag){
        String msg_format;
        //se flag == true formate com \n
        if(flag)  msg_format= "[ "+apelido+" ]: "+msg + "\n";
        
        //se flag == false formate sem \n
        else msg_format = "[ "+apelido+" ]: "+msg;
       
        
        textMsg.setText("");
        textMsg.requestFocus();
        
        return msg_format;
    }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        areaMsg = new javax.swing.JTextArea();
        textMsg = new javax.swing.JTextField();
        btnEnviar = new javax.swing.JButton();
        labelApelido = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaForca = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        textLetra = new javax.swing.JTextField();
        btnEnviar1 = new javax.swing.JButton();
        labelApelido1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jogo da Forca");

        areaMsg.setEditable(false);
        areaMsg.setColumns(20);
        areaMsg.setLineWrap(true);
        areaMsg.setRows(5);
        areaMsg.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CHAT", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        areaMsg.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(areaMsg);

        textMsg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textMsgKeyPressed(evt);
            }
        });

        btnEnviar.setText("ENVIAR");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        labelApelido.setFont(new java.awt.Font("Noto Sans", 0, 20)); // NOI18N
        labelApelido.setText("Qualquer um");

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 24)); // NOI18N
        jLabel1.setText("Apelido:");

        areaForca.setEditable(false);
        areaForca.setColumns(20);
        areaForca.setRows(5);
        areaForca.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "FORCA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        areaForca.setRequestFocusEnabled(false);
        jScrollPane2.setViewportView(areaForca);

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        jLabel2.setText("Letra:");

        textLetra.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        textLetra.setMinimumSize(new java.awt.Dimension(20, 40));

        btnEnviar1.setText("ENVIAR");
        btnEnviar1.setPreferredSize(new java.awt.Dimension(55, 40));
        btnEnviar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviar1ActionPerformed(evt);
            }
        });

        labelApelido1.setFont(new java.awt.Font("Noto Sans", 0, 20)); // NOI18N
        labelApelido1.setText("Qualquer um");

        jLabel3.setFont(new java.awt.Font("Noto Sans", 1, 24)); // NOI18N
        jLabel3.setText("DICA:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textLetra, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEnviar1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(labelApelido)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelApelido1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(textMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelApelido)
                    .addComponent(jLabel3)
                    .addComponent(labelApelido1))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(textLetra, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEnviar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textMsgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textMsgKeyPressed
        if (evt.getKeyCode() == VK_ENTER){
            btnEnviar.doClick();
        }
    }//GEN-LAST:event_textMsgKeyPressed

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        if(reserved.contains(textMsg.getText())){
            client.EnviaMsg(formatString(Apelido,textMsg.getText(),false));
        }
        else client.EnviaMsg(formatString(Apelido,textMsg.getText(),true));
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void btnEnviar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEnviar1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ForcaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ForcaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ForcaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ForcaGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ForcaGUI().setVisible(true);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ForcaGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaForca;
    private javax.swing.JTextArea areaMsg;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnEnviar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelApelido;
    private javax.swing.JLabel labelApelido1;
    private javax.swing.JTextField textLetra;
    private static javax.swing.JTextField textMsg;
    // End of variables declaration//GEN-END:variables
}
