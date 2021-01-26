package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class Configutil {




    public static Properties configdatafile(String filename)
    {
        Properties Prop =new Properties();
        File datafile = new File( System.getProperty("user.dir") + "/Util/"+filename);
        FileInputStream inputfilestream = null;
        try {
            inputfilestream = new FileInputStream(datafile);
        } catch (FileNotFoundException e) {

        }
        try {
            Prop.load(inputfilestream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Prop;
    }

        
}
