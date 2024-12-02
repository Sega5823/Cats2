@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  GateWay startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and GATE_WAY_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\GateWay-1.0-SNAPSHOT-plain.jar;%APP_HOME%\lib\Entity-1.0-SNAPSHOT.jar;%APP_HOME%\lib\cage-1.0.jar;%APP_HOME%\lib\spring-boot-starter-thymeleaf-3.2.0.jar;%APP_HOME%\lib\spring-boot-starter-web-3.2.3.jar;%APP_HOME%\lib\spring-boot-starter-security-3.2.0.jar;%APP_HOME%\lib\spring-boot-starter-data-jpa-3.2.3.jar;%APP_HOME%\lib\spring-boot-starter-mail-3.2.0.jar;%APP_HOME%\lib\spring-kafka-3.2.3.jar;%APP_HOME%\lib\postgresql-42.2.12.jar;%APP_HOME%\lib\spring-boot-starter-json-3.2.3.jar;%APP_HOME%\lib\jackson-datatype-jsr310-2.17.2.jar;%APP_HOME%\lib\jackson-annotations-2.17.2.jar;%APP_HOME%\lib\jackson-datatype-jdk8-2.17.2.jar;%APP_HOME%\lib\jackson-module-parameter-names-2.17.2.jar;%APP_HOME%\lib\jackson-core-2.17.2.jar;%APP_HOME%\lib\jackson-databind-2.17.2.jar;%APP_HOME%\lib\bootstrap-5.3.3.jar;%APP_HOME%\lib\lombok-1.18.22.jar;%APP_HOME%\lib\spring-boot-starter-aop-3.2.3.jar;%APP_HOME%\lib\spring-boot-starter-jdbc-3.2.3.jar;%APP_HOME%\lib\spring-boot-starter-3.2.3.jar;%APP_HOME%\lib\thymeleaf-spring6-3.1.2.RELEASE.jar;%APP_HOME%\lib\spring-security-config-6.2.0.jar;%APP_HOME%\lib\spring-security-web-6.2.0.jar;%APP_HOME%\lib\spring-context-support-6.1.1.jar;%APP_HOME%\lib\spring-data-jpa-3.2.3.jar;%APP_HOME%\lib\spring-webmvc-6.1.4.jar;%APP_HOME%\lib\spring-security-core-6.2.0.jar;%APP_HOME%\lib\spring-boot-autoconfigure-3.2.3.jar;%APP_HOME%\lib\spring-boot-3.2.3.jar;%APP_HOME%\lib\spring-context-6.1.12.jar;%APP_HOME%\lib\spring-aop-6.1.12.jar;%APP_HOME%\lib\jakarta.mail-2.0.2.jar;%APP_HOME%\lib\spring-messaging-6.1.12.jar;%APP_HOME%\lib\spring-orm-6.1.4.jar;%APP_HOME%\lib\spring-jdbc-6.1.4.jar;%APP_HOME%\lib\spring-tx-6.1.12.jar;%APP_HOME%\lib\spring-retry-2.0.8.jar;%APP_HOME%\lib\kafka-clients-3.7.1.jar;%APP_HOME%\lib\spring-web-6.1.4.jar;%APP_HOME%\lib\micrometer-observation-1.13.3.jar;%APP_HOME%\lib\hibernate-core-6.4.4.Final.jar;%APP_HOME%\lib\spring-aspects-6.1.4.jar;%APP_HOME%\lib\spring-boot-starter-tomcat-3.2.3.jar;%APP_HOME%\lib\thymeleaf-3.1.2.RELEASE.jar;%APP_HOME%\lib\HikariCP-5.0.1.jar;%APP_HOME%\lib\spring-data-commons-3.2.3.jar;%APP_HOME%\lib\spring-boot-starter-logging-3.2.3.jar;%APP_HOME%\lib\logback-classic-1.4.14.jar;%APP_HOME%\lib\log4j-to-slf4j-2.21.1.jar;%APP_HOME%\lib\jul-to-slf4j-2.0.12.jar;%APP_HOME%\lib\slf4j-api-2.0.12.jar;%APP_HOME%\lib\spring-beans-6.1.12.jar;%APP_HOME%\lib\spring-expression-6.1.12.jar;%APP_HOME%\lib\spring-core-6.1.12.jar;%APP_HOME%\lib\jaxb-runtime-4.0.2.jar;%APP_HOME%\lib\jaxb-core-4.0.2.jar;%APP_HOME%\lib\angus-activation-2.0.1.jar;%APP_HOME%\lib\jakarta.xml.bind-api-4.0.0.jar;%APP_HOME%\lib\jakarta.activation-api-2.1.2.jar;%APP_HOME%\lib\zstd-jni-1.5.6-3.jar;%APP_HOME%\lib\lz4-java-1.8.0.jar;%APP_HOME%\lib\snappy-java-1.1.10.5.jar;%APP_HOME%\lib\micrometer-commons-1.13.3.jar;%APP_HOME%\lib\aspectjweaver-1.9.21.jar;%APP_HOME%\lib\jakarta.persistence-api-3.1.0.jar;%APP_HOME%\lib\jakarta.transaction-api-2.0.1.jar;%APP_HOME%\lib\jboss-logging-3.5.0.Final.jar;%APP_HOME%\lib\hibernate-commons-annotations-6.0.6.Final.jar;%APP_HOME%\lib\jandex-3.1.2.jar;%APP_HOME%\lib\classmate-1.5.1.jar;%APP_HOME%\lib\byte-buddy-1.14.11.jar;%APP_HOME%\lib\jakarta.inject-api-2.0.1.jar;%APP_HOME%\lib\antlr4-runtime-4.13.0.jar;%APP_HOME%\lib\jakarta.annotation-api-2.1.1.jar;%APP_HOME%\lib\snakeyaml-2.2.jar;%APP_HOME%\lib\tomcat-embed-websocket-10.1.19.jar;%APP_HOME%\lib\tomcat-embed-core-10.1.19.jar;%APP_HOME%\lib\tomcat-embed-el-10.1.19.jar;%APP_HOME%\lib\attoparser-2.0.7.RELEASE.jar;%APP_HOME%\lib\unbescape-1.1.6.RELEASE.jar;%APP_HOME%\lib\spring-security-crypto-6.2.0.jar;%APP_HOME%\lib\spring-jcl-6.1.12.jar;%APP_HOME%\lib\txw2-4.0.2.jar;%APP_HOME%\lib\istack-commons-runtime-4.1.1.jar;%APP_HOME%\lib\logback-core-1.4.14.jar;%APP_HOME%\lib\log4j-api-2.21.1.jar


@rem Execute GateWay
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GATE_WAY_OPTS%  -classpath "%CLASSPATH%" org.example.SpringMainGateWay %*

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable GATE_WAY_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%GATE_WAY_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
