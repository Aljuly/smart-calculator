FROM postgres:latest
LABEL key="aljuly"

ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD passw0rd
ENV POSTGRES_DB calculator

WORKDIR /docker-entrypoint-initdb.d
ADD schema.sql /docker-entrypoint-initdb.d
