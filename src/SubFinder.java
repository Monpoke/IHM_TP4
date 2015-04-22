
import java.awt.MenuComponent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

class SubFinder {

    private File file;
    private final Finder finder;
    private final String path;
    private JList liste;
    private JScrollPane scroll;

    private SubFinder child = null;

    SubFinder(Finder finder, String path) {
        this.finder = finder;
        this.path = path;

        init();
    }

    void updateView() {
        finder.addItem(scroll);
    }

    private File[] getFiles() {
        return file.listFiles();
    }

    /**
     * Called in constructor
     */
    private void init() {
        file = new File(path);
        liste = new JList();
        liste.setCellRenderer(new CellRenderer());
        scroll = new JScrollPane(liste);
        
        scroll.setBorder(BorderFactory.createTitledBorder(path));

        /**
         * Add listener
         */
        liste.addListSelectionListener(new ListSelectionListener() {
            private int i = 0;

            @Override
            public void valueChanged(ListSelectionEvent e) {
                i++;
                if (i % 2 != 1) {
                    return;
                }

                destroyChild();

                /**
                 * On teste si fichier ou dossier
                 */
                String nPath = path + "\\" + ((JList) e.getSource()).getSelectedValue().toString();
                File test = new File(nPath);

                if (test.isDirectory()) {
                    child = (finder.ajouterFinder(nPath));
                } else {
                    System.out.println("FICHIER");
                    finder.showDetails(test);
                }

            }
        });

        updateFiles();
    }

    private void updateFiles() {
        File[] fichiers = getFiles();

        List<SuperFile> listeStrings = new ArrayList<>();

        if (fichiers != null) {
            for (File fichier : fichiers) {
                String[] sub = fichier.list();
                if (fichier.isFile() || sub != null) {
                    listeStrings.add(new SuperFile(fichier));
                }
            }
        }
        liste.setListData(listeStrings.toArray());

    }

    public void destroyChild() {
        if (child == null) {
            return;
        }

        finder.removeSubFinder(child);
        child.destroyChild();
        child = null;
    }

    JScrollPane getItem() {
        return scroll;
    }
}
