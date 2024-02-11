@echo off
SET DEVELOPMENT_HOME=D:\Omkar\spring-boot-microservices

cd %DEVELOPMENT_HOME%\service-registry\
call mvn install
call javaw -jar %DEVELOPMENT_HOME%\service-registry\target\*.jar

cd %DEVELOPMENT_HOME%\config-server\
call mvn install
call javaw -jar %DEVELOPMENT_HOME%\config-server\target\*.jar

cd %DEVELOPMENT_HOME%\department-service\
call mvn install
call javaw -jar %DEVELOPMENT_HOME%\department-service\target\*.jar

cd %DEVELOPMENT_HOME%\employee-service\
call mvn install
call javaw -jar %DEVELOPMENT_HOME%\employee-service\target\*.jar

cd %DEVELOPMENT_HOME%\api-gateway\
call mvn install
call javaw -jar %DEVELOPMENT_HOME%\api-gateway\target\*.jar

call javaw -jar %DEVELOPMENT_HOME%\*.jar