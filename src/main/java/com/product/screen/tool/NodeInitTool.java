package com.product.screen.tool;

import com.product.screen.entity.ClientConfig;
import com.product.screen.entity.ServerConfig;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class NodeInitTool {

    public static ClientConfig initClient(){
        ClientConfig client = new ClientConfig();
        List<ClientConfig.Node> clientNodes = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            ClientConfig.Node node = new ClientConfig.Node();
            node.setExeFile("exeFile" + i);
            node.setId("id" + i);
            node.setName("name" + i);
            node.setRemark("remark" + i);
            node.setTitles("titles" + i);
            node.setParams("params" + i);
            clientNodes.add(node);
        }
        client.setNodes(clientNodes);
        return client;
    }

    public static ServerConfig initServer(){
        ServerConfig serverConfig = new ServerConfig();

        ServerConfig.AppNodes appNodes = new ServerConfig.AppNodes();
        List<ServerConfig.Node> serverNodes = new ArrayList();
        for(int i = 0; i < 3; i++){
            ServerConfig.Node node = new ServerConfig.Node();
            node.setId("id" + i);
            node.setImage("image" + i);
            node.setName("name" + i);
            node.setRemark("remark" + i);
            node.setType("type" + i);
            node.setWallMode("wallMode" + i);
            serverNodes.add(node);
        }
        appNodes.setNodes(serverNodes);
        serverConfig.setAppNodes(appNodes);

        ServerConfig.HikServer hikServer = new ServerConfig.HikServer();
        hikServer.setIp("ip");
        hikServer.setPort("port");
        serverConfig.setHikServer(hikServer);

        ServerConfig.ServerIP serverIP = new ServerConfig.ServerIP();
        serverIP.setIp("ip");
        serverConfig.setServerIP(serverIP);

        ServerConfig.Servers servers = new ServerConfig.Servers();
        List<ServerConfig.Server> servers1 = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            ServerConfig.Server server = new ServerConfig.Server();
            server.setName("name" + i);
            server.setService("service" + i);
            servers1.add(server);
        }
        servers.setServers(servers1);
        serverConfig.setServers(servers);

        return serverConfig;
    }

    public static File initClientFile(){
        return new File("E:\\result\\clientXmlFile.xml");
    }

    public static File initServerFile(){
        return new File("E:\\result\\serverXmlFile.xml");
    }
}
