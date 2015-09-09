-- 分片组模型
DROP TABLE IF EXISTS tb_shard_group;
CREATE TABLE tb_shard_group (
  id int(11) unsigned NOT NULL AUTO_INCREMENT,
  name varchar(20) NOT NULL ,
  begin_id int(10) NOT NULL,
  end_id int(10) NOT NULL,
  writable tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (id)
);

-- 分片模型
DROP TABLE IF EXISTS tb_shard;
CREATE TABLE tb_shard (
  id int(11) unsigned NOT NULL AUTO_INCREMENT,
  shard_group_id int(11) NOT NULL,
  name varchar(50) NOT NULL,
  hash_value varchar(20) NOT NULL,
  PRIMARY KEY (id)
);

-- 分段表模型
DROP TABLE IF EXISTS tb_fragment_table;
CREATE TABLE tb_fragment_table (
  id int(11) NOT NULL AUTO_INCREMENT,
  shard_id int(11) NOT NULL,
  name varchar(50) NOT NULL,
  begin_id int(11) NOT NULL,
  end_id int(11) NOT NULL,
  PRIMARY KEY (id)
);
