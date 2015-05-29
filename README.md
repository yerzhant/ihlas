How to install
1. psql -h localhost -U postgres -f create.sql
2. psql -h localhost -U ihlas -f ihlas.sql
3. psql -h localhost -U ihlas -f init.sql
4. add Security Domain ihlas with empty cache type
5. code: Database, flag: required
6. options:
dsJndiName = java:jboss/datasources/ihlas
principalsQuery = select password from users where left_login_tries > 0 and name = ?
rolesQuery = select g.name, 'Roles' from users_groups g join users u on u.id = g.user_id and u.name = ?
