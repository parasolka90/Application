start runcrud.bat
if "%ERRORLEVEL%" == "0" goto runexplorer
echo.
echo RUN CRUD has errors - breaking work
goto fail

:runexplorer
start chrome.exe http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "1" goto stoptomcat
goto end
heroku
:stoptomcat
call %CATALINA_HOME%\bin\shutdown.bat

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.