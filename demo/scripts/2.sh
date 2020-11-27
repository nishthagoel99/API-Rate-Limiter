#!/bin/bash

# PURPOSE :different user+API combination has different rate limits
# INPUT:
#       1. user with userID 'nishtha' calls API login 10 times
#OUTPUT:
#       should accept at most 4 calls within 60s
#       INFO:/login API called successfully!******************200 OK
#       should reject 6 API calls
#       ERROR:Cannot make API call******************429 TOO MANY REQUESTS

for value in {1..10}
do
echo $value
curl -b 'userID=nishtha' "http://localhost:8090/login" #should run successfully max 4 times within 60s
done