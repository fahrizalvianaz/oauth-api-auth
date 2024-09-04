package com.project.oauth.auth.model;



import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@Entity
@Table(name = "oauth_clients", schema = "public")
@EqualsAndHashCode(callSuper = true)
public class Clients extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private Long idClient;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "client_secret")
    private String clientSecret;

    @Column(name = "authorized_grant_types")
    private String authorizedGrantTypes;

    private String scope;

    @Column(name = "redirect_uris")
    private String redirectUris;

    @Column(name = "access_token_validity")
    private Integer accessTokenValidity;

    @Column(name = "refresh_token_validity")
    private Integer refreshTokenValidity;

    @ManyToMany
    @JoinTable(
            name = "oauth_client_role",
            joinColumns = @JoinColumn(name = "id_client"),
            inverseJoinColumns = @JoinColumn(name = "id_roles")
    )
    private Set<Clients> clients;

}
