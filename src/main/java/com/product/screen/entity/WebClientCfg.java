package com.product.screen.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
//"Java中StringUtils工具类进行String为空的判断解析 - 码农教程 - Google Chrome"
@Data
@NoArgsConstructor
public class WebClientCfg {
    private List<Node> nodes;

    @Data
    @NoArgsConstructor
    public static class Node{
        private boolean selected = false;
        private String id = "";
        private String titles = "默认";
        private String exeFile = "默认";
        private String params = "无";
        private String remark = "无";
        private String windowTitle = "默认";
        private String appType = "默认";
        private String url = "默认";

        public Node(ClientConfig.Node node){
            id = node.getId();
            titles = StringUtils.isBlank(node.getTitles()) ? titles : node.getTitles();
            exeFile = StringUtils.isBlank(node.getExeFile()) ? exeFile : node.getExeFile();
            params = StringUtils.isBlank(node.getParams()) ? params : node.getParams();
            remark = StringUtils.isBlank(node.getRemark()) ? remark : node.getRemark();
        }

        public Node(ClientConfigPool.Node poolNode){
            id = poolNode.getId();
            titles = StringUtils.isBlank(poolNode.getTitles()) ? titles : poolNode.getTitles();
            exeFile = StringUtils.isBlank(poolNode.getExeFile()) ? exeFile : poolNode.getExeFile();
            params = StringUtils.isBlank(poolNode.getParams()) ? params : poolNode.getParams();
            remark = StringUtils.isBlank(poolNode.getRemark()) ? remark : poolNode.getRemark();
            windowTitle = StringUtils.isBlank(poolNode.getWindowTitle()) ? windowTitle : poolNode.getWindowTitle();
            appType = StringUtils.isBlank(poolNode.getAppType()) ? appType : poolNode.getAppType();
            url = StringUtils.isBlank(poolNode.getUrl()) ? url : poolNode.getUrl();
        }
    }

    public WebClientCfg(ClientConfig clientCfg){
        nodes = new ArrayList<>();
        List<ClientConfig.Node> cfgNodes = clientCfg.getNodes();
        cfgNodes.stream().forEach(item -> nodes.add(new Node(item)));
    }

    public WebClientCfg(ClientConfigPool clientCfgPool){
        nodes = new ArrayList<>();
        List<ClientConfigPool.Node> cfgPoolNodes = clientCfgPool.getNodes();
        cfgPoolNodes.stream().forEach(item -> nodes.add(new Node(item)));
    }

}
