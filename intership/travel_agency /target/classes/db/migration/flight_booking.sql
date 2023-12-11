CREATE TABLE flight_booking (
                        booking_id SERIAL PRIMARY KEY,
                        user_id BIGINT,
                        flight_id BIGINT,
                        booking_date_and_time TIMESTAMP,
                        FOREIGN KEY (user_id) REFERENCES clients_info (id)
);