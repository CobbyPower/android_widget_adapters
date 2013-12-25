:: /*
::  * =================================================================================
::  * Copyright (C) 2013 Martin Albedinsky [Wolf-ITechnologies]
::  * =================================================================================
::  * Licensed under the Apache License, Version 2.0 or later (further "License" only);
::  * ---------------------------------------------------------------------------------
::  * You may use this file only in compliance with the License. More details and copy
::  * of this License you may obtain at
::  *
::  * 		http://www.apache.org/licenses/LICENSE-2.0
::  *
::  * You can redistribute, modify or publish any part of the code written in this
::  * file but as it is described in the License, the software distributed under the
::  * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES or CONDITIONS OF
::  * ANY KIND.
::  *
::  * See the License for the specific language governing permissions and limitations
::  * under the License.
::  * =================================================================================
::  */
:: ------------------------------------------------------------------------------------
::      UPDATE SCRIPT FOR 'ANDROID WIDGET ADAPTERS' JAR FILE FOR MAVEN REPOSITORY
:: ------------------------------------------------------------------------------------
@ECHO OFF

:: ====================================================================================
:: Library specific parameters
:: ------------------------------------------------------------------------------------
@SET LIBRARY_NAME=android-widget-adapters
@SET LIBRARY_VERSION=1.0
:: ====================================================================================
:: Script global parameters
:: ------------------------------------------------------------------------------------
:: Maven repository parameters
@SET MAVEN_LOCAL_PATH=C:\Users\Martin\.m2\repository\
@SET MAVEN_LIBRARY_GROUP_PATH=%MAVEN_LOCAL_PATH%com\wit\android\
@SET MAVEN_LIBRARY_GROUP_ID=com.wit.android
@SET MAVEN_LIBRARY_JAR_SOURCES=%MAVEN_LIBRARY_GROUP_PATH%%LIBRARY_NAME%\%LIBRARY_VERSION%\%LIBRARY_NAME%-%LIBRARY_VERSION%-sources.jar
@SET MAVEN_LIBRARY_JAR_JAVADOC=%MAVEN_LIBRARY_GROUP_PATH%%LIBRARY_NAME%\%LIBRARY_VERSION%\%LIBRARY_NAME%-%LIBRARY_VERSION%-javadoc.jar
:: Library parameters
@SET LIBRARY_ARTIFACTS_DIR=..\artifacts\
:: Build the names of jar files
@SET LIBRARY_JAR=%LIBRARY_ARTIFACTS_DIR%%LIBRARY_NAME%-%LIBRARY_VERSION%.jar
@SET LIBRARY_JAR_SOURCES=%LIBRARY_ARTIFACTS_DIR%%LIBRARY_NAME%-%LIBRARY_VERSION%-sources.jar
@SET LIBRARY_JAR_JAVADOC=%LIBRARY_ARTIFACTS_DIR%%LIBRARY_NAME%-%LIBRARY_VERSION%-javadoc.jar
:: ------------------------------------------------------------------------------------
:: Save input parameter.
@SET PARAM=%1
:: ------------------------------------------------------------------------------------
:: Check action to perform.
IF [%PARAM%]==[-type] GOTO ListTypes
IF [%PARAM%]==[jar] GOTO Jar
IF [%PARAM%]==[support] GOTO Support
GOTO UnknownParameter
:ListTypes
ECHO.Use one of the types listed below:
ECHO.- jar (to load jar file with compiled code)
ECHO.- support (to load all support jars: sources + documentation)
GOTO Finish
:: ------------------------------------------------------------------------------------
:: Load requested jar library into local maven repository
:: ------------------------------------------------------------------------------------
:Jar
ECHO.Loading jar file with compiled code into maven local repository ...
:: Jar with compiled source code
IF EXIST %LIBRARY_JAR% (
    mvn install:install-file^
    -DgroupId=%MAVEN_LIBRARY_GROUP_ID%^
    -DartifactId=%LIBRARY_NAME%^
    -Dversion=%LIBRARY_VERSION%^
    -Dfile=%LIBRARY_JAR%^
    -Dpackaging=jar^
    -DgeneratePom=true
)
GOTO Finish
:: ------------------------------------------------------------------------------------
:Support
ECHO.Loading support jar files (sources + documentation) into maven local repository ...
:: This actually only copies these jar files into local path
:: Jar with only java documentation
IF EXIST %LIBRARY_JAR_JAVADOC% (
    COPY %LIBRARY_JAR_JAVADOC% %MAVEN_LIBRARY_JAR_JAVADOC% /Y
)
:: Jar with sources
IF EXIST %LIBRARY_JAR_SOURCES% (
    COPY %LIBRARY_JAR_SOURCES% %MAVEN_LIBRARY_JAR_SOURCES% /Y
)
ECHO.Loading of support jar files successfully finished.
GOTO Finish
:: ------------------------------------------------------------------------------------
:UnknownParameter
ECHO.Unknown parameter type '%PARAM%'
ECHO.Use "-type" as parameter to list possible types of libraries to load into repository.
GOTO Finish
:: ------------------------------------------------------------------------------------
:Finish
:: ------------------------------------------------------------------------------------
ECHO ON