Проект написано мовою JAVA версії 11. 

Для виконання завдання використовувався SpringBoot версії 2.3.5(Всі допоміжні модулі теж даної версії).

Для роботи з базою даних використовується **JdbcTemplate**. Для Rest запитів використовується **RestTemplate**

База даних Postgres версії 12. 
Дамп бази даних прикріплено до репозиторію з назвою **pg**.
Користувач,база даних встановлено по замовчуванню **postgres**. Пароль до бази - **1**.

Логін і Пароль для відкриття сторінки /users - **adm1n**.

**Як працює:**
Кожного разу коли відкривається сторінка users йде запит по посиланню зі завдання, на отримання коментарів. Після чого ці дані записуються в базу даних. І вже після запису, дані витягаються з бази даних та відображаються на сторінці.

Щоб відкрити сторінку /users можна за посиланням(При зміні порту в application.properties замініть '8080' на вказаний вами порт): http://localhost:8080/users
