package com.study.member.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Integer id;

    @Column(nullable = false)
    private String userid;

    @Column(nullable = false)
    private String userpw;

    @Column(nullable = false)
    private String email;

    private String role;

    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @Builder
    public Member(String userid, String userpw, String email, String role, LocalDateTime createdDate){
        this.userid = userid;
        this.userpw = userpw;
        this.email = email;
        this.role = role;
        this.createdDate = createdDate;
    }
}
