
import java.io.File;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pierre
 */
public class SuperFile {

    private File file;

    public SuperFile(File file) {
        this.file = file;
    }
    

    public boolean isFile(){
        return file.isFile();
    }

    @Override
    public String toString() {
        return file.getName();
    }
    
    
}
