package com.james.point.controller;

import com.james.point.dto.request.EventRequest;
import com.james.point.dto.response.EventResponse;
import com.james.point.dto.response.RemainPointResponse;
import com.james.point.service.PointService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/")
@RestController
@Slf4j
public class PointController {

    final private PointService pointService;

    @PostMapping("events")
    public EventResponse pointEvent(@RequestBody EventRequest eventRequest) throws Exception {
        return pointService.pointEvent(eventRequest);
    }

    @GetMapping("point")
    public RemainPointResponse remainPoint(@PathVariable String userId) {
        return pointService.remainPoint(userId);
    }
}
