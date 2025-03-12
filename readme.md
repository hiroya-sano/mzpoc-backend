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

curl http://localhost:8080/sleep
curl http://localhost:8080/sleep/multi

curl http://localhost:8080/memoryLeak
curl http://localhost:8080/memoryLeak/release

curl http://localhost:8080/heavyData

curl http://localhost:8080/cpuload
curl http://localhost:8080/cpuload?n=80

# 停止
docker-compose down
```

## DB

```bash
# 接続(AKS)
endpoint=mz12poc.postgres.database.azure.com            # ULS環境
endpoint=cloudinfra-dev-db.postgres.database.azure.com  # MC環境
user=postgreuser
password=Password1234
psql "host=${endpoint} port=5432 dbname=postgres user=${user} password=${password} sslmode=require"

# 接続(ローカル)
endpoint=localhost
user=postgres
password=postgresk
psql "host=${endpoint} port=5432 dbname=carappdb user=${user} password=${password}"
```