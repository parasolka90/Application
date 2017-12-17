package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.crud.tasks.service.MailCreatorService.SCHEDULED_EMAIL;

@Component
public class EmailScheduler {

    private static final String SUBJECT = "Tasks: once a day email";

    @Autowired
    private SimpleEmailService emailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    @Scheduled(cron = "0 0 8 * * *")
//@Scheduled(fixedDelay = 30000)
    public void sendInformationEmail() {
        emailService.send(new Mail(
                        adminConfig.getAdminMail(),
                        SUBJECT,
                        getMessage()),
                SCHEDULED_EMAIL);
    }

    private String getMessage() {
        long size = taskRepository.count();
        if (size == 1) {
            return "Currently in database you have: 1 task";
        }
        return "Currently in database you have: " + size + " tasks";
    }
}

