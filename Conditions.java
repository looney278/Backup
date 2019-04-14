package textualadventuregamegenerator;

import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class Conditions extends javax.swing.JFrame {

    public String conditions = " ";
    public boolean accessed = false;
    public ArrayList<JComboBox<String>> jComboBox = new ArrayList<>();
    public ArrayList<JTextField> jTextField = new ArrayList<>();

    public Conditions() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox13 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox13.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setTitle("Add Condition");

        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Action one");

        jLabel4.setText("Action two");

        jLabel5.setText("Item name");

        jLabel6.setText("Use text");

        jLabel7.setText("Room branch");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(58, 58, 58)
                        .addComponent(jLabel6)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel3)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(0, 19, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel6)
                        .addComponent(jLabel5))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 261, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Used to create the conditions interface for users to input what items
     * they want within their game
     */
    public void createInterface() {
        jComboBox.clear();
        jTextField.clear();
        for (int i = 0; i < 15; i++) {
            jComboBox.add(new JComboBox<>());
            this.add(jComboBox.get(i));
            //ifs for collumn 1,2,3
            if (i < 5) {
                jComboBox.get(i).setBounds(220, 30 + i * 30, 70, 25);
                //add text for action 1
                //search used to make other items in the room appear 
                jComboBox.get(i).addItem("pickup");
                //pickup adds to invent
                jComboBox.get(i).addItem("examine");
                jComboBox.get(i).addItem("smash");
            }
            if (i >= 5 && i < 10) {
                jComboBox.get(i).setBounds(315, 30 + (i - 5) * 30, 70, 25);
                //add text for action 2
                //None,use,eat,kick,drop,smash
                jComboBox.get(i).addItem("None");
                jComboBox.get(i).addItem("use");
                jComboBox.get(i).addItem("read");
                jComboBox.get(i).addItem("eat");
            }
            if (i >= 10) {
                jComboBox.get(i).setBounds(415, 30 + (i - 10) * 30, 70, 25);
                //add text for action 3
                for (int j = 0; j < 100; j++) {
                    if (j <= 9) {
                        jComboBox.get(i).addItem("0" + j);
                    }
                    if (j >= 10) {
                        jComboBox.get(i).addItem("" + j);
                    }
                }
            }
            jComboBox.get(i).setVisible(true);
        }
        for (int i = 0; i < 10; i++) {
            jTextField.add(new JTextField());
            this.add(jTextField.get(i));
            //ifs for collumn 1,2
            if (i < 5) {
                jTextField.get(i).setBounds(10, 30 + i * 30, 80, 25);
            }
            if (i >= 5) {
                jTextField.get(i).setBounds(120, 30 + (i - 5) * 30, 80, 25);
            }
            jTextField.get(i).setVisible(true);
        }
        this.setLayout(null);
        setVisible(true);
    }

    /**
     * Used to fill in blank sections of a conditions value to keep values in
     * the correct places
     *
     * @param evt
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String connection = "";
        String secondAction;
        String firstString;
        String thirdString;
        Boolean blank = false;
        for (int i = 0; i < 5; i++) {
            //if there is no item then input blank
            if ("".equals(jTextField.get(0).getText())) {
                conditions += " , , ";
                blank = true;
            }
            if ("".equals(jTextField.get(i).getText()) && i != 0) {
                conditions += ", , , ";
                blank = true;
            }
            //if room connection needed
            if (!"None".equals(jComboBox.get(i + 5).getSelectedItem().toString()) && !"00".equals(jComboBox.get(i + 10).getSelectedItem().toString())) {
                connection = jComboBox.get(i + 10).getSelectedItem().toString();
            }
            //if second actoin is needed
            if (!"None".equals(jComboBox.get(i + 5).getSelectedItem().toString())) {
                secondAction = jComboBox.get(i + 5).getSelectedItem().toString();
                firstString = "You " + secondAction + " the " + jTextField.get(i).getText();
                thirdString = " " + jTextField.get(i).getText() + connection;

            } else {
                firstString = "";
                secondAction = "";
                thirdString = "";
            }
            //if no blank run this
            if (blank == false) {
                conditions = "";
                conditions += firstString + ","
                        + jComboBox.get(i).getSelectedItem().toString() + " the " + jTextField.get(i).getText() + ","
                        + secondAction + thirdString;
            }
        }
        accessed = true;
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * Used to give the value of conditions to the play window to get text that
     * is needed for buttons
     *
     * @return conditions
     */
    public String conditions() {
        return conditions;
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Conditions().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
