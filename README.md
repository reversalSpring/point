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
    - 테스트 코드 넣기 ㅠㅠ
    - rest doc 문서화 적용