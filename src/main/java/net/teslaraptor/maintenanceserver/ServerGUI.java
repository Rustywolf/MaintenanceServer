package net.teslaraptor.maintenanceserver;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.regex.Pattern;
import javax.swing.text.DefaultCaret;
import net.teslaraptor.maintenanceserver.util.TextUtil;

public class ServerGUI extends javax.swing.JFrame {

    private Server server;
    private Thread serverThread;
    
    private Pattern numbersOnly = Pattern.compile("[^0-9]");
    
    public ServerGUI() {
        initComponents();
        
        Config.load();
        updateConfigText();
        
        DefaultCaret caret = (DefaultCaret)msgConsole.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }

    public void printException(Exception e) {
        StringWriter writer = new StringWriter();
        PrintWriter printer = new PrintWriter(writer);
        e.printStackTrace(printer);
        
        msgConsole.append(writer.toString());
    }
    
    public void print(String string) {
        msgConsole.append(string);
    }
    
    public void println(String string) {
        msgConsole.append(string + System.lineSeparator());
    }
    
    public void updateConfigText() {
        this.textDescription.setText(Config.description.replace('§', '&'));
        this.textDisconnectionMessage.setText(Config.disconnectMessage.replace('§', '&'));
        this.textPlayerCount.setText(String.valueOf(Config.playersMax));
        this.textProtocol.setText(String.valueOf(Config.versionProtocol));
        this.textVersion.setText(Config.versionName.replace('§', '&'));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        msgConsole = new javax.swing.JTextArea();
        buttonServerController = new javax.swing.JButton();
        textDisconnectionMessage = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textVersion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        textProtocol = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        textPlayerCount = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        textDescription = new javax.swing.JTextField();
        buttonReloadConfig = new javax.swing.JButton();
        buttonSaveConfig = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Maintainence Server"); // NOI18N

        msgConsole.setColumns(20);
        msgConsole.setRows(5);
        jScrollPane1.setViewportView(msgConsole);
        msgConsole.getAccessibleContext().setAccessibleName("boxLog");

        buttonServerController.setText("Start Server");
        buttonServerController.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonServerControllerActionPerformed(evt);
            }
        });

        jLabel1.setText("Disconnection Message: ");
        jLabel1.setToolTipText("");

        jLabel2.setText("Version:");
        jLabel2.setToolTipText("");

        jLabel3.setText("Version Protocol:");
        jLabel3.setToolTipText("");

        jLabel4.setText("Max Player Count:");
        jLabel4.setToolTipText("");

        jLabel5.setText("Description:");
        jLabel5.setToolTipText("");

        buttonReloadConfig.setText("Reload Config");
        buttonReloadConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonReloadConfigActionPerformed(evt);
            }
        });

        buttonSaveConfig.setText("Save Config");
        buttonSaveConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveConfigActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textDescription)
                            .addComponent(textPlayerCount)
                            .addComponent(textDisconnectionMessage)
                            .addComponent(textVersion)
                            .addComponent(textProtocol))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(buttonSaveConfig)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonReloadConfig)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonServerController)
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textDisconnectionMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textVersion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textProtocol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textPlayerCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonServerController)
                    .addComponent(buttonReloadConfig)
                    .addComponent(buttonSaveConfig))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        buttonServerController.getAccessibleContext().setAccessibleName("buttonStart");
        textDisconnectionMessage.getAccessibleContext().setAccessibleName("textDisconnectMessage");
        jLabel1.getAccessibleContext().setAccessibleName("labelDisconnectionMessage");
        buttonReloadConfig.getAccessibleContext().setAccessibleName("buttonReload");
        buttonReloadConfig.getAccessibleContext().setAccessibleDescription("");

        getAccessibleContext().setAccessibleName("frameMain");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonServerControllerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonServerControllerActionPerformed
        if (!Server.isRunning) {
            try {
                server = new Server();
                serverThread = new Thread(server);
                serverThread.start();
            } catch (Exception e) {
                printException(e);
            }
        } else {
            if (serverThread != null) {
                try {
                    server.shutdown();
                } catch (Exception e) {
                    printException(e);
                }
                
                serverThread = null;
                server = null;
            }
        }
        
        Server.isRunning = !Server.isRunning;
        
        buttonServerController.setText((Server.isRunning ? "Stop" : "Start") + " Server");
    }//GEN-LAST:event_buttonServerControllerActionPerformed

    private void buttonReloadConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReloadConfigActionPerformed
        Config.load();
        updateConfigText();
        
        println("Config reloaded succesfully!");
    }//GEN-LAST:event_buttonReloadConfigActionPerformed

    private void buttonSaveConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveConfigActionPerformed
        try {
            Config.description = TextUtil.toChatChar(this.textDescription.getText());
            Config.disconnectMessage = TextUtil.toChatChar(this.textDisconnectionMessage.getText());
            Config.playersMax = Integer.valueOf(this.textPlayerCount.getText());
            Config.versionProtocol = Integer.valueOf(this.textProtocol.getText());
            Config.versionName = TextUtil.toChatChar(this.textVersion.getText());
            
            Config.save();
            println("Config updated succesfully!");
        } catch (NumberFormatException e) {
            println("There was an error saving the config");
            println("Make sure the Version Protocl and Max Player Count are valid numbers!");
        }
    }//GEN-LAST:event_buttonSaveConfigActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonReloadConfig;
    private javax.swing.JButton buttonSaveConfig;
    private javax.swing.JButton buttonServerController;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea msgConsole;
    private javax.swing.JTextField textDescription;
    private javax.swing.JTextField textDisconnectionMessage;
    private javax.swing.JTextField textPlayerCount;
    private javax.swing.JTextField textProtocol;
    private javax.swing.JTextField textVersion;
    // End of variables declaration//GEN-END:variables

}
