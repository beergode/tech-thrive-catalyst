package com.beergode.decisionmaker;

import com.beergode.decisionmaker.common.DomainComponent;
import javax.swing.text.html.parser.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
		includeFilters = {
				@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {DomainComponent.class})
		}
)
public class DecisionMakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DecisionMakerApplication.class, args);
	}

}
