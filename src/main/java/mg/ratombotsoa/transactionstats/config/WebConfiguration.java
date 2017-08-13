package mg.ratombotsoa.transactionstats.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {

	@Bean
    public ServletRegistrationBean<?> registerH2Servlet(){
        return new ServletRegistrationBean<>(new WebServlet(), "/h2/*");
    }
}
