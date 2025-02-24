package com.cristhianpc.kata.management.config;


import com.cristhianpc.kata.management.Models.enums.UserRols;
import com.cristhianpc.kata.management.Utils.implment.JwtAuhtenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuhtenticationFilter auhtenticationFilter;

    public SecurityConfig(AuthenticationProvider authenticationProvider, JwtAuhtenticationFilter auhtenticationFilter) {
        this.authenticationProvider = authenticationProvider;
        this.auhtenticationFilter = auhtenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request ->
                        request.requestMatchers("/api/v1/auth/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/api/v1/movies/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/api/v1/reservations/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/api/v1/user/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/api/v1/rooms/**").permitAll()
                                .requestMatchers("/api/v1/admin/**").hasAuthority(UserRols.ADMIN.name())
                                .anyRequest().authenticated()
                )
                .cors(Customizer.withDefaults())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(auhtenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

}
