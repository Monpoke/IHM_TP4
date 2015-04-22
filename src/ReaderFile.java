
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Pierre
 */
class ReaderFile {

    static void update(JLabel affichageDroite, File file) {

        String[] lab = file.getName().split("\\.");
        String ext = lab[lab.length - 1].toLowerCase();
        reset(affichageDroite);

        if ("txt".equals(ext) || "java".equals(ext)) {
            affichageDroite.setText(readFile(file.getAbsolutePath()));

        } else if (isImage(ext)) {
            affichageDroite.setIcon(new ImageIcon(file.getAbsolutePath()));
            affichageDroite.setText(file.getName());
        } else {
            affichageDroite.setText("Fichier " + file.getName() + " d'extension " + ext);
        }

    }

    private static boolean isImage(String ext) {

        List<String> c = new ArrayList<>();
        c.add("jpg");
        c.add("png");
        c.add("gif");

        return c.contains(ext);
    }

    private static void reset(JLabel affichageDroite) {
        affichageDroite.setText("");
        affichageDroite.setIcon(null);
    }

    private static String readFile(String path) {
        BufferedReader br = null;
        String r = "";
        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader(path));

            while ((sCurrentLine = br.readLine()) != null) {
                r += (sCurrentLine) + "\n";
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        return r;
    }
}
