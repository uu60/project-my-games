#!/bin/bash

cd frontend-my-games
npm run build
cd ..

rm -rf backend-my-games/src/main/resources/static/*
cp -r frontend-my-games/dist/* backend-my-games/src/main/resources/static
echo 'done'