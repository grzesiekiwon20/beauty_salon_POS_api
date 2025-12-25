//package com.beautysalon.config;
//
//
//import org.keycloak.admin.client.Keycloak;
//import org.keycloak.admin.client.KeycloakBuilder;
//import org.keycloak.admin.client.resource.RealmResource;
//import org.keycloak.representations.idm.RealmRepresentation;
//import org.keycloak.representations.idm.UserRepresentation;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//
//@Service
//public class KeycloakAdminService {
//
//
//    private final String realm;
//    private final String serverUrl;
//    private final String clientId;
//    private final String clientSecret;
//
//    public KeycloakAdminService(@Value("${spring.keycloak.server-url}") String serverUrl,
//                                @Value("${spring.keycloak.realm}") String realm,
//                                @Value("${spring.keycloak.client-id}") String clientId,
//                                @Value("${spring.keycloak.client-secret}") String clientSecret) {
//        this.serverUrl = serverUrl;
//        this.realm = realm;
//        this.clientId = clientId;
//        this.clientSecret = clientSecret;
//    }
//
//    private Keycloak keycloak() {
//        return KeycloakBuilder.builder()
//                .serverUrl(serverUrl)
//                .realm(realm)
//                .clientId(clientId)
//                .clientSecret(clientSecret)
//                .grantType("client_credentials")
//                .build();
//    }
//
//
//
//    public UserRepresentation getUserById(String userId) {
//        try(Keycloak keycloak = keycloak()){
//            RealmResource realmResource = keycloak.realm(realm);
//            return realmResource.users().get(userId).toRepresentation();
//        }catch (Exception e) {
//            throw new RuntimeException("Error fetching user from Keycloak", e);
//        }
//    }
//
//    public List<UserRepresentation> getUsers() {
//        try (Keycloak keycloak = keycloak()) {
//            RealmResource realmResource = keycloak.realm(realm);
//            return realmResource.users().list();
//        } catch (Exception e) {
//            throw new RuntimeException("Error fetching users from Keycloak", e);
//        }
//    }
//
//
//    public void close() {
//        this.keycloak().close();
//    }
//
//}
