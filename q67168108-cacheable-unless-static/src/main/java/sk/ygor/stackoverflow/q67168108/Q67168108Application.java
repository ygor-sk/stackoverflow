package sk.ygor.stackoverflow.q67168108;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
@EnableCaching
public class Q67168108Application implements ApplicationRunner {

    private static final String CURRENCY_CHANNELS_CACHE = "channel1";
    private static final String CURRENCY_CHANNEL_CACHE_KEY_GENERATOR = "generator1";

    @Autowired
    Q67168108Application application;

    @Cacheable(value = CURRENCY_CHANNELS_CACHE,
            keyGenerator = CURRENCY_CHANNEL_CACHE_KEY_GENERATOR,
            unless = "T(com.cache.MyCacheKeyGenerator).isQueryNotCacheable(#p0)")
    public List<Item> getQueryForCollection(final QueryBuilder query, final String sort) {
        System.out.println("Q67168108Application.getQueryForCollection");
        return Collections.emptyList();
    }
    @Bean("generator1")
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

    @Override
    public void run(ApplicationArguments args) {
        System.out.println(application.getQueryForCollection(new QueryBuilder(), "sort1"));;
        System.out.println(application.getQueryForCollection(new QueryBuilder(), "sort1"));;
        System.out.println(application.getQueryForCollection(new QueryBuilder(), "sort1"));;
    }

    public static void main(String[] args) {
        SpringApplication.run(Q67168108Application.class, args);
    }

}
