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
public class PlayItem {
    @JSONField(name = "mrl")
    private String mrl;
    @JSONField(name = "description")
    private String description;
    @JSONField(name = "options")
    private String options;
}
