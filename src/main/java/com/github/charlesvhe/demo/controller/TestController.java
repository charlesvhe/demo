package com.github.charlesvhe.demo.controller;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @GetMapping()
    public String test() {
        this.logger.info("/test GET");
        return "Hello " + System.currentTimeMillis();
    }

    @GetMapping("/start")
    public String start() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("candidateGroups", "groupManager");
        variables.put("assigneeList", Arrays.asList("a1","a2"));

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(
                "demo_process", "demo_process_1001", variables);

        this.logger.info("start");
        return processInstance.toString();
    }

    @GetMapping("/approve")
    public String approve(String user, Integer age) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("age", age);

        Task task = taskService.createTaskQuery()
                .processDefinitionKey("demo_process")
                .taskCandidateUser(user)
                .singleResult();

        taskService.complete(task.getId(), variables);

        this.logger.info("approve");
        return task.toString();
    }
}
