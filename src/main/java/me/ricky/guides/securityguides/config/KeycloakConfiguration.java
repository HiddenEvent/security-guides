package me.ricky.guides.securityguides.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfiguration {
  @Value("${keycloak.auth-server-url}")
  private String authServerUrl;

  @Value("${keycloak.realm}")
  private String realm;

  @Value("${keycloak.client-id}")
  private String clientId;

  @Value("${keycloak.client-secret}")
  private String clientSecret;

  /*
   *  Keycloak 서버와 통신하기 위한 클라이언트 빌더
   * */
  @Bean
  public Keycloak keycloak() {
    return KeycloakBuilder.builder()
            .serverUrl(authServerUrl)
            .realm(realm)
            .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
            .clientId(clientId)
            .clientSecret(clientSecret)
            .build();
  }
}