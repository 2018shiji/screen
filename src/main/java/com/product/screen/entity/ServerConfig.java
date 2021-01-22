package com.product.screen.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Setting")
@XmlType(name = "Setting", propOrder = {"appNodes", "servers", "serverIP", "hikServer"})
@NoArgsConstructor
public class ServerConfig {
    @XmlElement(name = "APPNODES")
    private AppNodes appNodes;
    @XmlElement(name = "Servers")
    private Servers servers;
    @XmlElement(name = "SERVERIP")
    private ServerIP serverIP;
    @XmlElement(name = "HIKSERVER")
    private HikServer hikServer;

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "APPNODES")
    @NoArgsConstructor
    public static class AppNodes{
        @XmlElement(name = "NODE")
        private List<Node> nodes;

        public AppNodes(WebServerCfg.AppNodes webAppNodes){
            nodes = new ArrayList<>();
            List<WebServerCfg.Node> tmpWebNodes = webAppNodes.getNodes();
            for(int i = 0; i < tmpWebNodes.size(); i++){
                nodes.add(new Node(tmpWebNodes.get(i)));
            }
        }
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "Servers")
    @NoArgsConstructor
    public static class Servers{
        @XmlElement(name = "Server")
        private List<Server> servers;

        public Servers(WebServerCfg.Servers webServers){
            servers = new ArrayList<>();
            List<WebServerCfg.Server> tmpServers = webServers.getServers();
            for(int i = 0; i < tmpServers.size(); i++){
                servers.add(new Server(tmpServers.get(i)));
            }
        }
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "SERVERIP")
    @NoArgsConstructor
    public static class ServerIP{
        @XmlAttribute(name = "IP")
        private String ip = "";

        public ServerIP(WebServerCfg.ServerIP webServerIP){
            ip = webServerIP.getIp();
        }
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "HIKSERVER")
    @NoArgsConstructor
    public static class HikServer{
        @XmlAttribute(name = "IP")
        private String ip = "";
        @XmlAttribute(name = "PORT")
        private String port = "";

        public HikServer(WebServerCfg.HikServer webHikServer){
            ip = webHikServer.getIp();
            port = webHikServer.getPort();
        }
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "NODE")
    @NoArgsConstructor
    public static class Node{
        @XmlAttribute(name = "ID")
        private String id = "";
        @XmlAttribute(name = "NAME")
        private String name = "";
        @XmlAttribute(name = "TITLES")
        private String titles;
        @XmlAttribute(name = "EXEFILE")
        private String exeFile;
        @XmlAttribute(name = "TYPE")
        private String type = "";
        @XmlAttribute(name = "WALLMODE")
        private String wallMode = "";
        @XmlAttribute(name = "IMAGE")
        private String image = "";
        @XmlAttribute(name = "remark")
        private String remark = "";

        public Node(WebServerCfg.Node webNode){
            id = webNode.getId();
            type = webNode.getType();
            wallMode = webNode.getWallMode();
            image = webNode.getImage();
            remark = webNode.getRemark();
        }
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "Server")
    @NoArgsConstructor
    public static class Server {
        @XmlAttribute(name = "Name")
        private String name = "";
        @XmlAttribute(name = "Service")
        private String service = "";

        public Server(WebServerCfg.Server webServer){
            name = webServer.getName();
            service = webServer.getService();
        }
    }

    public ServerConfig(WebServerCfg webServerCfg){
        appNodes = new AppNodes(webServerCfg.getAppNodes());
        servers = new Servers(webServerCfg.getServers());
        serverIP = new ServerIP(webServerCfg.getServerIP());
        hikServer = new HikServer(webServerCfg.getHikServer());
    }

}
