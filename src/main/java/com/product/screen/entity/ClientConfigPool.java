package com.product.screen.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "APPNODES")
@XmlType(name = "APPNODES", propOrder = {"nodes"})
public class ClientConfigPool {
    @XmlElement(name = "NODE")
    private List<Node> nodes;

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "NODE")
    @NoArgsConstructor
    public static class Node {
        @XmlAttribute(name = "ID")
        private String id = "";
        @XmlAttribute(name = "NAME")
        private String name = "";
        @XmlAttribute(name = "TITLES")
        private String titles = "";
        @XmlAttribute(name = "EXEFILE")
        private String exeFile = "";
        @XmlAttribute(name = "TYPE")
        private String type = "";
        @XmlAttribute(name = "PARAMS")
        private String params = "";
        @XmlAttribute(name = "WALLMODE")
        private String wallMode = "";
        @XmlAttribute(name = "IMAGE")
        private String image = "";
        @XmlAttribute(name = "remark")
        private String remark = "";
        @XmlAttribute(name = "WINDOWTITLE")
        private String windowTitle = "";
        @XmlAttribute(name = "APPTYPE")
        private String appType = "";
        @XmlAttribute(name = "URL")
        private String url = "";

        Node(WebClientCfg.Node webNode) {
            id = webNode.getId();
            titles = ("默认".equals(webNode.getTitles())) ? "" : webNode.getTitles();
            exeFile = ("默认".equals(webNode.getExeFile())) ? "" : webNode.getExeFile();
            params = ("无".equals(webNode.getParams())) ? "" : webNode.getParams();
            remark = ("无".equals(webNode.getRemark())) ? "" : webNode.getRemark();
            windowTitle = ("默认".equals(webNode.getWindowTitle())) ? "" : webNode.getWindowTitle();
            appType = ("默认".equals(webNode.getAppType())) ? "" : webNode.getAppType();
            url = ("默认".equals(webNode.getUrl())) ? "" : webNode.getUrl();
        }
    }

    public ClientConfigPool(WebClientCfg webClientCfg){
        nodes = new ArrayList<>();
        for(int i = 0; i < webClientCfg.getNodes().size(); i++) {
            WebClientCfg.Node webNode = webClientCfg.getNodes().get(i);
            Node node = new Node(webNode);
            nodes.add(node);
        }
    }
}
