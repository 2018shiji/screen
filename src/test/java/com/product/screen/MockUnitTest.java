package com.product.screen;

import com.product.screen.entity.ClientConfig;
import com.product.screen.entity.ServerConfig;
import com.product.screen.tool.XmlUtil;
import com.product.screen.tool.NodeInitTool;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.PostConstruct;

import static org.mockito.BDDMockito.*;

@SpringBootTest
public class MockUnitTest {
    @Autowired
    private ScreenNavigator screenNavigator;
    @MockBean
    private XmlUtil xmlUtil;

    @PostConstruct
    public void initMockUnit(){
        initMockImpl();
    }

    private void initMockImpl(){
        when(xmlUtil.convertToXmlFile(
                argThat((ArgumentMatcher<ClientConfig>) clientConfig -> clientConfig instanceof ClientConfig),
                eq("utf-8"), anyString()))
                .thenReturn(NodeInitTool.initClientFile());
        when(xmlUtil.convertToXmlFile(
                argThat((ArgumentMatcher<ServerConfig>) serverConfig -> serverConfig instanceof ServerConfig),
                eq("utf-8"), anyString()))
                .thenReturn(NodeInitTool.initServerFile());
//        when(xmlUtil.convertFileToObject(any(), ClientConfig.class)).thenReturn(NodeInitTool.initClient());
        when(xmlUtil.convertFileToObject(any(), any())).thenReturn(NodeInitTool.initServer());
    }

    @Test
    void testSetClientConfig(){
        screenNavigator.setClientConfig(NodeInitTool.initClient());
    }

    @Test
    void testSetServerConfig(){
        screenNavigator.setServerConfig(NodeInitTool.initServer());
    }

    @Test
    void testGetClientConfig(){
        System.out.println(screenNavigator.getClientConfig());
    }

    @Test
    void testGetServerConfig(){
        System.out.println(screenNavigator.getServerConfig());
    }
}
