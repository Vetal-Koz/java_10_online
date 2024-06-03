INSERT INTO cars (description, body_type, brand, generation, model)
VALUES
    (
        'The Volkswagen Golf Mk4 is a compact hatchback that offers an ideal blend of performance, efficiency, and practicality for city driving.
        Launched in the late 1990s, the Mk4 Golf is renowned for its robust build quality, stylish design, and advanced features for its time.
        The Mk4 Golf comes with a range of engine options, from economical four-cylinder engines to more powerful turbocharged variants, catering to
        different driving preferences and needs. Its compact size makes it easy to maneuver through crowded urban streets, while the spacious interior
        provides ample room for passengers and cargo. Safety features such as airbags, ABS, and stability control were some of the pioneering
        implementations in this model, setting new standards in its class. The Mk4 Golf’s comfortable ride quality, coupled with precise handling and
        responsive steering, ensures a pleasant driving experience. The car also boasts a versatile and practical design, with rear seats that can be
        folded down to increase cargo space, making it suitable for both daily commutes and weekend getaways. The Volkswagen Golf Mk4 is a testament
        to Volkswagen’s commitment to engineering excellence and continues to be a popular choice among car enthusiasts and practical drivers alike,
        thanks to its reliability, timeless design, and overall value.',
        'HATCHBACK',
        'VOLKSWAGEN',
        'Mk4',
        'Golf'
    );


INSERT INTO car_images (image_url, main_image) VALUES
('https://a.d-cd.net/4073aaes-1920.jpg', true),
('https://a.d-cd.net/f63aaes-1920.jpg', false),
('https://a.d-cd.net/16b3aaes-1920.jpg', false),
('https://a.d-cd.net/d473aaes-1920.jpg', false);


INSERT INTO car_engine (cylinders, displacement, fuel_economy, horsepower, torque, type_of_fuel) VALUES
-- Petrol Engines
(4, 1.4, 6.5, 75, 126, 'PETROL'),
(4, 1.6, 7.2, 102, 148, 'PETROL'),
(4, 1.8, 8.0, 125, 170, 'PETROL'),
(4, 1.8, 8.9, 150, 210, 'PETROL'),
(6, 2.8, 10.5, 204, 270, 'PETROL'),
(6, 3.2, 10.8, 241, 320, 'PETROL'),
-- Diesel Engines
(4, 1.9, 5.0, 68, 133, 'DIESEL'),
(4, 1.9, 5.5, 90, 210, 'DIESEL'),
(4, 1.9, 5.6, 100, 240, 'DIESEL'),
(4, 1.9, 5.8, 110, 235, 'DIESEL'),
(4, 1.9, 5.9, 115, 285, 'DIESEL');


INSERT INTO car_variant (color, drivetrain, kilometrage, number_of_doors, number_of_seats, price, safety_rating, transmission, year, car_engine_id, car_id) VALUES
('Red', 'FWD', 150000, 3, 5, 3500.00, 4, 'MANUAL', 2002, 1,1),
('Blue', 'FWD', 120000, 5, 5, 4500.00, 5, 'AUTOMATIC', 2003, 2,1),
('Black', 'AWD', 100000, 5, 5, 5000.00, 5, 'MANUAL', 2004,5,1),
('White', 'FWD', 180000, 5, 5, 3200.00, 4, 'MANUAL', 2001,6, 1),
('Silver', 'FWD', 90000, 5, 5, 5200.00, 5, 'AUTOMATIC', 2003, 7, 1);


INSERT INTO thumbnails (car_id, car_image_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4);