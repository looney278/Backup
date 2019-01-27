package textualadventuregamegenerator;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class Open extends javax.swing.JFrame {

    BufferedReader br = null;
    FileReader fr = null;

    public Open() throws IOException, SAXException, ParserConfigurationException {
        final JFileChooser fc = new JFileChooser();
        int response = fc.showOpenDialog(Open.this);
        if (response == JFileChooser.APPROVE_OPTION) {

            File fXmlFile = new File(fc.getSelectedFile().getPath());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = (Document) dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("story");
            
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    System.out.println("Story id : " + eElement.getAttribute("id"));
                    System.out.println("Story text : " + eElement.getElementsByTagName("text").item(0).getTextContent());
                    System.out.println("Inventory : " + eElement.getElementsByTagName("inventory").item(0).getTextContent());
                    System.out.println("After branch : " + eElement.getElementsByTagName("afterBranch").item(0).getTextContent());
                    System.out.println("Before branch : " + eElement.getElementsByTagName("beforeBranch").item(0).getTextContent());

                }
            }
            //place each type of section to place
            //into their correct locations
        } else {
            System.out.println("Failed to load file");
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
