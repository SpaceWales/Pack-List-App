insert into users (username) values ('william');
insert into packlist (user_id) values (1);
insert into item (pack_id,item_name,item_weight,weight_unit) values (1,'tent',2.5,'g');
insert into item (pack_id,item_name,item_weight,weight_unit) values (1,'bag',5,'oz');
insert into item (pack_id,item_name,item_weight,weight_unit) values (1,'pack',15,'lbs');
insert into item (pack_id,item_name,item_weight,weight_unit) values (1,'food',4,'g');
insert into item (pack_id,item_name,item_weight,weight_unit) values (1,'water',3,'lbs');

insert into trip(region,date_start,date_end) values ('michigan','2021-04-30','2021-05-02');

select * from item where pack_id = 1;

select * from item 
join packlist on packlist.pack_id = item.pack_id
join users on packlist.user_id = users.user_id
where packlist.user_id = 1;

insert into users (username) values('ford');
insert into packlist (user_id) values (2);
insert into item (pack_id,item_name,item_weight,weight_unit) values (2,'tent',2.5,'g');
insert into item (pack_id,item_name,item_weight,weight_unit) values (2,'bag',5,'oz');
insert into item (pack_id,item_name,item_weight,weight_unit) values (2,'pack',15,'lbs');
insert into item (pack_id,item_name,item_weight,weight_unit) values (2,'food',4,'g');
insert into item (pack_id,item_name,item_weight,weight_unit) values (2,'water',3,'lbs');

insert into user_trip (user_id,trip_id) values(1,1);
insert into user_trip (user_id,trip_id) values(2,1);

insert into packlist(user_id) values(5);

select * from packlist where user_id = 5 order by pack_id desc limit 1;

select * from packlist
join item on packlist.user_id = item.pack_id where user_id = 2;

