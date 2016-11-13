package moc.main;

import lombok.Data;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Administrator on 2016/11/6.
 */
@Data
public class Config {
    public final static String FILEPATH="/mocconfig.properties";
    public static void loadDefault(){
        Properties properties=new Properties();
        try {
            properties.load(Config.class.getResourceAsStream(FILEPATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        host=(properties.getProperty("host"));
        hostpath=(properties.getProperty("hostpath"));
    }

    static {
        loadDefault();
    }

    public static String host;
    public static String hostpath;

}
