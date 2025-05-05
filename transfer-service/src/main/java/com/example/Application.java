package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.example")
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class Application {
    public static void main(String[] args) {


        //-----------------------
        // init / boot  phase
        //-----------------------

        ConfigurableApplicationContext applicationContext = null;
        applicationContext = SpringApplication.run(Application.class, args);


        System.out.println();
        //-----------------------
        // run phase
        //-----------------------

        //TransferService upiTransferService = (TransferService) applicationContext.getBean("upiTransferService");
        //System.out.println(upiTransferService.getClass());

        // Perform the transfer
        //upiTransferService.transfer(100, "1", "2");
        //System.out.println();

        String bean1 = applicationContext.getBean("bean1", String.class);
        System.out.println(bean1);


        System.out.println();
        //-----------------------
        // shutdown phase
        //-----------------------

        // Perform any necessary cleanup or shutdown operationsxx


    }


}
