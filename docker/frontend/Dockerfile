FROM node:18-alpine3.20 as angular

WORKDIR /app
COPY . .
RUN npm install
RUN npm run build:prod

FROM httpd:alpine3.15

WORKDIR /usr/local/apache2/htdocs
COPY --from=angular /app/dist/my-first-project .
