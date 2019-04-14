package textualadventuregamegenerator;

import java.awt.BorderLayout;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class LoadImage extends javax.swing.JFrame {

    public boolean accessed = false;

    public LoadImage() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setTitle("Load Image");

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

    public void imageSelect() {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new java.io.File(".\\Images"));
        int response = fc.showOpenDialog(LoadImage.this);
        System.out.println(fc);
        if (response == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String sname = file.getPath();
            String[] splitSname;
            splitSname = sname.split("\\\\");
            //System.out.println(Arrays.toString(splitSname));
            int i = splitSname.length;
            //System.out.println(splitSname[i-2]);
            //System.out.println(splitSname[i-1]);
            sname = splitSname[i-2] + "\\" + splitSname[i-1];
            //System.out.println(sname);
            setLayout(new BorderLayout());
            setContentPane(new JLabel(new ImageIcon(sname)));
            pack();
            setVisible(true);
            //store file names here
            MainWindow.setImage(sname);
            MainWindow.setOutputs("File loaded\n");
        } else {
            MainWindow.setOutputs("Failed to load file\n");
        }

    }
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoadImage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
