drop table if exists categroy;
drop table if exists beer_category;

create table category
(
    id  varchar(36) not null primary key,
    description varchar(50),
    created_date datetime(6),
    last_modified_date datetime(6) default null,
    version integer default null
) engine = InnoDB;

create table beer_category
(
    beer_id varchar(36) not null,
    category_id varchar(36) not null,
    primary key (beer_id, category_id),
    constraint beer_id_fk foreign key (beer_id) references beer (id),
    constraint category_id_fk foreign key (category_id) references category (id)
) engine = InnoDB;