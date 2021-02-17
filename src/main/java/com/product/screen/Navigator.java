package com.product.screen;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.io.Files;
import com.product.screen.entity.GlobalConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

@Controller
public class Navigator {
    private String globalCfgFile = "D:\\globalCfg.txt";

    @ResponseBody
    @RequestMapping("getGlobalConfig")
    public GlobalConfig getGlobalConfig() throws IOException {
//        InputStream globalCfgIS = new ClassPathResource("globalCfg").getInputStream();
//        String read = CharStreams.toString(new InputStreamReader(globalCfgIS, Charset.forName("UTF-8")));
        String read = Files.asCharSource(new File(globalCfgFile), Charset.forName("UTF-8")).read();
        GlobalConfig globalConfig = JSONObject.parseObject(read, GlobalConfig.class);
        return globalConfig;
    }

    @ResponseBody
    @RequestMapping("setGlobalConfig")
    public void setGlobalConfig(@RequestBody GlobalConfig globalCfg){
        System.out.println(globalCfg);
        if(globalCfg == null || StringUtils.isAnyBlank(globalCfg.getClientCfgUrl(), globalCfg.getServerCfgUrl(), globalCfg.getClientCtrlIp()))
            return;

        File file = new File(globalCfgFile);

        setGlobalCfgFile(file, globalCfg);
    }

    private void setGlobalCfgFile(File file, GlobalConfig globalCfg){
        if(!file.exists()){
            try{
                file.createNewFile();
            } catch (Exception e){ e.printStackTrace();}
        }
        try {
            serverCfgDir = globalCfg.getServerCfgUrl();
            clientCfgDir = globalCfg.getClientCfgUrl();
            FileWriter fileWriter = new FileWriter(file);
            String cfgStr = JSON.toJSONString(globalCfg);
            fileWriter.write(cfgStr);
            fileWriter.close();
        } catch (Exception e){e.printStackTrace();}
    }

}
