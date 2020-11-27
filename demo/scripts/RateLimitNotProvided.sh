#!/bin/bash

# PURPOSE :when no RateLImit is specified in DB, ASSUME DEFAULT ratelimit as 5
# INPUT:
#       1. userID and API call WITHOUT predefined rate limit in database 10 times
#OUTPUT:
#       should run successfully max 5 times within 60s                     ----->for (1)
#       should reject 5 API calls                                          ----->for (1)
#       ERROR:Cannot make API call******************429 TOO MANY REQUESTS  ----->for (1)
for value in {1..10}
do
echo $value

curl -b 'userID=manoj2' "http://localhost:8090/login"  #manoj2 userID has no RateLimit specified for login API in DB
done