package com.microservice.AuthService.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="auth_tokens")
public class AuthToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "access_token", nullable = false, unique = true)
    private String accessToken;

    @Column(name = "refresh_token", nullable = false, unique = true)
    private String refreshToken;

    @Column(name = "issued_date", nullable = false)
    private LocalDateTime issuedDate;

    @Column(name = "used", nullable = false)
    private boolean used = false;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id", nullable = false)
    private User authUser;
}
