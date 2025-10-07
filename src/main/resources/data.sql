insert into user_account (user_id, user_password, nickname, email, created_at, created_by, modified_at, modified_by)
values ('Kamilah', 'Lind', 'Murial', 'user@email.com',
        '2021-03-02 22:40:04', 'Kamilah', '2021-05-30 23:53:46', 'Kamilah');

insert into article (user_id, title, content, hashtag, created_by, created_at, modified_by, modified_at)
values ((select id from user_account where user_id = 'Kamilah'),
        'Quisque ut erat.',
        'Vestibulum quam sapien, varius ut, blandit non...',
        '#pink',
        'Kamilah', '2021-05-30 23:53:46', 'Murial', '2021-03-10 08:48:50');

insert into article_comment (article_id, user_id, content, created_by, created_at, modified_by, modified_at)
values ((select id from article where title = 'Quisque ut erat.'),
        (select id from user_account where user_id = 'Kamilah'),
        'Quisque id justo sit amet...',
        'Orv', '2021-03-02 22:40:04', 'Orv', '2021-04-27 15:38:09');