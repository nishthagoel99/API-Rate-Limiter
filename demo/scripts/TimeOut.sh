#!/bin/bash


for value in {1..10}
do
echo $value
curl -b 'userID=manoj' "http://localhost:8090/documents" #should run successfully max 4 times within 60s

done
sleep 60 #to make sure that fixed window time is elapsed.
#after 60seconds, API calls should be made.
curl -b 'userID=manoj' "http://localhost:8090/documents" #should run successfully max 4 times within 60s
curl -b 'userID=manoj' "http://localhost:8090/documents"


echo All done