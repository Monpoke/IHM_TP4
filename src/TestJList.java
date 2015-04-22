
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



public class TestJList {
  protected JLabel label;
  protected JList list;

  TestJList() {
    JFrame f = new JFrame("JList simple");
    
    String[] items = {"Paris", "Berlin", "Londres", "Rome", "Lisbonne", "Madrid"};
    
    list = new JList(items);
    // nombre d’items visibles
    list.setVisibleRowCount(3);
    // mode de sélection: 1 seul élément
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    // Listener 
    list.addListSelectionListener(new ListSelectionListener(){
        public void valueChanged(ListSelectionEvent e) {
          label.setText("Selection de " + list.getSelectedValue());
        }});
    
    // Indispensable pour avoir la barre de défilement
    JScrollPane scroll= new JScrollPane(list);
    // Affichage d’une bordure pour des raisons esthétiques
    Border b = BorderFactory.createTitledBorder("Liste");
    scroll.setBorder(b);

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(1,2));
    panel.add(scroll);
    label = new JLabel("Aucune selection");
    panel.add(label);

    f.getContentPane().add(panel);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.pack();
    f.setVisible(true);
  }

  public static void main2(String args[]){
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new TestJList();
      }
    });
  }
}