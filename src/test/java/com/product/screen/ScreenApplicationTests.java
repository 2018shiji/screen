package com.product.screen;

import com.google.common.io.CharStreams;
import com.google.common.io.Resources;
import com.product.screen.entity.ClientConfig;
import com.product.screen.entity.ServerConfig;
import com.product.screen.entity.WebClientCfg;
import com.product.screen.entity.WebServerCfg;
import com.product.screen.tool.XmlUtil;
import com.product.screen.tool.NodeInitTool;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;


@SpringBootTest
class ScreenApplicationTests {
    @Autowired
    private XmlUtil xmlUtil;
    @Autowired
    ScreenNavigator navigator;
    private WebClientCfg clientConfig =  NodeInitTool.initClient();
    private WebServerCfg serverConfig = NodeInitTool.initServer();

    @Test
    void contextLoads() {
        String result = xmlUtil.convertToXmlString(clientConfig, "utf-8");
        System.out.println(result);
    }

    @Test
    void testComplexXmlString(){
        String result = xmlUtil.convertToXmlString(serverConfig, "utf-8");
        System.out.println(result);
    }

    @Test
    void testXmlFile() {
        xmlUtil.convertToXmlFile(clientConfig, "utf-8", "E:\\result\\xmlFile.xml");
    }

    @Test
    void testComplexXmlFile(){
        xmlUtil.convertToXmlFile(serverConfig, "utf-8", "E:\\result\\complexXmlFile.xml");
    }

    @Test
    void testXmlStringToBean(){
        String result = xmlUtil.convertToXmlString(clientConfig, "utf-8");
        System.out.println(result);
        ClientConfig objectResult = xmlUtil.convertStringToObject(result, ClientConfig.class);
        System.out.println(objectResult);
    }

    @Test
    void testComplexXmlStringToBean(){
        String result = xmlUtil.convertToXmlString(serverConfig, "utf-8");
        System.out.println(result);
        ServerConfig objectResult = xmlUtil.convertStringToObject(result, ServerConfig.class);
        System.out.println(objectResult);
    }

    @Test
    void testXmlFileToBean(){
        File file = xmlUtil.convertToXmlFile(clientConfig, "utf-8", "E:\\result\\xmlFile.xml");
        System.out.println(file);
        ClientConfig objectResult = xmlUtil.convertFileToObject(file, ClientConfig.class);
        System.out.println(objectResult);
    }

    @Test
    void testComplexXmlFileToBean(){
        File file = xmlUtil.convertToXmlFile(serverConfig, "utf-8", "E:\\result\\complexXmlFile.xml");
        System.out.println(file);
        ServerConfig objectResult = xmlUtil.convertFileToObject(file, ServerConfig.class);
        System.out.println(objectResult);
    }

    @Test
    void testNavigatorSCC(){
        navigator.setClientConfig(NodeInitTool.initClient());
    }

    @Test
    void testToString(){
        List<String> test = Arrays.asList("12","23","34");
        String testStr = test.toString();
        System.out.println(testStr);
        String[] strings = StringUtils.commaDelimitedListToStringArray(testStr.substring(1, testStr.length() - 1));
        List<String> stringList = Arrays.asList(strings);
        System.out.println(stringList);
    }

    @Test
    void testStrTrim(){
        String test = "   38474    ";
        System.out.println(test.trim());
    }

    @Test
    void testGuavaResources() {
        try {
            URL globalCfg = Resources.getResource("globalCfg");
            String path = globalCfg.getPath();
            System.out.println(path);
            BufferedInputStream content = (BufferedInputStream)globalCfg.getContent();
            String result = CharStreams.toString(new InputStreamReader(content, Charset.forName("UTF-8")));
            System.out.println(result);

            File file = new File(path);
            System.out.println(file);
            if(!file.exists()){
                try{
                    boolean newFile = file.createNewFile();
                    System.out.println(newFile);
                } catch (Exception e){e.printStackTrace();}
            }

        }catch (Exception e){e.printStackTrace();}
    }

    @Test
    void testClasspathResource() throws IOException {
        File global = new ClassPathResource("globalCfg").getFile();

    }
}
