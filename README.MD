
#Задание
Круїзна компанія	Компанія має декілька круїзних лайнерів. Лайнер має свою пасажиромісткість, маршрут, кількість відвідуваних портів, початок та кінець круїзу, персонал.
Клієнт вибирає круїз із каталогу, залишає заявку на нього, за наявності вільних місць, завантажує скан копії документів та сплачує повну вартість круїзу, після того, як адміністратор круїзної компанії підтвердив можливість туру.
Реалізувати можливість вибору круїзу за датою та тривалістю.
Адміністратор круїзної компанії керує записами круїзних лайнерів та заявками клієнтів, змінюючи їх статус на "сплачена".
Заявка автоматично становиться "завершеною" по завершенні круїзу.

#Для Установки :

    1) Скачайте проект : git clone https://github.com/OzzKoup/finalProject.git
    
#Для Запуска :

    1) Запустите файлы : src/main/resources/schema.sql и data.sql
    2) Зайти в корневую папку Tomcat /bin
    3) Запустить startup.bin
    4) Соберите проект сборщиком типа Maven
    4) Перейти по стандартному адресу localhost:8080/CruiseCompany