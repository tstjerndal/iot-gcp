gcloud container clusters create iot-cluster --num-nodes 2 --machine-type n1-standard-1 --zone europe-west1-b
gcloud container clusters get-credentials iot-cluster --region europe-west1-b
kubectl run iot-java --image=gcr.io/iot-starvalley-server/iot-java:v1 --port 8080
kubectl expose deployment iot-java --type=LoadBalancer
kubectl create secret generic cloudsql-instance-credentials --from-file credentials.json=../iot-starvalley-server-aff5a7c7c4c0.json
kubectl create secret generic cloud-db-credentials --from-literal=username=iotUser --from-literal=password=mjaa3579

#cloud sql proxy
curl -o cloud_sql_proxy https://dl.google.com/cloudsql/cloud_sql_proxy.darwin.amd64
chmod +x cloud_sql_proxy
./cloud_sql_proxy -instances=iot-starvalley-server:europe-west1:iot-db=tcp:3306
brew install mysql-client
/usr/local/opt/mysql-client/bin/mysql --port=3306 --host=127.0.0.1 --password=mjaa3579 --user=iotUser --database=iot-db


