package com.james.point.common.repository;

import com.james.point.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Place, Long> {
}
