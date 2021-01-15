package com.product.screen;

import com.product.screen.entity.ClientConfig;
import com.product.screen.entity.ServerConfig;
import com.product.screen.tool.XmlUtil;
import com.product.screen.tool.NodeInitTool;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;


@SpringBootTest
class ScreenApplicationTests {
    @Autowired
    private XmlUtil xmlUtil;
    @Autowired
    ScreenNavigator navigator;
    private ClientConfig clientConfig =  NodeInitTool.initClient();
    private ServerConfig serverConfig = NodeInitTool.initServer();

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
}
