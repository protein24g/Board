package com.study.board.entity;

import com.study.member.entity.Member;
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
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private Integer view;

    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @ManyToOne(fetch = FetchType.LAZY) // 엔티티 정보가 실제로 접근할 때까지 로딩 지연
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Board(String title, String content, Integer view, Member member, LocalDateTime createdDate) {
        this.title = title;
        this.content = content;
        this.view = view;
        this.member = member;
        this.createdDate = createdDate;
    }
}
