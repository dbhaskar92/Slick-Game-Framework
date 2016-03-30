@ECHO OFF

setlocal enableextensions enabledelayedexpansion

::
:: Author: Dhananjay Bhaskar
:: Last Modified: Dec 13, 2013
:: Description: Downloads JDK (jdk-7u45-windows-x64.exe) in ..\bin\ directory from
:: 				http://www.oracle.com/technetwork/java/javase/downloads/index.html using WGET,
:: 				performs silent installation, compiles and launches game.
:: Works on x86 architecture (tested on Windows 8)
::

FOR /F "tokens=* USEBACKQ" %%F IN (`dir "C:\Program Files\Java" /b`) DO ( SET JDKDir=%%F )
set JDKDir=%JDKDir:~0,-1%
SET FileName="C:\Program Files\Java\%JDKDir%\bin\javac.exe"

IF EXIST %FileName% GOTO FILE_EXISTS
	:: Install JDK and JRE
	
	ECHO. & ECHO Getting JDK ... & ECHO.
	..\bin\wget.exe --no-check-certificate --no-cookies -O ..\bin\jdk-win-x64.exe --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com" http://download.oracle.com/otn-pub/java/jdk/7u45-b18/jdk-7u45-windows-x64.exe
	
	ECHO. & ECHO Installing JDK ... & ECHO.
	Start /WAIT ..\bin\jdk-win-x64.exe /s ADDLOCAL="ToolsFeature,SourceFeature,PublicjreFeature"

	ECHO. & ECHO Setting Environment ... & ECHO.
	DEL ..\bin\jdk-win-x64.exe
	FOR /F "tokens=* USEBACKQ" %%F IN (`dir "C:\Program Files\Java" /b`) DO ( SET InstallDir=%%F )
	set InstallDir=%InstallDir:~0,-1%
	SET COMPILE_BIN="C:\Program Files\Java\%InstallDir%\bin\javac.exe"
	SET EXEC_BIN="C:\Program Files\Java\%InstallDir%\bin\java.exe"
	GOTO COMPILE_GAME
EXIT

:FILE_EXISTS
SET COMPILE_BIN="C:\Program Files\Java\%JDKDir%\bin\javac.exe"
SET EXEC_BIN="C:\Program Files\Java\%JDKDir%\bin\java.exe"
GOTO COMPILE_GAME
EXIT

:COMPILE_GAME
ECHO. & ECHO Compiling Game ... & ECHO.
!COMPILE_BIN! -version
!EXEC_BIN! -version
SET CLASSPATH=.
FOR /F "tokens=* USEBACKQ" %%F IN (`dir ..\libs\*.jar /s /b`) DO ( 
	SET CLASSPATH=!CLASSPATH!;%%F
)
!COMPILE_BIN! Slick2dGame.java Ball.java BrickWall.java
ECHO. & ECHO Running Game ... & ECHO.
!EXEC_BIN! -Djava.library.path=..\libs\natives\ Slick2dGame