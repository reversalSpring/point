package com.james.point.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
public class EventResponse {
    private int earnPoint;

    public EventResponse(int earnPoint) {
        this.earnPoint = earnPoint;
    }
}
