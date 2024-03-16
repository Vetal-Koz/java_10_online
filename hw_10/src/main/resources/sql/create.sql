CREATE TABLE cars(
                     car_id serial PRIMARY KEY,
                     car_name varchar(255) NOT NULL,
                     year_of_creating integer NOT NULL
);


CREATE TABLE garages(
    garage_id serial PRIMARY KEY,
    garage_name varchar(255) NOT NULL
);


CREATE TABLE car_garage(
    car_id bigint not null,
    garage_id bigint not null,
    PRIMARY KEY (car_id, garage_id),
    FOREIGN KEY (car_id) REFERENCES cars(car_id),
    FOREIGN KEY (garage_id) REFERENCES garages(garage_id)
)

