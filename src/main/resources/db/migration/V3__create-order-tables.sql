drop table if exists beer_order_line;
drop table if exists beer_order;

create table beer_order
(
    id                 varchar(36) not null,
    created_date       datetime(6),
    customer_ref       varchar(255),
    last_modified_date datetime(6),
    version            integer,
    customer_id        varchar(36),
    primary key (id),
    key customer_id (customer_id),
    constraint beer_order_customer_fk foreign key (customer_id) references customer (id)
) engine = InnoDB;

create table beer_order_line
(
    id                 varchar(36) not null,
    beer_id            varchar(36),
    beer_order_id      varchar(36),
    created_date       datetime(6),
    last_modified_date datetime(6),
    order_quantity     integer,
    quantity_allocated integer,
    version            integer,
    primary key (id),
    key beer_id (beer_id),
    key beer_order_id (beer_order_id),
    constraint beer_order_fk foreign key (beer_order_id) references beer_order (id),
    constraint beer_fk foreign key (beer_id) references beer (id)
) engine = InnoDB;

