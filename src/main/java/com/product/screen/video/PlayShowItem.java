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
public class PlayShowItem {
    @JSONField(name = "id")
    private int id;
    @JSONField(name = "title")
    private String title;

    public PlayShowItem(PlayCfgItem playCfgItem){
        this.title = playCfgItem.getTitle();
    }

    public PlayShowItem setId(int id){
        this.id = id;
        return this;
    }
}
