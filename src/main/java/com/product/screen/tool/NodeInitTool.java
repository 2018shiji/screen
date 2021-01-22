package com.product.screen.tool;

import com.product.screen.entity.WebClientCfg;
import com.product.screen.entity.WebServerCfg;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class NodeInitTool {

    public static WebClientCfg initClient(){
        WebClientCfg client = new WebClientCfg();
        List<WebClientCfg.Node> clientNodes = new ArrayList<>();
        WebClientCfg.Node node1 = new WebClientCfg.Node();
        node1.setId("HX1F_S1");
        node1.setTitles("");
        node1.setExeFile("");
        node1.setParams("T4AR全景");
        node1.setRemark("AR");
        clientNodes.add(node1);

        WebClientCfg.Node node2 = new WebClientCfg.Node();
        node2.setId("HX1F_S2");
        node2.setTitles("");
        node2.setExeFile("DSSClient.exe");
        node2.setParams("");
        node2.setRemark("02-5G无人集卡_实时");
        clientNodes.add(node2);

        WebClientCfg.Node node3 = new WebClientCfg.Node();
        node3.setId("HX1F_S2_VIDEO");
        node3.setTitles("无人驾驶-928-车内");
        node3.setExeFile("");
        node3.setParams("");
        node3.setRemark("02-5g_无人集卡_录像");
        clientNodes.add(node3);

        WebClientCfg.Node node6 = new WebClientCfg.Node();
        node6.setId("HX1F_S3");
        node6.setTitles("无人机");
        node6.setExeFile("default");
        node6.setRemark("无人机录像");
        clientNodes.add(node6);

        WebClientCfg.Node node4 = new WebClientCfg.Node();
        node4.setId("HX1F_S4");
        node4.setTitles("机器人");
        node4.setExeFile("default");
        node4.setRemark("机器人");
        clientNodes.add(node4);

        WebClientCfg.Node node5 = new WebClientCfg.Node();
        node5.setId("HX1F_S5");
        node5.setTitles("在港船舶实时监控");
        node5.setExeFile("default");
        node5.setRemark("北斗_AIS/GIS");
        clientNodes.add(node5);

        client.setNodes(clientNodes);
        return client;
    }

    public static WebServerCfg initServer(){
        WebServerCfg WebServerCfg = new WebServerCfg();

        WebServerCfg.AppNodes appNodes = new WebServerCfg.AppNodes();
        List<WebServerCfg.Node> serverNodes = new ArrayList();
        WebServerCfg.Node node1 = new WebServerCfg.Node();
        node1.setId("HX1F_S1");
        node1.setType("HIK");
        node1.setWallMode("AR全景");
        node1.setImage("../../Image/AR.png");
        node1.setRemark("AR");
        serverNodes.add(node1);

        WebServerCfg.Node node2 = new WebServerCfg.Node();
        node2.setId("HX1F_S2");
        node2.setType("HIK");
        node2.setWallMode("4K-PC");
        node2.setImage("../../Image/无人集卡.png");
        node2.setRemark("无人集卡");
        serverNodes.add(node2);

        WebServerCfg.Node node3 = new WebServerCfg.Node();
        node3.setId("HX1F_S3");
        node3.setType("HIK");
        node3.setWallMode("4K-PC");
        node3.setImage("../../Image/无人机.png");
        node3.setRemark("无人机");
        serverNodes.add(node3);

        WebServerCfg.Node node4 = new WebServerCfg.Node();
        node4.setId("HX1F_S4");
        node4.setType("HIK");
        node4.setWallMode("4K-PC");
        node4.setImage("../../Image/巡逻机器人.png");
        node4.setRemark("巡逻机器人");
        serverNodes.add(node4);

        WebServerCfg.Node node5 = new WebServerCfg.Node();
        node5.setId("HX1F_S5");
        node5.setType("HIK");
        node5.setWallMode("4K-PC");
        node5.setImage("../../Image/北斗.png");
        node5.setRemark("北斗_AIS/GIS");
        serverNodes.add(node5);

        appNodes.setNodes(serverNodes);
        WebServerCfg.setAppNodes(appNodes);

        WebServerCfg.HikServer hikServer = new WebServerCfg.HikServer();
        hikServer.setIp("ip");
        hikServer.setPort("port");
        WebServerCfg.setHikServer(hikServer);

        WebServerCfg.ServerIP serverIP = new WebServerCfg.ServerIP();
        serverIP.setIp("ip");
        WebServerCfg.setServerIP(serverIP);

        WebServerCfg.Servers servers = new WebServerCfg.Servers();
        List<WebServerCfg.Server> clientSideServers = new ArrayList<>();
        WebServerCfg.Server server1 = new WebServerCfg.Server();
        server1.setName("演示电脑1");
        server1.setService("http://10.28.111.36:8733/ScreenService");
        clientSideServers.add(server1);

        WebServerCfg.Server server2 = new WebServerCfg.Server();
        server2.setName("演示电脑2");
        server2.setService("http://10.28.111.37:8733/ScreenService");
        clientSideServers.add(server2);

        servers.setServers(clientSideServers);
        WebServerCfg.setServers(servers);

        return WebServerCfg;
    }

    public static File initClientFile(){
        return new File("E:\\result\\clientXmlFile.xml");
    }

    public static File initServerFile(){
        return new File("E:\\result\\serverXmlFile.xml");
    }
}
