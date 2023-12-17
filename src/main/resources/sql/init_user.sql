INSERT INTO "user" (id, name, email, picture, state, role, created_date, modified_date)
VALUES ('feb6d5cb-c348-435f-a766-f2ea606ee6b4', '하동구', 'gkehdrn95@naver.com',
        'http://k.kakaocdn.net/dn/bqQUgJ/btssZdXB3Yp/ornRJI8H0N6vKNNTmoybHK/img_640x640.jpg', 'ACTIVATE', 'ADMIN',
        '2023-11-29 14:56:23.52', '2023-11-29 14:56:23.52');


INSERT INTO AUTHORITIES (user_id, authority)
VALUES ('feb6d5cb-c348-435f-a766-f2ea606ee6b4', 'ADMIN');
INSERT INTO AUTHORITIES (user_id, authority)
VALUES ('feb6d5cb-c348-435f-a766-f2ea606ee6b4', 'MEMBER');

---
INSERT INTO "user" (id, name, email, picture, state, role, created_date, modified_date)
VALUES ('feb6d5cb-c348-435f-a766-f2ea606ee6b3', '하동팔', 'gkehdrn96@naver.com',
        'http://k.kakaocdn.net/dn/bqQUgJ/btssZdXB3Yp/ornRJI8H0N6vKNNTmoybHK/img_640x640.jpg', 'ACTIVATE', 'MEMBER',
        '2023-11-29 14:56:23.52', '2023-11-29 14:56:23.52');

INSERT INTO AUTHORITIES (user_id, authority)
VALUES ('feb6d5cb-c348-435f-a766-f2ea606ee6b3', 'MEMBER');