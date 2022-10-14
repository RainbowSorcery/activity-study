package com.lyra.activit.controller;

import com.lyra.activit.common.Result;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProcessInstanceController {
    @Autowired
    private ProcessRuntime processRuntime;

    @Autowired
    private RuntimeService runtimeService;

    @GetMapping("/start")
    public Result<Object> start(@RequestParam String processDefinitionId) {
        Map<String, Object> variables = new HashMap<>();

        return null;
    }
}
