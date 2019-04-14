package textualadventuregamegenerator;

import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;

public class Save extends javax.swing.JFrame {

    String sectionString;

    /**
     * Used to save the create games data into a file in the correct format, it
     * also adds the final xml tag to the file when the game is finished to keep
     * the files in the correct format
     *
     * @param storyText
     * @param conditions
     * @param id
     * @param before
     * @param finished
     * @param edit
     * @param image
     * @throws IOException
     */
    public Save(String storyText, String conditions, String id, String before, boolean finished, boolean edit, String image) throws IOException {
        //if finished is false write all to variable, if true write all to file
        //stored in mainwindow to stop rewriting on save initialisation
        if (finished == false) {
            sectionString = "\n<story id='" + id
                    + "'>\n<text>" + storyText + "</text>"
                    + "\n<conditions>" + conditions + "</conditions>"
                    + "\n<beforeBranch>" + before + "</beforeBranch>"
                    + "\n<imageLocation>" + image + "</imageLocation>"
                    + "\n</story>";
        } else {
            final JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new java.io.File("."));
            int response = fc.showSaveDialog(Save.this);

            if (response == JFileChooser.APPROVE_OPTION) {
                try (FileWriter fw = new FileWriter(fc.getSelectedFile()
                        + ".xml", false)) {
                    if (edit == false) {
                        //write all the stored variables to file
                        fw.write("<?xml version=\"1.0\"?>\n"
                                + "<gameFile>"
                                //gets all save data from main for every area
                                + MainWindow.getStoryDataText()
                                + "\n</gameFile>");

                    }
                    if (edit == true) {
                        try (FileWriter fwr = new FileWriter(fc.getSelectedFile()
                                + ".xml", false)) {
                            fwr.write(storyText);
                            MainWindow.setOutputs("Edits saved");
                        }
                    }
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
