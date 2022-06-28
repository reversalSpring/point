package com.james.point.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointId;
    private BigDecimal savePoint;

    private String type;
    private String action;
    private String reviewId;
    private String content;
    private String userId;


    //TODO created, updateAt, deletedYn --> 공통으로 빼기
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Boolean deletedYn;

    @OneToMany(mappedBy = "point", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Photo> photoList;

    @PrePersist
    public void beforePersist() {
        this.createdAt = this.updatedAt = LocalDateTime.now();
        this.deletedYn = Boolean.FALSE;
    }

    @PreUpdate
    public void beforeUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Point deletePoint() {
        return  Point.builder()
                .savePoint(this.savePoint.multiply(new BigDecimal(-1)))
                .type(this.type)
                .action(this.action)
                .reviewId(this.reviewId)
                .content(this.content)
                .userId(this.userId)
                .build();
    }
}
