package com.example;

import com.example.service.TransferService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.example")
@EnableAspectJAutoProxy
@EnableJpaRepositories
@EnableTransactionManagement
@EntityScan(basePackages = "com.example.model")
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

        TransferService upiTransferService = (TransferService) applicationContext.getBean("upiTransferService");
        System.out.println(upiTransferService.getClass());

//         Perform the transfer
        upiTransferService.transfer(100, "1", "2");
        System.out.println();

//        String bean1 = applicationContext.getBean("bean1", String.class);
//        System.out.println(bean1);


        System.out.println();
        //-----------------------
        // shutdown phase
        //-----------------------

        // Perform any necessary cleanup or shutdown operationsxx


    }


}
