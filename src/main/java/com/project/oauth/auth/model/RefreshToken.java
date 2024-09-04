package com.project.oauth.auth.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "oauth_refresh_token", schema = "public")
@EqualsAndHashCode(callSuper = true)
public class RefreshToken extends BaseModel{

    @Id
    @Column(name = "id_refresh_token")
    private Long idRefreshToken;

    private String refreshToken;

    @OneToOne
    @JoinColumn(name = "id_users")
    private Users user;


}
