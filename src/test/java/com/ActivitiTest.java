package com;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ActivitiTest {


    @Test
    public void test1() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取流程定义与部署相关Service
        Deployment deployment = processEngine.getRepositoryService()
                // 创建一个部署对象
                .createDeployment()
                .name("demand")
                // 加载资源文件
                .addClasspathResource("activiti/demand.bpmn20.xml")
//                .addClasspathResource("processes/rejectProcess.png")
                // 完成部署demand
                .deploy();
        log.info(deployment.getId());
        log.info(deployment.getName());

    }

    @Test
    public void test2() {
        // 显式创建 ProcessEngineConfiguration
        ProcessEngineConfiguration config = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration();

        // 设置任何必要的配置选项
        // config.setJdbcUrl("...");
        // config.setJdbcDriver("...");
        // config.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        // 创建 ProcessEngine
        ProcessEngine processEngine = config.buildProcessEngine();

        // 使用创建的 ProcessEngine 进行部署
        Deployment deployment = processEngine.getRepositoryService()
                .createDeployment()
                .name("demand")
                .addClasspathResource("activiti/demand.bpmn20.xml")
                .deploy();

        log.info(deployment.getId());
        log.info(deployment.getName());

        // 测试完成后关闭 ProcessEngine
        processEngine.close();
    }

    @Test
    public void test3() {
        System.out.println("----错误提交-----");
    }
}
