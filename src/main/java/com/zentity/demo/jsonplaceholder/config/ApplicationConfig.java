package com.zentity.demo.jsonplaceholder.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.Filter;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
@EnableSwagger2
public class ApplicationConfig {

    @Value("${jsonplaceholder.baseurl:https://jsonplaceholder.typicode.com/}")
    private String baseUrl;
    @Value("${timeout.connection:30000}")
    private int connectionTimeout = 30000;
    @Value("${timeout.read:10000}")
    private int readTimeout = 10000;
    @Value("${timeout.write:10000}")
    private int writeTimeout = 10000;

    @Bean
    public Filter eTagFilter() {
        return new ShallowEtagHeaderFilter();
    }

    @Bean
    public FilterRegistrationBean shallowEtagHeaderFilterRegistration() {
        FilterRegistrationBean<Filter> result = new FilterRegistrationBean<>();
        result.setFilter(this.eTagFilter());
        result.addUrlPatterns("/*");
        result.setName("eTagFilter");
        return result;
    }

    @Bean
    public WebClient webClient() {
        TcpClient tcpClient = TcpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectionTimeout)
            .doOnConnected(c -> {
                c.addHandler(new ReadTimeoutHandler(readTimeout, TimeUnit.MILLISECONDS));
                c.addHandler(new WriteTimeoutHandler(writeTimeout, TimeUnit.MILLISECONDS));
            });
        return WebClient.builder()
            .baseUrl(baseUrl)
            .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
            .build();
    }

    @Bean
    public Docket swaggerDocConfiguration() {
        return new Docket( DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
            .select()
            .apis( RequestHandlerSelectors.basePackage("com.zentity.demo.jsonplaceholder") )
            .build();
    }

}
