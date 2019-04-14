package textualadventuregamegenerator;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.util.ArrayList;
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

    public class OpenedData implements Serializable {

        public boolean accessed = true;
        public String text = "Error";
        public String id = "Error";
        public String conditions = "Error";
        public String before = "Error";
        public String image = "Error";

        /**
         * Constructor for OpenedData object
         *
         * @param inputText
         * @param inputId
         * @param inputConditions
         * @param inputBefore
         * @param inputImage
         */
        public OpenedData(String inputText, String inputId, String inputConditions, String inputBefore, String inputImage) {
            text = inputText;
            id = inputId;
            conditions = inputConditions;
            before = inputBefore;
            image = inputImage;
        }

        /**
         * Returns the objects text data
         *
         * @param data
         * @return data.text
         */
        public String getOpenedDataText(OpenedData data) {
            return data.text;
        }

        /**
         * Returns the objects before data
         *
         * @param data
         * @return data.before
         */
        public String getBeforeDataText(OpenedData data) {
            return data.before;
        }

        /**
         * Returns the objects id data
         *
         * @param data
         * @return data.id
         */
        public String getOpenedDataId(OpenedData data) {
            return data.id;
        }

        /**
         * Returns the objects conditions data
         *
         * @param data
         * @return data.conditions
         */
        public String getOpenedDataConditions(OpenedData data) {
            return data.conditions;
        }
        
        public String getOpenedDataImage(OpenedData data) {
            return data.image;
        }
    }
    public MainWindow mainwindow;
    StringBuilder wholeDocument = new StringBuilder();
    ArrayList<OpenedData> loadedData = new ArrayList<>();
    public boolean accessed = true;

    /**
     * Used to select the file needed to play the game and then places its data
     * into an object that can be read by the Play class
     *
     * @throws IOException
     * @throws SAXException
     * @throws ParserConfigurationException
     * @throws InterruptedException
     * @throws InvocationTargetException
     * @throws URISyntaxException
     */
    public Open() throws IOException, SAXException, ParserConfigurationException, InterruptedException, InvocationTargetException, URISyntaxException {
        accessed = true;
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new java.io.File("."));
        int response = fc.showOpenDialog(Open.this);
        if (response == JFileChooser.APPROVE_OPTION) {
            File fXmlFile = new File(fc.getSelectedFile().getPath());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = (Document) dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("story");
            BufferedReader br = new BufferedReader(new FileReader(fXmlFile));
            String line;
            while ((line = br.readLine()) != null) {
                wholeDocument.append(line).append("\n");
            }
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String text = (eElement.getElementsByTagName("text").item(0).getTextContent());
                    String id = (eElement.getAttribute("id"));
                    String conditions = eElement.getElementsByTagName("conditions").item(0).getTextContent();
                    String before = (eElement.getElementsByTagName("beforeBranch").item(0).getTextContent());
                    String image = (eElement.getElementsByTagName("imageLocation").item(0).getTextContent());
                    OpenedData loaded = new OpenedData(text, id, conditions, before, image);
                    loadedData.add(loaded);
                }
            }
            MainWindow.setOutputs("File loaded\n");
        } else {
            MainWindow.setOutputs("Failed to load file\n");
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
