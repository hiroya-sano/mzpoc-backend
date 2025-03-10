# Readme

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

# 停止
docker-compose down
```