drop table if exists beer_order_shipment;

create table beer_order_shipment
(
    id                  varchar(36) not null primary key,
    beer_order_id       varchar(36) unique,
    tracking_number     varchar(50),
    created_date        datetime(6),
    last_modified_date datetime(6) default null,
    version             integer     default null,
    constraint beer_order_shipment_beer_order_fk foreign key (beer_order_id) references beer_order (id)
) engine = InnoDB;

alter table beer_order
add column beer_order_shipment_id varchar(36);

alter table beer_order
add constraint beer_order_shipment_fk foreign key (beer_order_shipment_id) references beer_order_shipment (id);