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
        private String id = "";
        private String titles = "默认";
        private String exeFile = "默认";
        private String params = "无";
        private String remark = "无";

        public Node(ClientConfig.Node node){
            id = node.getId();
            titles = StringUtils.isBlank(node.getTitles()) ? titles : node.getTitles();
            exeFile = StringUtils.isBlank(node.getExeFile()) ? exeFile : node.getExeFile();
            params = StringUtils.isBlank(node.getParams()) ? params : node.getParams();
            remark = StringUtils.isBlank(node.getRemark()) ? remark : node.getRemark();
        }
    }

    public WebClientCfg(ClientConfig clientCfg){
        nodes = new ArrayList<>();
        List<ClientConfig.Node> cfgNodes = clientCfg.getNodes();
        cfgNodes.stream().forEach(item -> nodes.add(new Node(item)));
    }

}
