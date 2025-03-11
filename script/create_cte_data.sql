-- テーブル作成
CREATE TABLE heavy_main (
  id INT PRIMARY KEY,
  category VARCHAR(50),
  value INT
);

CREATE TABLE heavy_detail (
  detail_id INT PRIMARY KEY,
  main_id INT,
  description TEXT,
  FOREIGN KEY (main_id) REFERENCES heavy_main(id)
);

-- 検証用データインポート
\copy heavy_main FROM './script/data/heavy_main_data.csv' CSV HEADER;
\copy heavy_detail FROM './script/data/heavy_detail_data.csv' CSV HEADER;

-- 検証用データインポート（ローカル用）
\copy heavy_main FROM '/var/data/heavy_main_data.csv' CSV HEADER;
\copy heavy_detail FROM '/var/data/heavy_detail_data.csv' CSV HEADER;

-- 検証用SQL
SELECT m.id, m.category, m.value, d.description
FROM heavy_main m
JOIN heavy_detail d ON m.id = d.main_id
WHERE d.description = 'This is a sample description for item 1.';

SELECT m.id, m.category, m.value, d.description
FROM heavy_main m
JOIN heavy_detail d ON m.id = d.main_id
WHERE m.value > 140 limit 10;


-- 確認用
\dt
\d heavy_main
\d heavy_detail
select count(*) from heavy_main;
select * from heavy_main order by id desc limit 10;
select count(*) from heavy_detail;
select * from heavy_detail order by detail_id desc limit 10;

-- 検証後のデータ削除
delete from heavy_detail;
delete from heavy_main;
