#!/usr/bin/env bash
if [ "$TRAVIS_BRANCH" = 'master' ] && [ "$TRAVIS_PULL_REQUEST" == 'false' ]; then
    openssl aes-256-cbc -K $encrypted_c97290677436_key -iv $encrypted_c97290677436_iv -in deployment/keys.asc.enc -out deployment/keys.asc -d
    gpg --fast-import deployment/keys.asc
fi