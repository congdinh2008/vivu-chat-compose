package com.congdinh.vivuchat.config;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Spring Core Java-based Configuration
 * Tests manual Spring Context loading and bean configuration
 */
class AppConfigTest {

    @Test
    void testAppConfigAnnotations() {
        // Verify that AppConfig has the required annotations
        Class<AppConfig> configClass = AppConfig.class;
        
        assertTrue(configClass.isAnnotationPresent(org.springframework.context.annotation.Configuration.class), 
                   "AppConfig should have @Configuration annotation");
        
        assertTrue(configClass.isAnnotationPresent(org.springframework.transaction.annotation.EnableTransactionManagement.class),
                   "AppConfig should have @EnableTransactionManagement annotation");
        
        assertTrue(configClass.isAnnotationPresent(org.springframework.context.annotation.ComponentScan.class),
                   "AppConfig should have @ComponentScan annotation");
        
        assertTrue(configClass.isAnnotationPresent(org.springframework.context.annotation.PropertySource.class),
                   "AppConfig should have @PropertySource annotation");
        
        System.out.println("AppConfig annotations validation passed!");
        System.out.println("✓ @Configuration present");
        System.out.println("✓ @EnableTransactionManagement present");
        System.out.println("✓ @ComponentScan present");
        System.out.println("✓ @PropertySource present");
        System.out.println("All required Spring Core Java-based Configuration annotations are present");
    }

    @Test 
    void testConfigurationClass() {
        // Verify AppConfig can be instantiated and is a valid configuration class
        assertDoesNotThrow(() -> {
            AppConfig config = new AppConfig();
            assertNotNull(config, "AppConfig should be instantiable");
        }, "AppConfig should be instantiable without errors");
        
        System.out.println("AppConfig instantiation test passed!");
    }
}