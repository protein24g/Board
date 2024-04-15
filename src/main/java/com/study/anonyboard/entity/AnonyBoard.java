package com.study.anonyboard.entity;

import com.study.user.entity.User;
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
public class AnonyBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private Integer view;

    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY) // 엔티티 정보가 실제로 접근할 때까지 로딩 지연
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public AnonyBoard(String title, String content, Integer view, User user, LocalDateTime createdDate) {
        this.title = title;
        this.content = content;
        this.view = view;
        this.user = user;
        this.createdDate = createdDate;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
