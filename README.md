# 포인트 과제

- 실행 방법
1. 빌드
2. docker compose 설치
3. docker-compose up -d

- API 스펙

```json
POST /events
- request
{
	"type": "REVIEW",
	"action": "ADD", /* "MOD", "DELETE" */
	"reviewId": "240a0658-dc5f-4878-9381-ebb7b2667772",
	"content": " !",
	"attachedPhotoIds": ["e4d1a64e-a531-46de-88d0-ff0ed70c0bb8", "afb0cef2-
	851d-4a50-bb07-9cc15cbdc332"],
	 "userId": "3ede0ef2-92b7-4817-a5f3-0c575361f745",
	 "placeId": "2e4baf1c-5acb-4efb-a1af-eddada31b00f"
}

- response
{
	earnPoint : 100
}
```

```json
GET /point
- request
{
	"type": "REVIEW",
	"action": "ADD", /* "MOD", "DELETE" */
	"reviewId": "240a0658-dc5f-4878-9381-ebb7b2667772",
	"content": " !",
	"attachedPhotoIds": ["e4d1a64e-a531-46de-88d0-ff0ed70c0bb8", "afb0cef2-
	851d-4a50-bb07-9cc15cbdc332"],
	 "userId": "3ede0ef2-92b7-4817-a5f3-0c575361f745",
	 "placeId": "2e4baf1c-5acb-4efb-a1af-eddada31b00f"
}

- response
{
	remainPoint : 100
}
```

- 추가 고려 사항
    - redis로 조회및 동시성제어
    - mysql select, insert datasource 바꾸기
    - 테스트 코드
    - rest doc 문서화 적용
    - AWS SQS or Kafka를 활용한 이벤트 기반 고가용성

  
- DDL
```sql
  create table point.photo
  (
  photo_id   bigint auto_increment,
  created_at datetime(6)  not null,
  deleted_yn bit          not null,
  photo_uuid varchar(255) not null,
  updated_at datetime(6)  not null,
  point_id   bigint       not null,
  constraint photo_photo_id_uindex
  unique (photo_id)
  );

alter table point.photo
add primary key (photo_id);

create table point.place
(
place_id   bigint auto_increment,
created_at datetime(6)      not null,
deleted_yn bit default b'0' not null,
place_uuid varchar(255)     not null,
updated_at datetime(6)      not null,
user_id    varchar(255)     not null,
constraint place_place_id_uindex
unique (place_id)
);

create index place_place_uuid_index
on point.place (place_uuid);

alter table point.place
add primary key (place_id);

create table point.point
(
point_id   bigint auto_increment,
save_point bigint           not null,
action     varchar(255)     not null,
content    varchar(255)     null,
created_at datetime(6)      not null,
deleted_yn bit default false not null,
review_id  varchar(255)     not null,
type       varchar(255)     not null,
updated_at datetime(6)      not null,
user_id    varchar(255)     not null,
constraint point_point_id_uindex
unique (point_id)
);

create index point_review_id_user_id_index
on point.point (review_id, user_id);

alter table point.point
add primary key (point_id);
```
