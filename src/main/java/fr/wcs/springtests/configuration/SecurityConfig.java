package fr.wcs.springtests.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserDetailsService userDetailsService;

    /***
     * injection de dépendance pour fournir les dépendances nécessaires à SecurityConfig.
     * @param customAuthenticationProvider
     * @param jwtTokenProvider
     * @param userDetailsService
     */
    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /***
     * l'algorithme BCrypt pour hacher les mots de passe.
     * @return objet de type PasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /***
     * Définit une configuration CORS (Cross-Origin Resource Sharing) l'application Angular.
     * Définit l'origine autorisée des requêtes CORS. Dans ce cas, seules les requêtes provenant de "http://localhost:4200" seront acceptées.
     * Définit les méthodes HTTP autorisées pour les requêtes CORS.
     * Définit les en-têtes qui sont autorisés dans les requêtes CORS.
     * Définit les en-têtes qui seront exposés dans la réponse.
     * Les cookies, l'autorisation et les informations d'identification du client sont gérés lors de la manipulation des requêtes CORS.
     * @return CorsConfigurationSource qui a été configuré.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "content-type", "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    /***
     * configure la chaîne de filtres de sécurité (Security Filter Chain)
     * Active la prise en charge de CORS (Cross-Origin Resource Sharing).
     * Désactive la protection contre les attaques CSRF (Cross-Site Request Forgery).
     * Définit les règles d'autorisation pour les requêtes.
     * → Autorise tout le monde à accéder aux URL commençant par "/auth/".
     * → N'autorise que les utilisateurs authentifiés à accéder aux URL commençant par "/api/users/".
     * → Exige que toutes les autres requêtes soient authentifiées.
     * Authentifier les requêtes à l'aide de tokens JWT (JSON Web Token).
     * @param http
     * @return la chaîne de filtres de sécurité configurée.
     * @throws Exception
     */

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/api/users/**").hasAuthority("ADMIN")
                        /*.requestMatchers("/api/users/**").authenticated()*/
                        .anyRequest().authenticated())
                ;
        return http.build();
    }
}