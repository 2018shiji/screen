package com.product.screen.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GlobalConfig {
    @JSONField(name = "clientCfgUrl")
    private String clientCfgUrl;
    @JSONField(name = "serverCfgUrl")
    private String serverCfgUrl;
    @JSONField(name = "clientCtrlIp")
    private String clientCtrlIp;
    @JSONField(name = "videoListUrl")
    private String videoListUrl;
}
