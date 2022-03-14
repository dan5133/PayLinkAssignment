import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Logger;

@Getter
@Setter
public class BaseClass {
    private static Logger log = Logger.getLogger(String.valueOf(BaseClass.class));
    private static Properties objRepo = null;

    @BeforeMethod

    public void init() {
        objRepo = loadObjRepository();
    }


    @SneakyThrows

    public static Properties loadObjRepository() {
        objRepo = new Properties();
        objRepo.load(new FileInputStream(new File("OR.properties")));
        return objRepo;
    }

    public static Properties getObjRepo() {
        return objRepo;
    }

    @AfterClass

    public void cleanUp() {
        objRepo = null;
    }
}
