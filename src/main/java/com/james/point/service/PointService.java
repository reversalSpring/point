package com.james.point.service;

import com.james.point.common.repository.PlaceRepository;
import com.james.point.common.util.PointCalculator;
import com.james.point.domain.Photo;
import com.james.point.domain.Place;
import com.james.point.domain.Point;
import com.james.point.common.repository.PointRepository;
import com.james.point.dto.request.EventRequest;
import com.james.point.dto.response.EventResponse;
import com.james.point.dto.response.RemainPointResponse;
import com.james.point.enums.Action;
import com.james.point.enums.EarnPolicy;
import com.james.point.enums.Score;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PointService {

    private final PointRepository pointRepository;
    private final PlaceRepository placeRepository;

    public EventResponse pointEvent(EventRequest eventRequest) throws Exception {
        if(Action.ADD.equals(Action.valueOf(eventRequest.getAction()))) {
            return new EventResponse(savePoint(eventRequest));
        } else if (Action.MOD.equals(Action.valueOf(eventRequest.getAction()))) {
            // 기존에 삭제 row를 넣고 다시 계산
            return new EventResponse(deletePoint(eventRequest) + savePoint(eventRequest));
        } else if (Action.DELETE.equals(Action.valueOf(eventRequest.getAction()))) {
            return new EventResponse(deletePoint(eventRequest));
        }

        throw new Exception("ACTION ERROR!!");
    }

    private int bonusPlaceScore(String placeId) {
        if(placeRepository.existsByPlaceUuidAndDeletedYnFalse(placeId)) {
            return Score.FIRST_PLACE.getScore();
        }

        return 0;
    }

    private int savePoint(EventRequest eventRequest) {
        int savePoint = PointCalculator.calculatorPoint(eventRequest.getContent(), eventRequest.getAttachedPhotoIds()) + bonusPlaceScore(eventRequest.getPlaceId());

        Photo photo = Photo.builder()
                .photoUuid(eventRequest.getPlaceId())
                .build();

        List<Photo> photoList = new ArrayList<>();
        photoList.add(photo);

        Point point = Point.builder()
                .type(EarnPolicy.valueOf(eventRequest.getType()).toString())
                .action(Action.valueOf(eventRequest.getAction()).toString())
                .reviewId(eventRequest.getReviewId())
                .content(eventRequest.getContent())
                .userId(eventRequest.getUserId())
                .savePoint(BigDecimal.valueOf(savePoint))
                .photoList(photoList)
                .build();

        pointRepository.save(point);

        Place place = Place.builder()
                .userId(eventRequest.getUserId())
                .placeUuid(eventRequest.getPlaceId())
                .build();

        placeRepository.save(place);

        return point.getSavePoint().intValue();
    }

    private int deletePoint(EventRequest eventRequest) throws Exception {
        Point modPoint = pointRepository.findTopByReviewIdOrderByCreatedAtDesc(eventRequest.getReviewId()).orElseThrow(
                () -> new Exception("not find mod point!!")
        );

        Point deletePoint = modPoint.deletePoint();
        pointRepository.save(deletePoint);

        return deletePoint.getSavePoint().intValue();
    }

    public RemainPointResponse remainPoint(String userId) {
        Optional<Point> pointOptional = pointRepository.findAllByUserIdAndDeletedYnFalse(userId);

        int sumPoinkt = pointOptional.stream().mapToInt(point -> point.getSavePoint().intValue()).sum();

        return new RemainPointResponse(sumPoinkt);
    }
}
