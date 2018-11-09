package sk.ygor.stackoverflow.q53224496;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class StackOverflowApp {

    public static void main(String[] args) {
        SpringApplication.run(StackOverflowApp.class, args);
    }

    /**
     * We are hard coding the locale, so that validation errors are always presented in English.
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(Locale.ENGLISH);
        return resolver;
    }


}
