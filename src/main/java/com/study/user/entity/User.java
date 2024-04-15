package com.study.user.entity;

import com.study.anonyboard.entity.AnonyBoard;
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
@ToString(exclude = {"boards", "anonyBoards"}) // 순환 참조를 피하기 위해 toString에서 제외
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(nullable = false, name = "user_id", unique = true)
    private String userId;

    @Column(nullable = false, name = "nickName", unique = true)
    private String nickName;

    @Column(nullable = false)
    private String userPw;

    @Column(nullable = false, unique = true)
    private String email;

    private String role;

    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY) // AnonyBoard와의 연관관계
    private List<AnonyBoard> anonyBoards = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY) // Board와의 연관관계
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
