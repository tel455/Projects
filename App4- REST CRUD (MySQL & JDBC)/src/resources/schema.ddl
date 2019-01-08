create database cs378_devdatta_thi;

use cs378_devdatta_thi;

create table traffic_reports(published_date varchar(255) NOT NULL, issue_reported varchar(255) NOT NULL,
								address varchar(255) NOT NULL, zipcode varchar(20) NOT NULL, id int NOT NULL AUTO_INCREMENT, PRIMARY KEY(id));

