// package br.insper.pf.security;

// import br.insper.pf.models.UserInfo;
// import br.insper.pf.services.AuthService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.authentication.ProviderManager;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// public class WebSecurityConfig implements WebMvcConfigurer {

//     @Autowired
//     private AuthService authService;

//     @Override
//     public void addCorsMappings(CorsRegistry registry) {
//         registry.addMapping("/**").allowedMethods("*");
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http
//                 .csrf(csrf -> csrf.disable())
//                 .authorizeHttpRequests(authorize -> authorize
//                         .requestMatchers(HttpMethod.POST, "/tarefa").hasRole("ADMIN")
//                         .requestMatchers(HttpMethod.DELETE, "/tarefa").hasRole("ADMIN")
//                         .requestMatchers(HttpMethod.GET, "/tarefa").hasAnyRole("ADMIN", "DEVELOPER")
//                         .requestMatchers(HttpMethod.GET, "/tarefa/{id}").hasAnyRole("ADMIN", "DEVELOPER")
//                         .anyRequest().authenticated()
//                 )
//                 .oauth2ResourceServer(oauth2 -> oauth2.authenticationManager(
//                         new ProviderManager(new CustomAuthenticationProvider())
//                 ));

//         return http.build();
//     }

//     private class CustomAuthenticationProvider implements AuthenticationProvider {
//         @Override
//         public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//             if (authentication instanceof BearerTokenAuthenticationToken) {
//                 String token = ((BearerTokenAuthenticationToken) authentication).getToken();
//                 UserInfo userInfo = authService.validateToken(null, token);
//                 return new BearerTokenAuthenticationToken(token, userInfo.getAuthorities());
//             }
//             throw new IllegalArgumentException("Invalid authentication type");
//         }

//         @Override
//         public boolean supports(Class<?> authentication) {
//             return BearerTokenAuthenticationToken.class.isAssignableFrom(authentication);
//         }
//     }
// }