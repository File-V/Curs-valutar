package bnm.md/ro/content/ratele-de-schimb;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;



@SpringBootApplication
public class BnmRate {
    public static void main(String[] args) {

        new SpringApplicationBuilder().sources(BnmRate.class).run(args);
    }
}