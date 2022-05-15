create table `matchs` (
    id integer not null auto_increment,
    away_team_goal integer not null,
    date_match date not null,
    home_team_goal integer not null,
    away_team_id integer not null,
    home_team_id integer not null,
    primary key (id))
    engine=InnoDB

create table teams (
    id integer not null auto_increment,
    conceded integer,
    date_last_game date,
    name_team varchar(45) not null,
    points integer,
    scored integer,
    primary key (id))
     engine=InnoDB

create index id_away_idx on `matchs` (away_team_id)

create index id_home_idx on `matchs` (home_team_id)

alter table `matchs`
    add constraint FKevcwkstwugln1m8k2qlha7mvx
    foreign key (away_team_id) references teams (id)

alter table `matchs`
    add constraint FK8d2l95nq5hasetv2ptf2tmllu
    foreign key (home_team_id) references teams (id)