package generateXML;


import org.testng.xml.XmlPackage;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.testng.xml.XmlSuite.ParallelMode.METHODS;


public class GenerateXML {

    public static void main(String[] args){

        ArrayList<String> suitesPaths = new ArrayList<String>();

        if(System.getenv("suite").equals("all_tests")) suitesPaths = TestPaths.ALL;



        XmlSuite suite = new XmlSuite();
        suite.setName("UI");
        suite.setThreadCount(4);
        suite.setParallel(METHODS);
        XmlTest test = new XmlTest(suite);
        ArrayList<XmlPackage> packages = new ArrayList<XmlPackage>();
        test.setThreadCount(4);
        test.setName(suitesPaths.get(0).split("\\Q.\\E")[4]);
        for (String path : suitesPaths){
            XmlPackage testPackage = null;
            test.setPreserveOrder(true);
            testPackage = new XmlPackage();
            testPackage.setName(path);
            packages.add(testPackage);
        }
        test.setXmlPackages(packages);
        File file = new File("src/test/resources/custom.xml");

        try {
            FileWriter writer = new FileWriter(file);
            writer.write(suite.toXml());
            writer.close();
        } catch (IOException e) {

        }
    }
}
