package me.ricky.guides.securityguides.service;

import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.ricky.guides.securityguides.model.dto.UserDto;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    @Value("${keycloak.auth-server-url}")
    private String authServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.client-id}")
    private String clientId;

    @Value("${keycloak.client-secret}")
    private String clientSecret;

    private final Keycloak keycloak;

    public UserDto createUser(UserDto userDto) {

        // 유저정보 세팅
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(userDto.getEmail());
        user.setEmail(userDto.getEmail());
        user.setEmailVerified(true);
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        // Get realm
        RealmResource realmResource = keycloak.realm(realm);
        UsersResource usersResource = realmResource.users();

        Response response = usersResource.create(user);
        if (response.getStatus() == 201) {

            String userId = CreatedResponseUtil.getCreatedId(response);

            // create password credential
            CredentialRepresentation passwordCred = new CredentialRepresentation();
            passwordCred.setTemporary(false);
            passwordCred.setType(CredentialRepresentation.PASSWORD);
            passwordCred.setValue(userDto.getPassword());

            log.info("Created userId {}", userId);
            UserResource userResource = usersResource.get(userId);

            // Set password credential
            userResource.resetPassword(passwordCred);

            // role 세팅
            ClientRepresentation clientRep = realmResource.clients().findByClientId(clientId).get(0);
            List<RoleRepresentation> list = realmResource.clients().get(clientRep.getId()).roles().list();
            System.out.println(list);
            RoleRepresentation clientRoleRep =
                    realmResource.clients().get(clientRep.getId()).roles()
                    .get(userDto.getUserRole().getCode())
                    .toRepresentation();

            userResource.roles().clientLevel(clientRep.getId()).add(Collections.singletonList(clientRoleRep));

        }

        userDto.setStatus(response.getStatus());
        userDto.setStatusInfo(response.getStatusInfo().toString());

        return userDto;
    }

    public AccessTokenResponse setAuth(UserDto userDto) {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(authServerUrl)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType(OAuth2Constants.PASSWORD)
                .username(userDto.getEmail())
                .password(userDto.getPassword())
                .build();

        AccessTokenResponse response = keycloak.tokenManager().getAccessToken();
        return response;
    }

    /*
     *  사용자 존재하는지 체크
     * */
    public boolean existsByUsername(String userName) {

        List<UserRepresentation> search = keycloak.realm(realm).users()
                .search(userName);
        if (!search.isEmpty()) {
            log.debug("search : {}", search.get(0).getUsername());
            return true;
        }
        return false;
    }
}