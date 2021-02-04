package com.product.screen.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class WebServerCfg {
    private AppNodes appNodes;
    private Servers servers;
    private ServerIP serverIP;
    private HikServer hikServer;

    public WebServerCfg(ServerConfig serverConfig){
        appNodes = new AppNodes(serverConfig.getAppNodes());
        servers = new Servers(serverConfig.getServers());
        serverIP = new ServerIP(serverConfig.getServerIP());
        hikServer = new HikServer(serverConfig.getHikServer());
    }

    @Data
    @NoArgsConstructor
    public static class AppNodes{
        private List<Node> nodes;

        public AppNodes(ServerConfig.AppNodes appNodes){
            nodes = new ArrayList<>();
            List<ServerConfig.Node> cfgNodes = appNodes.getNodes();
            cfgNodes.stream().forEach(item -> nodes.add(new Node(item)));
        }

        public AppNodes(List<Node> nodes){
            this.nodes = nodes;
        }
    }

    @Data
    @NoArgsConstructor
    public static class Servers{
        private List<Server> servers;
        public Servers(ServerConfig.Servers cfgServers){
            servers = new ArrayList();
            List<ServerConfig.Server> tmpServers = cfgServers.getServers();
            tmpServers.stream().forEach(item -> servers.add(new Server(item)));
        }
    }

    @Data
    @NoArgsConstructor
    public static class ServerIP{
        private String ip = "";
        public ServerIP(ServerConfig.ServerIP cfgServerIP){
            ip = cfgServerIP.getIp();
        }
    }

    @Data
    @NoArgsConstructor
    public static class HikServer{
        private String ip = "";
        private String port = "";

        public HikServer(ServerConfig.HikServer cfgHikServer){
            ip = cfgHikServer.getIp();
            port = cfgHikServer.getPort();
        }
    }

    @Data
    @NoArgsConstructor
    public static class Node{
        private boolean selected = false;
        private String id = "";
        private String type = "";
        private String wallMode = "";
        private String image = "";
        private String remark = "";

        public Node(ServerConfig.Node cfgNode){
            id = cfgNode.getId();
            type = cfgNode.getType();
            wallMode = cfgNode.getWallMode();
            image = cfgNode.getImage();
            remark = cfgNode.getRemark();
        }
    }

    @Data
    @NoArgsConstructor
    public static class Server {
        private String name = "";
        private String service = "";

        public Server(ServerConfig.Server cfgServer){
            name = cfgServer.getName();
            service = cfgServer.getService();
        }
    }

}
