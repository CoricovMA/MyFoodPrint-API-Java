mvn clean install

bash BuildTools/stop_rm_containers.sh
bash BuildTools/build_run.sh
docker rmi $(docker images -q -f before=test2)