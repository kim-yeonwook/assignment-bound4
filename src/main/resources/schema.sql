-- Drop tables if they exist (reverse order of dependencies)
DROP TABLE IF EXISTS plan CASCADE;
DROP TABLE IF EXISTS "users" CASCADE;
DROP TABLE IF EXISTS project CASCADE;
DROP TABLE IF EXISTS project_task CASCADE;

CREATE TABLE plan (
        id BIGSERIAL PRIMARY KEY,
        name VARCHAR(255) NOT NULL UNIQUE,
        "limit" INT,
        description VARCHAR(1000)
);

CREATE TABLE "user" (
        id BIGSERIAL PRIMARY KEY,
        username VARCHAR(255) NOT NULL UNIQUE,
        plan_id BIGINT,
        CONSTRAINT fk_user_plan FOREIGN KEY (plan_id) REFERENCES plan(id)
);

CREATE TABLE project (
        id BIGSERIAL PRIMARY KEY,
        name VARCHAR(255) NOT NULL UNIQUE,
        manager_id BIGINT,
        status VARCHAR(50),
        deadline DATE,
        user_id BIGINT,
        CONSTRAINT fk_project_user FOREIGN KEY (user_id) REFERENCES "user"(id)
);

CREATE TABLE project_task (
        id BIGSERIAL PRIMARY KEY,
        name VARCHAR(255) NOT NULL,
        project_id BIGINT,
        CONSTRAINT fk_task_project FOREIGN KEY (project_id) REFERENCES project(id)
);

