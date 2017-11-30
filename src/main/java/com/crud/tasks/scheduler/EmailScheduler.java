package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    AdminConfig adminConfig;

    private static final  String SUBJECT = "tasks: Once a day email";
   @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        simpleEmailService.send(new Mail(adminConfig.getAdminMail(),
        SUBJECT, getMessage()));
    }
    private String getMessage() {
        long size = taskRepository.count();
        String message = "Currently in database you have: " + size + " tasks";
        if(size==1) {
            message = message.substring(0, message.length() - 1);
        }
        return message;
    }

}
