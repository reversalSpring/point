package com.james.point.common.repository;

import com.james.point.domain.Point;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PointRepository extends JpaRepository<Point, Long> {

    Optional<Point> findTopByReviewIdOrderByCreatedAtDesc(String reviewId);
    Optional<Point> findAllByUserIdAndDeletedYnFalse(String userId);
}
