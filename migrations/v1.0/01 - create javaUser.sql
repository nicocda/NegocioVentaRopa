CREATE USER 
 'javaUser'@'localhost'
   IDENTIFIED BY 'root';
   GRANT ALL PRIVILEGES ON ropa.* TO 'javaUser'@'localhost' WITH GRANT OPTION;