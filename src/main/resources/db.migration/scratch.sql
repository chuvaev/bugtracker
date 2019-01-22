DROP schema if exists test_db;
CREATE SCHEMA TEST_DB;
USE TEST_DB;

DROP TABLE IF EXISTS project;
CREATE TABLE project(
  project_id INT(11) NOT NULL auto_increment,
  project_name VARCHAR(50) NOT NULL,
  description VARCHAR(150),
  PRIMARY KEY (project_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS user;
CREATE TABLE user(
  user_id INT(11) NOT NULL AUTO_INCREMENT,
  login VARCHAR(50) NOT NULL,
  project_id INT(11) NOT NULL,
  PRIMARY KEY (user_id),
  CONSTRAINT FK_PROJECT_ID FOREIGN KEY (project_id)
  REFERENCES project (project_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS issue;
CREATE TABLE issue(
  issue_id INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  data TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  project_id INT(11) NOT NULL,
  user_id INT(11) NOT NULL,
  PRIMARY KEY (issue_id),
  CONSTRAINT FK_PROJECT_ISSUE_ID FOREIGN KEY (project_id)
  REFERENCES project (project_id),
  CONSTRAINT FK_USER_ISSUE_ID FOREIGN KEY (user_id)
  REFERENCES user (user_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


INSERT INTO `test_db`.`project` (`project_name`, `project_id`) VALUES('First Project', '1');
INSERT INTO `test_db`.`project` (`project_name`, `project_id`) VALUES ('Second Project', '2');
INSERT INTO `test_db`.`project` (`project_name`, `project_id`) VALUES ('Third Project', '3');

INSERT INTO `test_db`.`user` (`user_id`, `login`, `project_id`) VALUES ('1', 'user1', '1');
INSERT INTO `test_db`.`user` (`user_id`, `login`, `project_id`) VALUES ('2', 'user2', '2');
INSERT INTO `test_db`.`user` (`user_id`, `login`, `project_id`) VALUES ('3', 'user3', '3');

INSERT INTO `test_db`.`issue` (`issue_id`, `name`, `data`, `project_id`, `user_id`) VALUES ('1', 'ERROR1', '2019.01.20', '1', '1');
INSERT INTO `test_db`.`issue` (`issue_id`, `name`, `data`, `project_id`, `user_id`) VALUES ('2', 'ERROR2', '2019.01.20', '2', '2');
INSERT INTO `test_db`.`issue` (`issue_id`, `name`, `data`, `project_id`, `user_id`) VALUES ('3', 'ERROR3', '2019.01.20', '3', '3');

SELECT Project.project_id, User.login, Issue.name
FROM ((project
  INNER JOIN user ON project.project_id = user.user_id)
  INNER JOIN issue ON user.user_id = issue.issue_id);
