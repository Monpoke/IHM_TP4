
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Pierre
 */
class ListSelection implements ListSelectionListener {
    private File files2;
    private String path;
    private final JList jlist;
    private final String listTitle;


    ListSelection(File files2, String path, JList jlist, String listTitle) {
        this.files2 = files2;
        this.path = path;
        this.jlist = jlist;
        this.listTitle = listTitle;
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        String nPath =path + "\\" + ((JList)e.getSource()).getSelectedValue().toString();
        
        files2 = ListSelection.recupererFichiers(files2, nPath, jlist, listTitle);
    }
    
    
    /**
     * Permet de récupérer des fichiers
     * @param files
     * @param path
     * @param gaucheList 
     */
    public static File recupererFichiers(File files, String path, JList gaucheList, String title) {
        files = new File(path);
        
        String[] list = files.list();
        gaucheList.setListData(list);
        title = path;
        System.out.println(title);
        return files;
    }
}
