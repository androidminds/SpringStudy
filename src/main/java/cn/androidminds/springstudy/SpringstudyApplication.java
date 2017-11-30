package cn.androidminds.springstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SpringstudyApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringstudyApplication.class, args);
	}
}
