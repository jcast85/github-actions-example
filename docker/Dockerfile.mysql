FROM mysql:latest

# Environment variables
ENV MYSQL_ROOT_PASSWORD=mysqlrootpass \
    MYSQL_USER=mysqluser \
    MYSQL_PASSWORD=mysqlpassword \
    MYSQL_DATABASE=mysqlappdb

# Copy initialization SQL script
COPY ./backend/src/test/resources/setup.sql /docker-entrypoint-initdb.d/

# Expose port
EXPOSE 3306

# MySQL healthcheck
HEALTHCHECK --interval=5s --timeout=5s --retries=10 \
  CMD mysqladmin ping -h localhost -u root -p${MYSQL_ROOT_PASSWORD} || exit 1
