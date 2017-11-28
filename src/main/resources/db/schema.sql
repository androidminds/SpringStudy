

drop table IF EXISTS t_user;
create table t_user(
   user_id INT AUTO_INCREMENT PRIMARY KEY,
   user_name VARCHAR(30) not null,
   credits INT,
   password VARCHAR(32) not null,
   last_visit DATETIME,
   last_ip VARCHAR(23)
)