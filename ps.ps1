docker-compose down
echo Y | docker volume prune
echo Y | docker system prune -a
docker-compose up -d
docker-compose push
