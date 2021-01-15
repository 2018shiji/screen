package com.product.screen.entity;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Setting")
@XmlType(name = "Setting", propOrder = {"appNodes", "servers", "serverIP", "hikServer"})
public class ServerConfig {
    @XmlElement(name = "APPNODES")
    private AppNodes appNodes;
    @XmlElement(name = "SERVERS")
    private Servers servers;
    @XmlElement(name = "SERVERIP")
    private ServerIP serverIP;
    @XmlElement(name = "HIKSERVER")
    private HikServer hikServer;

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "APPNODES")
    public static class AppNodes{
        @XmlElement(name = "NODE")
        private List<Node> nodes;
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "SERVERS")
    public static class Servers{
        @XmlElement(name = "SERVERS")
        private List<Server> servers;
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "SERVERIP")
    public static class ServerIP{
        @XmlAttribute(name = "IP")
        private String ip = "";
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "HIKSERVER")
    public static class HikServer{
        @XmlAttribute(name = "IP")
        private String ip = "";
        @XmlAttribute(name = "PORT")
        private String port = "";
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "NODE")
    public static class Node{
        @XmlAttribute(name = "ID")
        private String id = "";
        @XmlAttribute(name = "NAME")
        private String name = "";
        @XmlAttribute(name = "TYPE")
        private String type = "";
        @XmlAttribute(name = "WALLMODE")
        private String wallMode = "";
        @XmlAttribute(name = "IMAGE")
        private String image = "";
        @XmlAttribute(name = "REMARK")
        private String remark = "";
    }

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "SERVER")
    public static class Server {
        @XmlAttribute(name = "NAME")
        private String name = "";
        @XmlAttribute(name = "SERVICE")
        private String service = "";
    }

}
