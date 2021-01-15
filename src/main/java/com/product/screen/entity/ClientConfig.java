package com.product.screen.entity;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "APPNODES")
@XmlType(name = "APPNODES", propOrder = {"nodes"})
public class ClientConfig {
    @XmlElement(name = "NODE")
    private List<Node> nodes;

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "NODE")
    public static class Node{
        @XmlAttribute(name = "ID")
        private String id = "";
        @XmlAttribute(name = "NAME")
        private String name = "";
        @XmlAttribute(name = "TITLES")
        private String titles = "";
        @XmlAttribute(name = "EXEFILE")
        private String exeFile = "";
        @XmlAttribute(name = "PARAMS")
        private String params = "";
        @XmlAttribute(name = "REMARK")
        private String remark = "";
    }
}
