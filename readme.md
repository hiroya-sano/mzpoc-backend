# Readme

## アプリケーション

```bash
# ビルド & デプロイ（ローカル）
cd backend
docker-compose up --build

# APIコールサンプル
curl http://localhost:8080/cars
curl -X POST \
  -H "Content-Type: application/json" \
  -d '{"model": "Toyota", "custom": "スポーツパッケージ"}' \
  http://localhost:8080/cars

curl http://localhost:8080/dummyerrors
curl http://localhost:8080/memoryLeak
curl http://localhost:8080/memoryLeak/release

curl http://localhost:8080/fullscan
curl http://localhost:8080/fullscan?description=hit

# 停止
docker-compose down
```

## DB

```bash
# 接続(AKS)
endpoint=mz12poc.postgres.database.azure.com
user=postgreuser
password=Password1234
psql "host=${endpoint} port=5432 dbname=postgres user=${user} password=${password} sslmode=require"

# 接続(ローカル)
endpoint=localhost
user=postgres
password=postgresk
psql "host=${endpoint} port=5432 dbname=carappdb user=${user} password=${password}"
```