package org.example;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class PredlogKorisnickogInterfejsa extends JDialog {
    private JPanel contentPane;
    private JButton buttonClose;
    private JButton buttonSave;
    private JButton buttonOpen;
    private JButton copyTopButton;
    private JTextArea textAreaTop;
    private JTextArea textAreaBottom;
    private JTextArea textAreaNew;
    private JButton copyBotButton;
    private JButton openTopButton;
    private JButton openBotButton;

    public PredlogKorisnickogInterfejsa() {
        setContentPane(contentPane);
        setModal(true);
        //    getRootPane().setDefaultButton(buttonOpen);

        buttonClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onButtonClose();
            }
        });


        // call onCancel() when cross is clicked
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onButtonClose();
            }
        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        copyTopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textAreaNew.append(textAreaTop.getSelectedText());
            }
        });
        copyBotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textAreaNew.append(textAreaBottom.getSelectedText());
            }
        });
        openTopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(null);
                File f= fileChooser.getSelectedFile();
                String filename=f.getAbsolutePath();

                try
                {
                    FileReader reader = new FileReader(filename);
                    BufferedReader br = new BufferedReader(reader);
                    textAreaTop.read(br,null);
                    br.close();
                    textAreaTop.requestFocus();

                }
                catch (Exception b){
                    JOptionPane.showMessageDialog(null, b);
                }
            }

        });
        openBotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(null);
                File f= fileChooser.getSelectedFile();
                String filename=f.getAbsolutePath();

                try
                {
                    FileReader reader = new FileReader(filename);
                    BufferedReader br = new BufferedReader(reader);
                    textAreaBottom.read(br,null);
                    br.close();
                    textAreaBottom.requestFocus();

                }
                catch (Exception b){
                    JOptionPane.showMessageDialog(null, b);
                }
            }
        });
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser SaveAs = new JFileChooser();
                SaveAs.setApproveButtonText("Save");
                int actionDialog = SaveAs.showOpenDialog(null);

                File fileName = new File(SaveAs.getSelectedFile() + ".txt");
                try {
                    if (fileName == null) {
                        return;
                    }
                    BufferedWriter outFile = new BufferedWriter(new FileWriter(fileName));
                    outFile.write(textAreaNew.getText()); //put in textfile

                    outFile.close();
                } catch (IOException ex) {
                }
            }
        });
    }

    private void onButtonClose() {
        // add your code here if necessary
        dispose();
    }
    public static void main(String[] args) {
        PredlogKorisnickogInterfejsa dialog = new PredlogKorisnickogInterfejsa();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
