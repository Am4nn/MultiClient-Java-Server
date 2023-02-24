package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MyFrame extends JFrame {

    String title;

    public MyFrame(String title) {
        this.title = title;
        setVisible(true);
        initComponents();
    }

    private void initComponents() {

        jScrollPane1 = new JScrollPane();
        convoArea = new JTextArea();
        jScrollPane2 = new JScrollPane();
        sendArea = new JTextArea();
        sendButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(title);
        setResizable(false);

        jScrollPane1.setAutoscrolls(true);

        convoArea.setEditable(false);
        convoArea.setColumns(20);
        convoArea.setFont(new java.awt.Font("Calibri", Font.PLAIN, 18)); // NOI18N
        convoArea.setLineWrap(true);
        convoArea.setRows(5);
        convoArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(convoArea);

        sendArea.setColumns(20);
        sendArea.setFont(new java.awt.Font("Calibri Light", Font.PLAIN, 18)); // NOI18N
        sendArea.setLineWrap(true);
        sendArea.setRows(3);
        sendArea.setText("Type your message here..");
        sendArea.setWrapStyleWord(true);
        sendArea.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane2.setViewportView(sendArea);
        sendArea.setText("");

        sendButton.setText("Send");
        sendButton.addActionListener(this::sendButtonActionPerformed);

        GroupLayout layout = new GroupLayout(getContentPane());
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                                                .addContainerGap())
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                                                .addGap(28, 28, 28)
                                                .addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                                .addGap(26, 26, 26))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
                                                .addGap(36, 36, 36))))
        );

        getContentPane().setLayout(layout);
        pack();
    }

    private void sendButtonActionPerformed(ActionEvent evt) {

        String str = sendArea.getText();

//        if(!str.trim().equals(""))
//        {
//            try {
//                Client.OUT.writeUTF(str.trim());
//            } catch (IOException ex) {
//                Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE,
//                        null, ex);
//            }
//        }

        sendArea.setText("");

    }

    public JTextArea convoArea;
    JScrollPane jScrollPane1;
    JScrollPane jScrollPane2;
    JTextArea sendArea;
    JButton sendButton;

    public static void main(String[] args) {
        MyFrame gui = new MyFrame("Server");
    }
}
