package com.lyra.activit.controller;

import cn.hutool.core.util.StrUtil;
import com.lyra.activit.common.Result;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

@RestController
@RequestMapping("/deploy")
public class DeployController {
    @Autowired
    private RepositoryService repositoryService;

    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file")MultipartFile file,
                                 @RequestParam("processName") String processName) {
        String originalFilename = file.getOriginalFilename();

        if (StrUtil.isBlank(originalFilename)) {
            return Result.error("文件名称为空");
        }

        if (!originalFilename.endsWith("zip")) {
            return Result.error("文件格式错误");
        }

        ZipInputStream zipInputStream = null;

        ProcessDefinition processDefinition = null;
        try {
            zipInputStream = new ZipInputStream(file.getInputStream());
            Deployment deployment = repositoryService.createDeployment()
                    .addZipInputStream(zipInputStream)
                    .name(processName)
                    .deploy();

            processDefinition = repositoryService.createProcessDefinitionQuery()
                    .deploymentId(deployment.getId()).singleResult();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (zipInputStream != null) {
                try {
                    zipInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return Result.ok(processDefinition.getId());
    }

    @GetMapping("/getDiagram")
    public void getDiagram(@RequestParam("deploymentId") String deploymentId,
                           @RequestParam("resourceName") String resourceName,
                           HttpServletResponse response) {
        InputStream resourceAsStream = repositoryService.getResourceAsStream(deploymentId, resourceName);

        try {
            IOUtils.copy(resourceAsStream, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(resourceAsStream);
        }
    }
}
