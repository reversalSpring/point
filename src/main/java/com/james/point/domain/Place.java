package com.james.point.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long placeId;

    private String userId;
    private String placeUuid;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Boolean deletedYn;

    @PrePersist
    public void beforePersist() {
        this.createdAt = this.updatedAt = LocalDateTime.now();
        this.deletedYn = Boolean.FALSE;
    }

    @PreUpdate
    public void beforeUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
