#!/bin/bash

# PURPOSE :multiple APIs accessed by same user
# INPUT:
#       1. user with userID 'ashwini' calls API documents 10 times
#       2. user with userID 'ashwini' calls API login 10 times
#OUTPUT:
#       should run successfully max 6 times within 60s                     ----->for (1)
#       should reject 4 API calls                                          ----->for (1)
#       ERROR:Cannot make API call******************429 TOO MANY REQUESTS  ----->for (1)
#       should run successfully max 3 times within 60s                     ----->for (2)
#       should reject 7 API calls                                          ----->for (2)
#       ERROR:Cannot make API call******************429 TOO MANY REQUESTS  ----->for (2)
for value in {1..10}
do
echo $value
curl -b 'userID=ashwini' "http://localhost:8090/documents" #should run successfully max 6 times within 60s
done