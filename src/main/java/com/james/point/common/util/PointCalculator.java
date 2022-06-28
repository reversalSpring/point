package com.james.point.common.util;

import com.james.point.common.repository.PointRepository;
import com.james.point.enums.Score;

import java.util.List;

public class PointCalculator {
    final static String SPLIT_STRING = ",";

    public static int calculatorPoint(String content, List<String> photoIds) {
        return contentScore(content) + photoScore(photoIds);
    }

    private static int contentScore(String content) {
        return content.length() > Score.CONTENT.getMin() ? Score.CONTENT.getScore() : 0;
    }

    private static int photoScore(List<String> photoIds){
        return photoIds.size() > Score.PHOTO.getMin() ? Score.CONTENT.getScore() : 0;
    }
}
