package textualadventuregamegenerator;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class MainWindow extends javax.swing.JFrame {

    public BugReport bugReport;
    public Conditions conditions;
    public Play play;
    public Open open;
    public Edit edit;
    public Save save;
    public LoadImage imageLoad;
    boolean finished = false;
    static ArrayList<String> storyData = new ArrayList<>();
    static String imageLocation = "";

    /**
     * Used to initialize the program
     *
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws InterruptedException
     * @throws InvocationTargetException
     */
    public MainWindow() throws IOException, SAXException, ParserConfigurationException, InterruptedException, InvocationTargetException {
        this.setResizable(false);
        conditions = new Conditions();
        conditions.accessed = false;
        imageLoad = new LoadImage();
        imageLoad.accessed = false;
        play = new Play();
        initComponents();
        editButton.setVisible(false);
        outputs.setEditable(false);
    }

    public static void setOutputs(String str) {
        outputs.append(str);
    }

    public static void setImage(String str) {
        imageLocation = str;
    }

    public static String getStoryDataText() {
        String storyDataText;
        storyDataText = storyData.toString().substring(1, storyData.toString().length() - 1);
        return storyDataText;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        OpenButton = new javax.swing.JButton();
        SaveButton = new javax.swing.JButton();
        BugReportButton = new javax.swing.JButton();
        FinishButton = new javax.swing.JButton();
        PlayButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        conditionsButton = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        editButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        outputs = new javax.swing.JTextArea();
        loadImage = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Textual Adventure Game Generator");

        OpenButton.setText("Open");
        OpenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenButtonActionPerformed(evt);
            }
        });

        SaveButton.setText("Add Room");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        BugReportButton.setText("Bug");
        BugReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BugReportButtonActionPerformed(evt);
            }
        });

        FinishButton.setText("Save/ Finish");
        FinishButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FinishButtonActionPerformed(evt);
            }
        });

        PlayButton.setText("Play");
        PlayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayButtonActionPerformed(evt);
            }
        });

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        conditionsButton.setText("Conditions");
        conditionsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conditionsButtonActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99" }));

        jLabel1.setText("Story ID");

        jLabel2.setText("Room before ID");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99" }));

        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        outputs.setColumns(20);
        outputs.setRows(5);
        jScrollPane3.setViewportView(outputs);

        loadImage.setText("Load Image");
        loadImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadImageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(SaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(FinishButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PlayButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(OpenButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(conditionsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(loadImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BugReportButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(OpenButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SaveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FinishButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(editButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PlayButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(loadImage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(conditionsButton)
                        .addGap(11, 11, 11)
                        .addComponent(BugReportButton)
                        .addGap(32, 32, 32)))
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Used to begin the Open class to select what file is needed
     *
     * @param evt
     */
    private void OpenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenButtonActionPerformed
        try {
            try {
                editButton.setVisible(true);
                open = new Open();
                play.playOpen = open;
            } catch (InterruptedException | InvocationTargetException | URISyntaxException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException | SAXException | ParserConfigurationException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_OpenButtonActionPerformed

    /**
     * Used to save the story generation
     *
     * @param evt
     */
    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        try {
            StoryText();
        } catch (IOException | SAXException | ParserConfigurationException | InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SaveButtonActionPerformed

    /**
     * Used to begin the BugReport class to submit bugs in the program
     *
     * @param evt
     */
    private void BugReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BugReportButtonActionPerformed
        bugReport = new BugReport();
        bugReport.setVisible(true);
    }//GEN-LAST:event_BugReportButtonActionPerformed

    /**
     * Used to begin the conditions class to select what items go in game
     *
     * @param evt
     */
    private void conditionsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conditionsButtonActionPerformed
        conditions.createInterface();
    }//GEN-LAST:event_conditionsButtonActionPerformed

    /**
     * Used to begin the Play class to start the game
     *
     * @param evt
     */
    private void PlayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayButtonActionPerformed
        play.runGame(0);
    }//GEN-LAST:event_PlayButtonActionPerformed

    /**
     * Used to set the story to finished to complete the saved file
     */
    private void FinishButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FinishButtonActionPerformed
        try {
            finished = true;
            StoryText();
        } catch (IOException | SAXException | ParserConfigurationException | InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_FinishButtonActionPerformed

    /**
     * Used to run the Edit class to allow an existing file to be edited
     *
     * @param evt
     */
    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        edit = new Edit();
        edit.setVisible(true);
        edit.jTextArea1.append(open.wholeDocument.toString());
    }//GEN-LAST:event_editButtonActionPerformed

    private void loadImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadImageActionPerformed
        imageLoad = new LoadImage();
        imageLoad.imageSelect();
        imageLoad.setVisible(true);
    }//GEN-LAST:event_loadImageActionPerformed

    public static void main(String args[]) throws IOException, SAXException, SAXException, InvocationTargetException, InterruptedException, ParserConfigurationException {
        try {
            new MainWindow().setVisible(true);
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Used to set which items from the classes need to be added to the save
     * file and then runs the save function
     *
     * @throws IOException
     * @throws org.xml.sax.SAXException
     * @throws javax.xml.parsers.ParserConfigurationException
     * @throws java.lang.InterruptedException
     * @throws java.lang.reflect.InvocationTargetException
     */
    public void StoryText() throws IOException, SAXException, ParserConfigurationException, InterruptedException, InvocationTargetException {
        String storyText;
        String condition = " , , , , , , , , , , , , , , ";
        String id;
        String before;
        String locationOfImage;
        boolean edited = false;
        before = jComboBox2.getSelectedItem().toString();
        id = jComboBox1.getSelectedItem().toString();
        storyText = jTextArea2.getText();
        if (this.conditions.accessed == true) {
            condition = this.conditions.conditions();
        }
        locationOfImage = MainWindow.imageLocation;
        this.save = new Save(storyText, condition, id, before, finished, edited,
                locationOfImage);
        storyData.add(save.sectionString);
        if (finished == true) {
            setOutputs("All areas saved\n");
        }
        if (finished == false) {
            setOutputs("Area added\n");
        }
        finished = false;
        /* System.out.println(storyData.size());
        //outputs the current save data before it is saved
        for (int i = 0; i < (storyData.size()); i++) {
            System.out.println(storyData.get(i));
        }*/
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BugReportButton;
    private javax.swing.JButton FinishButton;
    private javax.swing.JButton OpenButton;
    public javax.swing.JButton PlayButton;
    private javax.swing.JButton SaveButton;
    private javax.swing.JButton conditionsButton;
    public javax.swing.JButton editButton;
    public javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JTextArea jTextArea2;
    private javax.swing.JButton loadImage;
    public static javax.swing.JTextArea outputs;
    // End of variables declaration//GEN-END:variables

}
