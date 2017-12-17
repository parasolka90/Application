package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MailCreatorService {

    public static final String INFO_EMAIL = "INFO_EMAIL";
    public static final String SCHEDULED_EMAIL = "SCHEDULED_EMAIL";

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message, String emailType) throws Exception {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with trello account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/tasks_frontend");
        context.setVariable("trello_url", "https://trello.com/b/7G582kJH//kodilla-application");
        context.setVariable("button_app", "Visit website");
        context.setVariable("button_trello", "Visit Trello");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", "Have a nice day!");
        context.setVariable("company_details", Arrays.asList(companyConfig.getName(),
                companyConfig.getEmail(), companyConfig.getPhone()));
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);

        if (emailType.equals(INFO_EMAIL)) {
            return templateEngine.process("mail/created-trello-card-mail", context);
        } else if (emailType.equals(SCHEDULED_EMAIL)) {
            return templateEngine.process("mail/scheduled-mail", context);
        } else {
            throw new Exception("Incorrect email type");
        }
    }
}
