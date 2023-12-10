package me.ricky.guides.securityguides.config;

import me.ricky.guides.securityguides.filter.AuthoritiesLoggingAfterFilter;
import me.ricky.guides.securityguides.filter.AuthoritiesLoggingAtFilter;
import me.ricky.guides.securityguides.filter.CsrfCookieFilter;
import me.ricky.guides.securityguides.filter.RequestValidationBeforeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // cors
        http.cors(httpSecurityCorsConfigurer -> {
            httpSecurityCorsConfigurer.configurationSource(httpServletRequest -> {
                CorsConfiguration corsConfiguration = new CorsConfiguration();
                corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:33000"));
                corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
                corsConfiguration.setAllowCredentials(true);
                corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
                corsConfiguration.setMaxAge(3600L);
                return corsConfiguration;
            });
        });

        // csrf
        http.securityContext(securityContextConfigurer ->
                securityContextConfigurer.requireExplicitSave(false));
        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));

        CsrfTokenRequestAttributeHandler csrfHandler = new CsrfTokenRequestAttributeHandler();
        http.csrf(csrfConfigurer -> {
            csrfConfigurer.csrfTokenRequestHandler(csrfHandler);
            csrfConfigurer.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
            csrfConfigurer.ignoringRequestMatchers("/contact", "/register");
        });
        http.addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class);
        http.addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class);
        http.addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class);
        http.addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class);

        // authorize
        http.authorizeHttpRequests((requests) -> requests
//                .requestMatchers("myAccount").hasAuthority("VIEWACCOUNT")
//                .requestMatchers("myBalance").hasAnyAuthority("VIEWACCOUNT","VIEWBALANCE")
//                .requestMatchers("myLoans").hasAuthority("VIEWLOANS")
//                .requestMatchers("myCard").hasAuthority("VIEWCARDS")

                .requestMatchers("myAccount").hasRole("USER")
                .requestMatchers("myBalance").hasAnyRole("USER","ADMIN")
                .requestMatchers("myLoans").hasRole("USER")
                .requestMatchers("myCard").hasRole("USER")


                .requestMatchers("user").authenticated()
                .requestMatchers("contact", "notices", "register", "error").permitAll()
        );
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("password"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("password"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//    }
}
