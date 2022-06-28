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
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long photoId;
    private String photoUuid;

    @ToString.Exclude
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pointId",
            foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT),
            insertable = false, updatable = false
    )
    @JsonBackReference
    private Point point;

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
