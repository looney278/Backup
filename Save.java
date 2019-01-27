package textualadventuregamegenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;

public class Save extends javax.swing.JFrame {

    public Save(String storyText, String inventory, String id,
            String after, String before) throws IOException {

        BufferedReader br = null;
        FileReader fr = null;
        String currentLine = "";
        boolean newDoc = true;

        final JFileChooser fc = new JFileChooser();
        int response = fc.showSaveDialog(Save.this);
        if (response == JFileChooser.APPROVE_OPTION) {
            try {
                if (fc.getSelectedFile().exists() == false) {
                    fr = new FileReader(fc.getSelectedFile() + ".xml");
                    br = new BufferedReader(fr);
                    while ((currentLine = br.readLine()) != null) {
                        if (currentLine.contains("version")) {
                            newDoc = false;

                        }
                    }
                }
            } catch (IOException e) {
            }
            try (FileWriter fw = new FileWriter(fc.getSelectedFile()
                    + ".xml", true)) {

                //change to work for existing file 
                //so that xml version doesnt appear and storySection
                //is moved to the new end of the file
                if (newDoc == false) {
                    fw.write("\n<story id='" + id
                            + "'>\n<text>" + storyText + "</text>"
                            + "\n<inventory>" + inventory + "</inventory>"
                            + "\n<afterBranch>" + after + "</afterBranch>"
                            + "\n<beforeBranch>" + before + "</beforeBranch>"
                            + "\n</story>\n");
                } else {
                    fw.write("<?xml version=\"1.0\"?>\n" + "<storySection>"
                            + "\n<story id='" + id
                            + "'>\n<text>" + storyText + "</text>"
                            + "\n<inventory>" + inventory + "</inventory>"
                            + "\n<afterBranch>" + after + "</afterBranch>"
                            + "\n<beforeBranch>" + before + "</beforeBranch>"
                            + "\n</story>" + "\n</storySection>\n");
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
