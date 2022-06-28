package com.james.point.dto.response;

import lombok.Getter;

@Getter
public class RemainPointResponse {
    private int remainPoint;

    public RemainPointResponse(int earnPoint) {
        this.remainPoint = remainPoint;
    }
}
