-- ============================================================
-- Sample customer data
-- 5 INDIVIDUAL customers: firstName + lastName set, companyName NULL
-- 5 BUSINESS customers: companyName set, firstName + lastName NULL
-- ============================================================

-- INDIVIDUAL customers
INSERT INTO customers (first_name, last_name, company_name, birth_date, vat_number, email, phone_number, street, city, postal_code, region, country_code, type, created_at, updated_at)
VALUES ('Alice', 'Johnson', NULL, '1990-04-15', 'VAT-IND-00001', 'alice.johnson@example.com', '+1-202-555-0101', '12 Maple Avenue', 'New York', '10001', 'New York', 'US', 'INDIVIDUAL', '2024-01-10T08:00:00Z', '2024-06-15T10:30:00Z');

INSERT INTO customers (first_name, last_name, company_name, birth_date, vat_number, email, phone_number, street, city, postal_code, region, country_code, type, created_at, updated_at)
VALUES ('Carlos', 'Fernandez', NULL, '1985-09-23', 'VAT-IND-00002', 'carlos.fernandez@example.com', '+34-91-555-0202', 'Calle Gran Via 45', 'Madrid', '28013', 'Community of Madrid', 'ES', 'INDIVIDUAL', '2024-02-18T09:15:00Z', '2024-07-20T14:00:00Z');

INSERT INTO customers (first_name, last_name, company_name, birth_date, vat_number, email, phone_number, street, city, postal_code, region, country_code, type, created_at, updated_at)
VALUES ('Sophie', 'Müller', NULL, '1992-12-01', 'VAT-IND-00003', 'sophie.mueller@example.com', '+49-30-555-0303', 'Unter den Linden 10', 'Berlin', '10117', 'Berlin', 'DE', 'INDIVIDUAL', '2024-03-05T07:45:00Z', '2024-08-01T11:20:00Z');

INSERT INTO customers (first_name, last_name, company_name, birth_date, vat_number, email, phone_number, street, city, postal_code, region, country_code, type, created_at, updated_at)
VALUES ('Liam', 'O''Brien', NULL, '1988-07-30', 'VAT-IND-00004', 'liam.obrien@example.com', '+353-1-555-0404', '7 St Stephen''s Green', 'Dublin', 'D02 FY00', 'Leinster', 'IE', 'INDIVIDUAL', '2024-04-12T06:30:00Z', '2024-09-10T16:45:00Z');

INSERT INTO customers (first_name, last_name, company_name, birth_date, vat_number, email, phone_number, street, city, postal_code, region, country_code, type, created_at, updated_at)
VALUES ('Yuki', 'Tanaka', NULL, '1995-03-17', 'VAT-IND-00005', 'yuki.tanaka@example.com', '+81-3-555-0505', '2-1 Marunouchi', 'Tokyo', '100-0005', 'Tokyo', 'JP', 'INDIVIDUAL', '2024-05-22T10:00:00Z', '2024-10-03T09:15:00Z');

-- BUSINESS customers
INSERT INTO customers (first_name, last_name, company_name, birth_date, vat_number, email, phone_number, street, city, postal_code, region, country_code, type, created_at, updated_at)
VALUES (NULL, NULL, 'Acme Corp', NULL, 'VAT-BUS-00001', 'contact@acmecorp.com', '+1-800-555-0601', '100 Industrial Blvd', 'Chicago', '60601', 'Illinois', 'US', 'BUSINESS', '2023-06-01T08:00:00Z', '2024-01-15T12:00:00Z');

INSERT INTO customers (first_name, last_name, company_name, birth_date, vat_number, email, phone_number, street, city, postal_code, region, country_code, type, created_at, updated_at)
VALUES (NULL, NULL, 'Nexus Technologies GmbH', NULL, 'VAT-BUS-00002', 'info@nexus-tech.de', '+49-89-555-0702', 'Maximilianstraße 33', 'Munich', '80539', 'Bavaria', 'DE', 'BUSINESS', '2023-07-14T09:30:00Z', '2024-02-20T13:45:00Z');

INSERT INTO customers (first_name, last_name, company_name, birth_date, vat_number, email, phone_number, street, city, postal_code, region, country_code, type, created_at, updated_at)
VALUES (NULL, NULL, 'Blue Horizon S.A.', NULL, 'VAT-BUS-00003', 'hello@bluehorizon.fr', '+33-1-555-0803', '15 Rue de Rivoli', 'Paris', '75001', 'Île-de-France', 'FR', 'BUSINESS', '2023-08-25T11:00:00Z', '2024-03-05T15:30:00Z');

INSERT INTO customers (first_name, last_name, company_name, birth_date, vat_number, email, phone_number, street, city, postal_code, region, country_code, type, created_at, updated_at)
VALUES (NULL, NULL, 'Summit Logistics Ltd', NULL, 'VAT-BUS-00004', 'ops@summitlogistics.co.uk', '+44-20-555-0904', '50 Canary Wharf', 'London', 'E14 5AB', 'England', 'GB', 'BUSINESS', '2023-09-30T14:00:00Z', '2024-04-18T10:00:00Z');

INSERT INTO customers (first_name, last_name, company_name, birth_date, vat_number, email, phone_number, street, city, postal_code, region, country_code, type, created_at, updated_at)
VALUES (NULL, NULL, 'Orion Digital S.r.l.', NULL, 'VAT-BUS-00005', 'admin@oriondigital.it', '+39-02-555-1005', 'Via Montenapoleone 8', 'Milan', '20121', 'Lombardy', 'IT', 'BUSINESS', '2023-10-12T13:15:00Z', '2024-05-22T08:45:00Z');

