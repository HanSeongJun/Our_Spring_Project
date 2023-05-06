insert into city (id, city_code, city_name, grade) values (1,'CD11', '서울', 3 );
insert into city (id, city_code, city_name, grade) values (2, 'CD26', '부산', 3 );
insert into city (id, city_code, city_name, grade) values (3,'CD27', '대구', 1 );
insert into city (id, city_code, city_name, grade) values (4,'CD28', '인천', 1 );
insert into city (id, city_code, city_name, grade) values (5, 'CD29', '광주', 1 );
insert into city (id, city_code, city_name, grade) values (6, 'CD30', '대전', 2 );
insert into city (id, city_code, city_name, grade) values (7,'CD31', '울산', 1 );
insert into city (id, city_code, city_name, grade) values (8, 'CD36', '세종', 1 );
insert into city (id, city_code, city_name, grade) values (9, 'CD41', '경기', 1 );
insert into city (id, city_code, city_name, grade) values (10,'CD42', '강원', 3 );
insert into city (id, city_code, city_name, grade) values (11, 'CD43', '충북', 2 );
insert into city (id, city_code, city_name, grade) values (12, 'CD44', '충남', 1 );
insert into city (id, city_code, city_name, grade) values (13,'CD45', '전북', 1 );
insert into city (id, city_code, city_name, grade) values (14, 'CD46', '전남', 2);
insert into city (id, city_code, city_name, grade) values (15,'CD47', '경북', 1 );
insert into city (id, city_code, city_name, grade) values (16, 'CD48', '경남', 3 );
insert into city (id, city_code, city_name, grade) values (17,'CD50', '제주', 2);
-- city table rule
-- city table의 city_name은 api에서 가능한 name으로 동일하게 넣어야한다.
-- city table의 city_code는 SVG에서 제공하는 id값도 동일하게 넣어야한다.


insert into gu (id, gu_code, gu_name) values  (1, 'CD11140', "중구" );
insert into gu (id, gu_code, gu_name) values  (2, 'CD11170', "용산구" );

insert into spot(id, spot_name, comment, gu_id) values (1, "spot1" , "spot1은 사진작가라면 가봐야할 3대 바다 사진 스팟입니다.",1);
insert into spot(id, spot_name, comment, gu_id) values (2, "spot2" , "spot2은 사진작가라면 가봐야할 3대 산 사진 스팟입니다.",1);