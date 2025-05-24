CREATE DATABASE  IF NOT EXISTS `CREDIT_CARD_REQUEST_DIRECTORY`;
USE `CREDIT_CARD_REQUEST_DIRECTORY`;

--
-- Table structure for table `credit card request`
--

DROP TABLE IF EXISTS `CREDIT_CARD_REQUEST`;

CREATE TABLE `CREDIT_CARD_REQUEST` (
	`request_id` int NOT NULL AUTO_INCREMENT,
	`first_name` varchar(35),
	`last_name` varchar(35),
	`oib` varchar(11),
	`status` varchar(10),
  PRIMARY KEY (`request_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO credit_card_request (first_name, last_name, oib, status)
  VALUES ('Benjamin', 'Vinkovic', '11112223334' ,'pending');
INSERT INTO credit_card_request (first_name, last_name, oib, status)
  VALUES ('Marko', 'Markic', '111' ,'rejected');
INSERT INTO credit_card_request (first_name, last_name, oib, status)
  VALUES ('Luka', 'Lukic', '222' ,'rejected');
INSERT INTO credit_card_request (first_name, last_name, oib, status)
  VALUES ('Ivan', 'Ivic', '333' ,'pending');
INSERT INTO credit_card_request (first_name, last_name, oib, status)
  VALUES ('Matej', 'Matic', '444' ,'pending');
INSERT INTO credit_card_request (first_name, last_name, oib, status)
  VALUES ('Benjamin', 'Vinkovic', '11112223334' ,'approved');