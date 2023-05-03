insert into city (id, city_code, city_name, grade) values (1,'CD11', 'Seoul', 3 );
insert into city (id, city_code, city_name, grade) values (2, 'CD26', 'Busan', 2 );

insert into gu (id, gu_code, gu_name) values  (1, 'CD11140', "중구" );
insert into gu (id, gu_code, gu_name) values  (2, 'CD11170', "용산구" );

insert into spot(id, spot_name, comment, gu_id) values (1, "spot1" , "spot1은 사진작가라면 가봐야할 3대 바다 사진 스팟입니다.",1);
insert into spot(id, spot_name, comment, gu_id) values (2, "spot2" , "spot2은 사진작가라면 가봐야할 3대 산 사진 스팟입니다.",1);