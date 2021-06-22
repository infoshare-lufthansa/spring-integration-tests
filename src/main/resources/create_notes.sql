insert into tag (id, used, value)
values (1, 1, 'first'),
       (2, 1, 'second');

insert into note (id, value)
values (3, 'First note value'),
       (4, 'Second note value');

insert into note_tags(tags_id, notes_id)
values (1, 3), (1, 4), (2, 4);

insert into comment (id, value, note_id)
values (5, 'Comment 1', 3);