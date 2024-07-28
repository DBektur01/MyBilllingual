package javaB13;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class MyBillingual {

    public static void main(String[] args) {
        SpringApplication.run(MyBillingual.class, args);
    }

    @GetMapping
    String introduction() {
        return "introduction";
    }
}