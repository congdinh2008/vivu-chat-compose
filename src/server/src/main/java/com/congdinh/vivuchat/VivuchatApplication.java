package com.congdinh.vivuchat;

import com.congdinh.vivuchat.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class VivuchatApplication {

    public static void main(String[] args) {
        // Traditional Spring Context loading approach as per SRS guidelines
        // Load Spring context manually using AnnotationConfigApplicationContext
        try {
            log.info("================================================");
            log.info("Loading ViVuChat application with manual Spring Context...");
            log.info("================================================");
            
            // Create Spring Context using Java-based configuration
            ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
            
            log.info("Spring Application Context loaded successfully!");
            log.info("Available beans: {}", context.getBeanDefinitionCount());
            log.info("DataSource bean: {}", context.containsBean("dataSource"));
            log.info("SessionFactory bean: {}", context.containsBean("sessionFactory"));
            log.info("TransactionManager bean: {}", context.containsBean("transactionManager"));
            
            log.info("================================================");
            log.info("ViVuChat application started successfully!");
            log.info("================================================");
            log.info("Spring Context loaded with Java-based configuration");
            log.info("Manual context loading completed as per SRS guidelines");
            
        } catch (Exception e) {
            log.error("Failed to start ViVuChat application with manual Spring Context", e);
            System.exit(1);
        }
        
        // Additionally, start Spring Boot application for web services
        // This allows us to maintain both traditional Spring Core setup and Spring Boot web features
        SpringApplication.run(VivuchatApplication.class, args);
    }
    
    @Bean
    CommandLineRunner logStartup() {
        return args -> {
            log.info("=================================================");
            log.info("ViVuChat Spring Boot web layer started!");
            log.info("=================================================");
            log.info("API available at: http://localhost:8080/api");
            log.info("Basic API docs at: http://localhost:8080/api-docs");
        };
    }
}
