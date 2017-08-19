package com.github.charlesvhe.demo.service;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoProcessUserApproveTaskListener implements TaskListener {
    @Autowired
    private CategoryService categoryService;

    @Override
    public void notify(DelegateTask delegateTask) {
        Integer age = (Integer) delegateTask.getVariable("age");
        if(age < 18){
        throw new RuntimeException("业务逻辑异常 未成年");
        }
        System.out.println("DemoProcessUserApproveTaskListener "+delegateTask.getVariable("assignee"));
    }
}
