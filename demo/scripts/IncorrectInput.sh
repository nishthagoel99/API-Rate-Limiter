#!/bin/bash
# PURPOSE :provide incorrect api and/or userID
# INPUT:
#       1. Incorrect useriD
#       2. Incorrect API call
#OUTPUT:
#       ERROR:API/User not in Database for user ----->for (1)
#       ERROR:API/User not in Database for user ----->for (2)
for value in {1..1}
do
echo $value
curl -b 'userID=ashwini345' "http://localhost:8090/documents" #incorrect userID
curl -b 'userID=ashwini' "http://localhost:8090/login123" #incorrect API call
done