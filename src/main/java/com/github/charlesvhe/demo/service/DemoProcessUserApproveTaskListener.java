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
        System.out.println("DemoProcessUserApproveTaskListener ");
        throw new RuntimeException("业务逻辑异常");
    }
}
