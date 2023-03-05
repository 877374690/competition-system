package com.competition.web.controller.monitor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.competition.common.core.domain.AjaxResult;
import com.competition.framework.web.domain.Server;

/**
 * 服务器监控
 * 
 * @author competition
 */
@RestController
@RequestMapping("/monitor/server")
public class ServerController
{
    @PreAuthorize("@ss.hasPermi('monitor:server:list')")
    @GetMapping()
    public AjaxResult getInfo() throws Exception
    {
        //构建一个服务器对象
        Server server = new Server();
        server.copyTo();
        return AjaxResult.success(server);
    }
}
