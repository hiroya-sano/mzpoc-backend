-- テーブル作成
CREATE TABLE heavy_data (
  id INT PRIMARY KEY,
  category VARCHAR(50),
  value INT,
  description TEXT
);

-- 検証用データインポート
\copy heavy_data FROM './script/data/heavy_data.csv' CSV HEADER;

-- 確認用
\dt
\d heavy_data
select * from heavy_data;
select count(*) from heavy_data;
select * from heavy_data limit 10;
select * from heavy_data order by id desc limit 10;

-- 検証後のデータ削除
delete from heavy_data;
