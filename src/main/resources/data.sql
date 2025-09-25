INSERT INTO plan (id, name, "limit") VALUES (1, 'BASIC', 1);
INSERT INTO plan (id, name, "limit") VALUES (2, 'PRO', 5);

INSERT INTO "user" (id, username, plan_id) VALUES (1, 'basicuser', 1);
INSERT INTO "user" (id, username, plan_id) VALUES (2, 'prouser', 2);