package gov.nist.hit.hl7.auth;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import gov.nist.hit.hl7.auth.repository.PrivilegeRepository;
import gov.nist.hit.hl7.auth.service.AccountService;


@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, MongoAutoConfiguration.class,
    DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableMongoRepositories("gov.nist.hit.hl7")
@ComponentScan({"gov.nist.hit.hl7"})
public class App implements CommandLineRunner {

  @Autowired
  PrivilegeRepository priviliges;

  @Autowired
  AccountService accountService;



  public static void main(String[] args) {
    SpringApplication.run(App.class, args);

  }



  @Bean
  public JavaMailSenderImpl mailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost("smtp.nist.gov");
    mailSender.setPort(25);
    mailSender.setProtocol("smtp");
    Properties javaMailProperties = new Properties();
    javaMailProperties.setProperty("mail.smtp.auth", "false");
    javaMailProperties.setProperty("mail.debug", "true");

    mailSender.setJavaMailProperties(javaMailProperties);
    return mailSender;
  }

  @Bean
  public org.springframework.mail.SimpleMailMessage templateMessage() {
    org.springframework.mail.SimpleMailMessage templateMessage =
        new org.springframework.mail.SimpleMailMessage();
    templateMessage.setFrom("hl7-auth@nist.gov");
    templateMessage.setSubject("NIST HL7 Auth Notification");
    return templateMessage;
  }

  @Override
  public void run(String... arg0) throws Exception {

  }

  @PostConstruct
  void converAccounts() {
    // try {
    // Privilege user = new Privilege("USER");
    // Privilege admin = new Privilege("ADMIN");
    //
    // priviliges.save(user);
    // priviliges.save(admin);
    // accountService.createAccountsFromLegacy();
    // } catch (IOException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }

  }
}
