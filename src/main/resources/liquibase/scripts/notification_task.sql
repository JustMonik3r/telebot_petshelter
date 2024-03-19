 create table cat
 (
     id         bigserial not null primary key,
     name       varchar(255),
     age        integer
         constraint check_age check (age > 0),
     is_healthy boolean,
     vaccinations integer
 );

  create table dog
  (
      id         bigserial not null primary key,
      name       varchar(255),
      age        integer
          constraint check_age check (age > 0),
      is_healthy boolean,
      vaccinations integer
  );

  create table pet_owners
  (
    telegram_id bigint not null primary key,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    phone_number bigint not null,
    email varchar(255) not null,
    took_animal boolean
  );

   create table cat_shelter
   (
       id            bigserial not null primary key,
       about_shelter varchar(255),
       cat_id        bigint,
       location      varchar(255),
       name          varchar(255),
       safety_measures    varchar(700),
       security      varchar(255),
       timetable     varchar(255)
   );

    create table dog_shelter
    (
        id            bigserial not null primary key,
        about_shelter varchar(255),
        dog_id        bigint,
        location      varchar(255),
        name          varchar(255),
        safety_measures    varchar(700),
        security      varchar(255),
        timetable     varchar(255)
    );

   INSERT INTO cat_shelter (about_shelter, location, name, safety_measures, security, timetable)
   VALUES ('Наша организация предоставляет временное убежище для бездомных, потерянных или брошенных кошек. В приюте заботятся о животных, предоставляют им пищу, ветеринарную помощь, места для проживания и поиск новых домов.',
   'Мы находимся в городе Котгород, ул. Котовая, 1. Вы нас найдёте легко по вывеске "Кошкин дом". Наш телефон +7(987)654-32-10.',
   'Кошкин дом',
   'Общие рекомендации о технике безопасности на территории приюта: Следуйте указаниям персонала приюта и соблюдайте правила, чтобы обеспечить безопасность как для себя, так и для животных. Прикармливайте или касайтесь животных только с разрешения сотрудников приюта. Не проникайте в запрещенные зоны без разрешения. Будьте осторожны, особенно вблизи кошачьих вольеров, чтобы не испугать или не причинить вред животным. Если у вас есть аллергия на кошек, убедитесь, что вы принимаете соответствующие меры предосторожности, например, принимайте антигистаминные препараты или носите маску. Если вы замечаете какую-либо проблему или поведение, вызывающее беспокойство, сообщите об этом персоналу приюта.',
   'Чтобы оформить пропуск на машину, вам нужно связаться с охраной  по номеру +7(987)654-32-10.',
   'Наш приют открыт для посещения каждый день с 10:00 до 17:00. Будем рады видеть вас у нас.');

   INSERT INTO dog_shelter (about_shelter, location, name, safety_measures, security, timetable)
   VALUES ('Наша организация предоставляет временное убежище для бездомных, потерянных или брошенных собак. В приюте заботятся о животных, предоставляют им пищу, ветеринарную помощь, места для проживания и поиск новых домов.',
   'Мы находимся в городе Собакобург, ул. 2-ого Лая, 1. Вы нас найдёте легко по вывеске "Собачий рай". Наш телефон +7(987)654-32-10.',
   'Собачий рай',
   'Общие рекомендации о технике безопасности на территории приюта: Следуйте указаниям персонала приюта и соблюдайте правила, чтобы обеспечить безопасность как для себя, так и для животных. Прикармливайте или касайтесь животных только с разрешения сотрудников приюта. Не проникайте в запрещенные зоны без разрешения. Будьте осторожны, особенно вблизи собачих вольеров, чтобы не испугать или не причинить вред животным. Если у вас есть аллергия на собак, убедитесь, что вы принимаете соответствующие меры предосторожности, например, принимайте антигистаминные препараты или носите маску. Если вы замечаете какую-либо проблему или поведение, вызывающее беспокойство, сообщите об этом персоналу приюта.',
   'Чтобы оформить пропуск на машину, вам нужно связаться с охраной по номеру +7(987)654-32-10.',
   'Наш приют открыт для посещения каждый день с 10:00 до 17:00. Будем рады видеть вас у нас.');
