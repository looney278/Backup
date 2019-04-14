package textualadventuregamegenerator;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Play extends javax.swing.JFrame implements ActionListener, Serializable {

    String[] conditionSplit;
    ArrayList<JButton> buttons = new ArrayList<>();
    ArrayList<String> inventory = new ArrayList<>();
    ArrayList<String> room = new ArrayList<>();
    ArrayList<String> items = new ArrayList<>();
    ArrayList<String> itemID = new ArrayList<>();
    Open playOpen;
    int storySection = 0;
    boolean searched = false;
    String currentID = "";
    String playStoryTextStore = "";
    boolean firstRoom = true;
    String nextID = "";

    public Play() {
        initComponents();
    }

    /**
     * Used to create all of the buttons that are needed for the game, each of
     * the buttons are reloaded and cleared when the room changes
     *
     * @param buttonAmount
     */
    public void createButtons(int buttonAmount) {
        buttons.clear();
        //empties the array for new rooms when function called again
        //creates the amount of buttons needed for each room
        //set all to not visible first
        for (int i = 0; i < (buttonAmount); i++) {
            buttons.add(new JButton());
            buttons.get(i).setVisible(false);
            this.setLayout(null);
            setVisible(true);
            this.add(buttons.get(i));
            buttons.get(i).addActionListener(this);
            //set all buttons texts and locations here
            //requires read in document to have 5 full actions or array gets 
            //null calls and breaks
            if (i < buttonAmount / 2) {
                buttons.get(i).setText(conditionSplit[(i * 3) + 1]);
                buttons.get(i).setBounds(10, 200 + i * 25, 200, 25);
                //hide buttons until room is searched
                if (searched == true) {
                    buttons.get(i).setVisible(true);
                }
                //if string isnt empty run item check
                if (!" ".equals(buttons.get(i).getText())) {
                    int t = conditionSplit[(i * 3) + 2].length();
                    String itemText = conditionSplit[(i * 3) + 2].substring(4, t - 2);
                    //for all buttons if item already owned dont create button
                    if (inventory.contains(itemText) && buttons.get(i).getText().contains("pickup")) {
                        buttons.get(i).setVisible(false);
                    }
                }

            }
            if (i >= buttonAmount / 2) {

                // 13 for 5 buttons 10 for 4 7 for 3 4 for 2 //(i * 3) - 13]
                buttons.get(i).setText(conditionSplit[(i * 3) - 13]);
                buttons.get(i).setBounds(240, 200 + ((i - 5) * 25), 200, 25);
                //removes the text used to set the room direction eg 02 room id for secret room
                if (buttons.get(i).getText().matches(".*\\d+.*")) {
                    String buttonText = buttons.get(i).getText();
                    buttons.get(i).setText(buttonText.substring(0, buttonText.length() - 2));
                    if (playOpen.loadedData.get(storySection).getOpenedDataText(playOpen.loadedData.get(storySection)).contains("door")) {
                        buttons.get(i).setText(buttonText.substring(0, buttonText.length() - 2) + " on the door");
                    }
                    if (playOpen.loadedData.get(storySection).getOpenedDataText(playOpen.loadedData.get(storySection)).contains("box")) {
                        buttons.get(i).setText(buttonText.substring(0, buttonText.length() - 2) + " on the box");
                    }
                }
                //used to make buttons appear based on if item in invent
                if (!" ".equals(buttons.get(i).getText())) {
                    int t = conditionSplit[(i * 3) - 13].length();
                    String itemText = conditionSplit[(i * 3) - 13].substring(4, t - 2);
                    //for all buttons if item already owned dont create button
                    if (inventory.contains(itemText) && buttons.get(i).getText().contains("use")) {
                        buttons.get(i).setVisible(true);
                    }
                }
            }
            //removes empty buttons
            if (" ".equals(buttons.get(i).getText())) {
                buttons.get(i).setVisible(false);
            }
        }
        addPreviouslyDroppedItems();
    }

    /**
     * Used to run the game
     *
     * @param storySection
     */
    public void runGame(int storySection) {
        playStoryText.setLineWrap(true);
        playStoryText.setWrapStyleWord(true);
        buttons.clear();
        try {
            if (playOpen.accessed == true) {
                if (firstRoom == false) {
                    playStoryText.setText(playStoryTextStore + "\n" + playOpen.loadedData.get(storySection).getOpenedDataText(playOpen.loadedData.get(storySection)));
                } else {
                    playStoryText.setText(playOpen.loadedData.get(storySection).getOpenedDataText(playOpen.loadedData.get(storySection)));
                }
                currentID = playOpen.loadedData.get(storySection).id;
                //conditions will be item,actiontext,action
                //System.out.println(playOpen.loadedData.get(storySection).getOpenedDataConditions(playOpen.loadedData.get(storySection)));
                conditionSplit = playOpen.loadedData.get(storySection).getOpenedDataConditions(playOpen.loadedData.get(storySection)).split(",");
                createButtons((conditionSplit.length / 3) * 2);
                if (!"".equals(playOpen.loadedData.get(storySection).getOpenedDataImage(playOpen.loadedData.get(storySection)))) {
                    addImageToGame();
                } else {
                    jLabel1.setVisible(false);
                }
                playStoryText.setEditable(false);
            }
        } catch (NullPointerException e) {
            MainWindow.setOutputs("No data loaded\n");
        }

    }

    /**
     * Used to reset all of the games buttons
     */
    public void resetButtons() {
        for (int i = 0; i < (buttons.size()); i++) {
            buttons.get(i).setVisible(false);
        }
    }

    /**
     * Used to change the room to the next area id or the previous one
     *
     * @param direction
     */
    public void changeRoom(boolean direction) {
        searched = false;
        resetButtons();
        //  System.out.println("Working");
        //if condition has door open text then reload story to the new
        //story id that is the same branch as the door open
        //then reload the room or append to the main text, append
        //will be alot easier probably
        //get current id then change based on that ids afterbranch
        //System.out.println(playOpen.loadedData.get(storySection).after);
        int j = playOpen.loadedData.size();
        //true is forwards false is backwards
        if (direction == true) {
            for (int i = 0; i < (j); i++) {
                // if the story id = the new side section
                //   previousRoom = playOpen.loadedData.get(i).id;
                if (nextID.equals(playOpen.loadedData.get(i).id)) {
                    j = 0;
                    playStoryText.append("\nYou change area");
                    storySection = i;
                    firstRoom = false;
                    playStoryTextStore = playStoryText.getText();
                    runGame(storySection);
                }
            }
        }
        // back section needs redoing
        if (direction == false) {
            for (int i = 0; i < (j); i++) {
                if (playOpen.loadedData.get(storySection).before.equals(playOpen.loadedData.get(i).id)) {
                    j = 0;
                    storySection = i;
                    firstRoom = false;
                    playStoryTextStore = playStoryText.getText();
                    runGame(storySection);
                }
            }
        }
    }

    public void addImageToGame() {
        String sname = playOpen.loadedData.get(storySection).getOpenedDataImage(playOpen.loadedData.get(storySection));
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(sname).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        jLabel1.setIcon(imageIcon);
        jLabel1.setVisible(true);

    }

    /**
     * Used to see if you can move to new area based on if item is in invent
     *
     * @param item
     * @param i
     */
    public void inventCheck(String item, int i) {
        if (inventory.contains(item)) {
            boolean direction = true;
            //if room is not the same room change to new one
            if (!currentID.equals(nextID)) {
                playStoryText.append("\n");
                playStoryText.append(conditionSplit[(i * 3) - 15]);
                changeRoom(direction);
            } else {
                playStoryText.append("\n");
                playStoryText.append(conditionSplit[(i * 3) - 15]);
            }
        } else {
            playStoryText.append("\nYou do not have the required item");
        }
    }

    /**
     * Used to add items that are dropped and left in rooms to allow them to be
     * picked up when going back to the room
     *
     */
    public void addPreviouslyDroppedItems() {
        if (room.size() > 0) {
            for (int i = 0; i < room.size() - 1; i++) {
                if (room.get(i).equals(currentID) && items.size() > 0) {
                    buttons.add(new JButton());
                    this.setLayout(null);
                    setVisible(true);
                    this.add(buttons.get(i));
                    buttons.get(i).setText("pickup the " + items.get(i));
                    buttons.get(i).setBounds(10, 200 + (i + 5) * 25, 180, 25);
                    buttons.get(i).setVisible(true);
                }
            }
        }
    }

    /**
     * Used to store the items of players when they are picked up along with the
     * room they are dropped in and the room that the item leads to
     *
     * @param item
     * @param id
     * @param iid
     * @param add
     */
    public void storeRoomItems(String item, String id, String iid, boolean add) {
        if (add == true) {
            items.add(item);
            room.add(id);
            itemID.add(iid);
        }
        if (add == false) {
            items.remove(item);
            room.remove(id);
            itemID.remove(iid);
        }

    }

    /**
     * Used to pickup items that are on the floor and add them to a players
     * inventory so it can be used at a later point
     *
     * @param i
     */
    public void pickupItem(int i) {
        //add text needs to be filtered to only the items text
        String itemText;
        String itemId = nextID;
        itemText = buttons.get(i).getText().substring(11);
        //adds the item to the players inventory
        inventory.add(itemText);
        //adds player inventory items to screen when buttons created
        jComboBox1.addItem(inventory.get(inventory.size() - 1));
        storeRoomItems(itemText, currentID, itemId, false);
        //for all buttons on right side make visible if picked up
        //eg if button contains use + itemname then make visible
        for (int j = 0; j < buttons.size(); j++) {
            if (buttons.get(j).getText().contains(itemText) && buttons.get(j).getText().contains("use")) {
                buttons.get(j).setVisible(true);
            }
            if (buttons.get(j).getText().contains(itemText) && buttons.get(j).getText().contains("read")) {
                buttons.get(j).setVisible(true);
            }
            if (buttons.get(j).getText().contains(itemText) && buttons.get(j).getText().contains("eat")) {
                buttons.get(j).setVisible(true);
            }
            if (" ".equals(buttons.get(i).getText())) {
                buttons.get(i).setVisible(false);
            }
        }

    }

    /**
     * Used to save the players serialized data so that they can load the game
     * at a later point to continue
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void saveProgress() throws FileNotFoundException, IOException {
        String filename = "file.ser";
        MainWindow.setOutputs("Progress saved\n");
        loadedData data = new loadedData(conditionSplit, inventory, room,
                items, itemID, playOpen, storySection, searched, currentID, playStoryTextStore,
                firstRoom, nextID);
        // Serialization  
        //Saving of object in a file 
        FileOutputStream file = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(file);
        // Method for serialization of object 
        out.writeObject(data);
        out.close();
        file.close();
    }

    /**
     * Used to load the players serialized data so that they can continue from
     * where they last saved
     */
    public void loadProgress() {
        String filename = "file.ser";
        loadedData data;
        resetButtons();
        jComboBox1.removeAllItems();
        buttons.clear();
        this.dispose();
        //completely run new play?
        //if file is in same folder load else do nothing
        try {
            // Reading the object from a file 
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);
            // Method for deserialization of object 
            data = (loadedData) in.readObject();
            in.close();
            file.close();
            conditionSplit = data.conditionSplit;
            inventory = data.inventory;
            room = data.room;
            items = data.items;
            itemID = data.itemID;
            playOpen = data.playOpen;
            storySection = data.storySection;
            searched = data.searched;
            currentID = data.currentID;
            playStoryTextStore = data.playStoryTextStore;
            firstRoom = data.firstRoom;
            nextID = data.nextID;
            runGame(storySection);
            for (int i = 0; i < inventory.size(); i++) {
                jComboBox1.addItem(inventory.get(i));
            }
            MainWindow.setOutputs("Progress loaded\n");

        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        back = new javax.swing.JButton();
        Drop = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        Search = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        playStoryText = new javax.swing.JTextArea();
        saveGame = new javax.swing.JButton();
        loadGame = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setTitle("Play");

        back.setText("Previous area");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        Drop.setText("Drop");
        Drop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DropActionPerformed(evt);
            }
        });

        Search.setText("Search");
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });

        playStoryText.setColumns(20);
        playStoryText.setRows(5);
        jScrollPane1.setViewportView(playStoryText);

        saveGame.setText("Save");
        saveGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveGameActionPerformed(evt);
            }
        });

        loadGame.setText("Load");
        loadGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadGameActionPerformed(evt);
            }
        });

        jLabel1.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Drop, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(back)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(loadGame)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveGame)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(saveGame)
                            .addComponent(loadGame))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Drop)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Search)
                            .addComponent(back))
                        .addGap(0, 310, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Used to go to the last area that a player was in
     *
     * @param evt
     */
    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        boolean direction = false;
        if (storySection != 0) {
            playStoryText.append("\nYou go back");
            changeRoom(direction);
        } else {
            playStoryText.append("\nYou cannot go back anymore");
        }
    }//GEN-LAST:event_backActionPerformed

    /**
     * Used to drop items on the floor in game so that they appear in the area
     * when dropped
     *
     * @param evt
     */
    private void DropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DropActionPerformed
        int i = buttons.size();
        if (jComboBox1.getSelectedItem() != null && searched == true) {
            String droppedItem = jComboBox1.getSelectedItem().toString();
            String iid = ""; //something here to remove the id from storage to keep everything inline
            jComboBox1.removeItem(jComboBox1.getSelectedItem());
            inventory.remove(droppedItem);
            storeRoomItems(droppedItem, currentID, iid, true);
            playStoryText.append("\nYou drop the " + droppedItem);
            //creates button to retake dropped items needs to include remembering
            //what room its in so item persists through room transitions
            buttons.add(new JButton());
            this.setLayout(null);
            setVisible(true);
            this.add(buttons.get(i));
            buttons.get(i).addActionListener(this);
            buttons.get(i).setText("pickup the " + droppedItem);
            //fix to stop overlap on dropped items
            buttons.get(i).setBounds(10, 200 + (i - 5) * 25, 180, 25);
            buttons.get(i).setVisible(true);
        }
        if (jComboBox1.getSelectedItem() != null && searched == false) {
            playStoryText.append("\nYou should search the area first");
        }
    }//GEN-LAST:event_DropActionPerformed

    /**
     * Used to search the games rooms and display the items that are hidden
     * there
     *
     * @param evt
     */
    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        //when search is pressed it shows other items
        if (searched == false) {
            playStoryText.append("\nYou search the area");
            searched = true;
            resetButtons();
            createButtons((conditionSplit.length / 3) * 2);
        } else {
            playStoryText.append("\nYou have already searched this area");
        }

     }//GEN-LAST:event_SearchActionPerformed

    /**
     * Used to save the serialized data so that a player can save their progress
     * mid game
     *
     * @param evt
     */
    private void saveGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveGameActionPerformed
        try {
            saveProgress();

        } catch (IOException ex) {
            Logger.getLogger(Play.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_saveGameActionPerformed

    /**
     * Used to load the serialized data to get where the player was when they
     * last saved their progress
     *
     * @param evt
     */
    private void loadGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadGameActionPerformed
        loadProgress();
    }//GEN-LAST:event_loadGameActionPerformed

    public void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Drop;
    private javax.swing.JButton Search;
    private javax.swing.JButton back;
    public javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JDialog jDialog1;
    public javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loadGame;
    public javax.swing.JTextArea playStoryText;
    private javax.swing.JButton saveGame;
    // End of variables declaration//GEN-END:variables

    /**
     * Used to select what happens based on what button is pressed
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i <= 9; i++) {
            if (" ".equals(buttons.get(i).getText())) {
                buttons.get(i).setVisible(false);
            }
            if (e.getSource() == buttons.get(i)) {
                //if 1to5 is clicked show 6-10 button
                if (i <= 4) {
                    if ("".equals(buttons.get(i + 5).getText())) {
                        buttons.get(i + 5).setVisible(false);
                    } else {
                        //if the button says to pickup add to invent
                        if ((buttons.get(i).getText()).contains("pickup")) {
                            pickupItem(i);
                        }
                        //if the button says to drop remove from invent
                        if ((buttons.get(i).getText()).contains("drop")) {
                            buttons.get(i + 5).setVisible(false);
                        }
                        if (!" ".equals(conditionSplit[(i * 3) + 1])) {
                            //adds use text eg use key on door
                            playStoryText.append("\nYou " + conditionSplit[(i * 3) + 1]);
                        }
                        if (" ".equals(conditionSplit[(i * 3) + 1])) {
                            playStoryText.append("\nYou " + buttons.get(i).getText());
                        }
                        buttons.get(i).setVisible(false);
                    }
                }
                //if 6-10 is clicked run change room forward
                if (i >= 5 && i <= 10) {
                    if (conditionSplit[((i - 5) * 3) + 2].contains("use")) {
                        if (conditionSplit[((i - 5) * 3) + 2].matches(".*\\d+.*")) {
                            int t = conditionSplit[((i - 5) * 3) + 2].length();
                            nextID = conditionSplit[((i - 5) * 3) + 2].substring(t - 2, t);
                            //System.out.println(nextID);
                            //checks if the item needed to open is in invent
                            //- first 4 letters -2nd 2 letters
                            String item;
                            item = conditionSplit[((i - 5) * 3) + 2].substring(4, t - 2);
                            inventCheck(item, i);
                            break;
                        }
                    }

                    if (conditionSplit[((i - 5) * 3) + 2].contains("read")) {
                        int t = conditionSplit[((i - 5) * 3) + 2].length();
                        String item;
                        item = conditionSplit[((i - 5) * 3) + 2].substring(5, t);
                        inventCheck(item, i);
                        break;

                    }
                    if (conditionSplit[((i - 5) * 3) + 2].contains("eat")) {
                        buttons.get(i).setVisible(false);
                        int t = conditionSplit[((i - 5) * 3) + 2].length();
                        String item;
                        item = conditionSplit[((i - 5) * 3) + 2].substring(4, t);
                        inventCheck(item, i);
                        break;
                        //add bit to remove eaten item
                    }
                }
            }
        }
        if (buttons.size() >= 10) {
            for (int i = 9; i < buttons.size(); i++) {
                if (" ".equals(buttons.get(i).getText())) {
                    buttons.get(i).setVisible(false);
                }
                if (e.getSource() == buttons.get(i)) {
                    playStoryText.append("\nYou " + buttons.get(i).getText());
                    pickupItem(i);
                    buttons.get(i).setVisible(false);
                    buttons.remove(i);
                }
            }
        }
    }

    private static class loadedData implements Serializable {

        String[] conditionSplit;
        ArrayList<String> inventory;
        ArrayList<String> room;
        ArrayList<String> items;
        ArrayList<String> itemID;
        Open playOpen;
        int storySection;
        boolean searched;
        String currentID;
        String playStoryTextStore;
        boolean firstRoom;
        String nextID;

        /**
         * Used to set the loadedData objects values to the current values in
         * game so that it can be serialized
         *
         * @param a
         * @param c
         * @param d
         * @param e
         * @param f
         * @param g
         * @param h
         * @param i
         * @param j
         * @param k
         * @param l
         * @param m
         */
        public loadedData(String[] a, ArrayList<String> c,
                ArrayList<String> d, ArrayList<String> e, ArrayList<String> f, Open g, int h,
                boolean i, String j, String k, Boolean l, String m) {
            this.conditionSplit = a;
            this.inventory = c;
            this.room = d;
            this.items = e;
            this.itemID = f;
            this.playOpen = g;
            this.storySection = h;
            this.searched = i;
            this.currentID = j;
            this.playStoryTextStore = k;
            this.firstRoom = l;
            this.nextID = m;
        }
    }
}
