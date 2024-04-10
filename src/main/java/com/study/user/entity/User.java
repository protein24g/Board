package com.study.user.entity;

import com.study.board.entity.Board;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@ToString(exclude = "boards") // 순환 참조를 피하기 위해 boards는 toString에서 제외
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, name = "nickName")
    private String nickName;

    @Column(nullable = false)
    private String userPw;

    @Column(nullable = false)
    private String email;

    private String role;

    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY) // Board 엔티티의 member 필드에 의해 매핑됨
    private List<Board> boards = new ArrayList<>();

    @Builder
    public User(String userId, String nickName, String userPw, String email, String role, LocalDateTime createdDate){
        this.userId = userId;
        this.nickName = nickName;
        this.userPw = userPw;
        this.email = email;
        this.role = role;
        this.createdDate = createdDate;
    }
}
