        Test task for Plexsupply

https://plexsupply.com/

1. Написать тест кейсы для чекаута сайта.
2. Написать багрепорт, баги могут быть как гипотетические так и реальные (реальные отметить).
3. Выбрать самый важный тест кейс (по вашему мнению) и создать для него скрипт автотеста (инструменты на ваш выбор).


        Installation
1. Simply copy all files from repository in that place what you want! But for successful execution
on your computer must be installed Java and Maven!  


        Execution
1. Open project root folder.
2. Open terminal.
3. Run one of below CLI commands (without " ").


    CLI Commands
1. Command "mvn test" - standard run in default configuration (OS - Linux, Browser -Chrome);
2. Command "mvn test -DplatformAndBrowser=os_browser" - run with selected not default platform and browser
(present 4 variants: linux_chrome, windows_chrome, linux_firefox, windows_firefox) if you set some another value ;
test suite will fall by exception.
3. Command "mvn -Dmaven.surefire.debug test" run in default configuration with debugger. Yor IDE
must be configured!
4. Command like "mvn -Dmaven.surefire.debug test -DplatformAndBrowser=linux_chrome" run in debug mode with chosen
OS and Browser combination.