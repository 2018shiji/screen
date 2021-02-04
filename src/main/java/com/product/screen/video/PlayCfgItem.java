package com.product.screen.video;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PlayCfgItem {
    @JSONField(name = "selected")
    private boolean selected = false;
    @JSONField(name = "mrl")
    private String mrl;
    @JSONField(name = "title")
    private String title;
    @JSONField(name = "options")
    private String options;
    @JSONField(name = "volume")
    private int volume;
}
