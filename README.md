# 🏛️ HallHub – Online Hall Booking System

Java (Servlets + **JDBC**) · MySQL · HTML/CSS/JavaScript

## Features
- User registration & login (salted SHA-256, session based)
- Search halls by date, time and guests with **live availability**
- **Instant quote**: hourly rate + weekend surcharge (+20%) + coupon + 18% GST
- Double-booking protection (row lock + overlap check in a JDBC transaction)
- **Payments**: UPI, Card (Luhn validated, only last 4 digits stored), Net Banking, Wallet, Pay at venue
- **15-minute slot hold** with countdown – unpaid slots auto-release
- Cancel booking (payment marked REFUNDED), printable receipt, dark mode, confetti 🎉

## Run it
1. Install JDK 11+, Maven, MySQL 8.
2. Create the database: `mysql -u root -p < database/schema.sql`
3. Set credentials (defaults: root / root):
   `export DB_USER=root DB_PASS=yourpassword` (Windows: `set DB_USER=root`)
4. Start: `mvn jetty:run` and open http://localhost:8080
   (or `mvn clean package` and drop `target/hall-booking.war` into Tomcat 9)

Coupons: `WELCOME10`, `FEST20`, `STUDENT15`. Test card: `4242 4242 4242 4242`.

## Structure
```
database/schema.sql            tables + sample halls/coupons
src/main/java/com/hallbooking/
  util/   DBConnection, Api
  dao/    UserDAO, HallDAO, BookingDAO, PaymentDAO   (all JDBC)
  servlet/ AuthServlet, HallServlet, BookingServlet, PaymentServlet
src/main/webapp/  index, auth, halls, book, payment, bookings (.html) + css/js
```

## Notes
The payment step is a simulated gateway. To accept real money, call Razorpay/Stripe in `PaymentDAO.pay()`.
Use BCrypt for passwords in production.
