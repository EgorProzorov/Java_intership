CREATE TABLE hotel_booking (
                               booking_id SERIAL PRIMARY KEY,
                               user_id BIGINT,
                               hotel_id BIGINT,
                               start_date DATE,
                               end_date DATE,
                               FOREIGN KEY (user_id) REFERENCES clients_info(id)
);
