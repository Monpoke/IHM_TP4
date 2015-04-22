
import java.awt.GridLayout;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FileExplorer {

    public FileExplorer(String path) {

        JFrame f = new JFrame("FileExplorer");

        File files = null,
                files2 = null;
        JList gaucheList = new JList(),
                droiteList = new JList();

        // Update renderer
        gaucheList.setCellRenderer(new CellRenderer());
        droiteList.setCellRenderer(new CellRenderer());

        JLabel label = new JLabel("Aucune sélection");
        String listTitle = "Aucune sélection";
        
        try {
            files = ListSelection.recupererFichiers(files, path, gaucheList, path);
        } catch (Exception e) {
            System.out.println("OUPS. " + e.getMessage());
            System.exit(1);
        }

        JScrollPane scrollGauche = new JScrollPane(gaucheList);
        scrollGauche.setBorder(BorderFactory.createTitledBorder(files.getAbsolutePath()));

        JScrollPane scrollDroit = new JScrollPane(droiteList);
        scrollDroit.setBorder(BorderFactory.createTitledBorder(listTitle));

        /**
         * On ajoute au panneau
         */
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3));
        panel.add(label);
        panel.add(scrollGauche);
        panel.add(scrollDroit);
        panel.add(new JLabel("TEST"));

        /**
         * On enregistre les évènements
         */
        gaucheList.addListSelectionListener(new ListSelection(files2, path, droiteList, listTitle));

        f.getContentPane().add(panel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static void main(String args[]) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FileExplorer("C:\\");
            }
        });
    }

}
