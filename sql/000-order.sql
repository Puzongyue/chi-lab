create table `order`
(
	id int auto_increment
		primary key,
	schedule_id int null,
	price double null,
	ticket_num int null,
	order_time datetime null,
	user_id int null
);

