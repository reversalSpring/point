package com.james.point;

import com.james.point.common.repository.PointRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PointApplicationTests {

    @Autowired
    PointRepository pointRepository;

    @BeforeEach
    public void setup() {
    }

    @Test
    public void selectPoint(){
        pointRepository.findAll().forEach(point ->
                System.out.println(point.toString())
        );
    }
}
