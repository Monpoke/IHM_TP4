
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Finder extends JFrame {

    private ArrayList<SubFinder> subFinders = new ArrayList<>();
    
    private final JPanel panel;
    private final JLabel affichageDroite;

    public Finder(String path) {
        super("Finder");

        /**
         * Affichage des détails
         */
        affichageDroite = new JLabel();

        /**
         * Panel
         */
        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ajouterFinder(path);

        getContentPane().add(panel);
        pack();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        
        
    }

    /**
     * On ajoute un finder.
     *
     * @param path
     */
    protected SubFinder ajouterFinder(String path) {
        SubFinder subFinder = new SubFinder(this, path);
        subFinders.add(subFinder);
        
        subFinder.updateView();
        
        return subFinder;
    }

    public JPanel getPanel() {
        return panel;
    }

    
    

    void addItem(JScrollPane scroll) {
        panel.add(scroll);
        panel.remove(affichageDroite);
        redraw();
    }


    void removeSubFinder(SubFinder child) {
        subFinders.remove(child);
        panel.remove(child.getItem());
        panel.remove(affichageDroite);
        redraw();
    }

    void showDetails(File file){
        ReaderFile.update(affichageDroite, file);
        panel.add(affichageDroite);
        redraw();
    }
    
    void redraw(){
        getContentPane().validate();
        getContentPane().repaint();
    }
    
    
    /**
     * Main
     *
     * @param args
     */
    public static void main(String args[]) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Finder(".");
            }
        });
    }
    
}
