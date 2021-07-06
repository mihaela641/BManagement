-- 01.06.2021; Initial DB creation --
-- ############################### --

CREATE TABLE public.sample_entities (
	id int4 NOT NULL,
	name varchar(25) NOT NULL
);
COMMENT ON TABLE public.sample_entities IS 'Sample entities';

-- Column comments

COMMENT ON COLUMN public.sample_entities.id IS 'Identificator';


-- 01.06.21; products table creation --
-- ################################# --

CREATE TABLE public.products (
	id serial NOT NULL,
	"name" varchar NOT NULL,
	type_id int NOT NULL,
	price float4 NOT NULL,
	date_added date NOT NULL,
	manufacturer_id int NOT null,
	CONSTRAINT products_pk PRIMARY KEY (id)
);

COMMENT ON TABLE public.products IS 'Products';
COMMENT ON COLUMN public.products.id IS 'Identification';
COMMENT ON COLUMN public.products."name" IS 'Name of the product';
COMMENT ON COLUMN public.products.type_id IS 'Foreign key references to types';
COMMENT ON COLUMN public.products.price IS 'Price of the product';
COMMENT ON COLUMN public.products.date_added IS 'Date of adding';
COMMENT ON COLUMN public.products.manufacturer_id IS 'Foreign key references manufacturers';

-- 01.06.21; shops table creation --
-- ################################ --

CREATE TABLE public.shops (
    id serial NOT NULL,
    "name" varchar(50) NOT NULL,
    address_id int NOT NULL,
    phone_number varchar(15) NOT NULL,
    manager_id int NOT NULL,
    status int NOT null,
    CONSTRAINT shops_pk PRIMARY KEY (id)
);

 -- Column comments

 COMMENT ON COLUMN public.shops.id IS 'Shop id';
COMMENT ON COLUMN public.shops."name" IS 'Shop name';
COMMENT ON COLUMN public.shops.address_id IS 'Connection to tab address';
COMMENT ON COLUMN public.shops.phone_number IS 'Contains a phone number';
COMMENT ON COLUMN public.shops.manager_id IS 'Connection to tab employee';
COMMENT ON COLUMN public.shops.status IS 'Working/temporarily closed/closed';

-- 01.06.21; products_shops table creation --
-- ######################################### --

CREATE TABLE public.products_shops (
	product_id int NOT NULL,
	shop_id int NOT NULL,
	quantity int NOT NULL,
	CONSTRAINT products_shops_pk PRIMARY KEY (product_id,shop_id)
);

-- 01.06.21; employees table creation --
-- ################################# --

CREATE TABLE public.employees (
	id serial NOT NULL,
	"name" varchar NOT NULL,
	birth_date date NOT NULL,
	position_id int NOT NULL,
	country_id int NOT NULL,
	salary float4 NOT NULL,
	shop_id int NOT NULL,
	CONSTRAINT employees_pk PRIMARY KEY (id)
);
COMMENT ON TABLE public.employees IS 'Employees';

-- Column comments

COMMENT ON COLUMN public.employees.id IS 'identification';
COMMENT ON COLUMN public.employees."name" IS 'employee name';
COMMENT ON COLUMN public.employees.birth_date IS 'employee birth date';
COMMENT ON COLUMN public.employees.position_id IS 'foreign key references positions';
COMMENT ON COLUMN public.employees.country_id IS 'foreign key references countries';
COMMENT ON COLUMN public.employees.salary IS 'salary of the employee';
COMMENT ON COLUMN public.employees.shop_id IS 'foreign key references shops';


-- 01.06.21; tags table creation --
-- ############################# --

CREATE TABLE public.tags (
	id serial NOT NULL,
	code varchar(10) NOT NULL,
	"name" varchar NOT NULL,
	CONSTRAINT tags_pk PRIMARY KEY (id)
);
COMMENT ON TABLE public.tags IS 'Tags';

-- Column comments

COMMENT ON COLUMN public.tags.id IS 'Identification';
COMMENT ON COLUMN public.tags.code IS 'Tag code';
COMMENT ON COLUMN public.tags."name" IS 'Tag name';


-- 01.06.21; products_tags table creation --
-- ###################################### --

CREATE TABLE public.products_tags (
	product_id int NOT NULL,
	tag_id int NOT NULL,
	CONSTRAINT products_tags_pk PRIMARY KEY (tag_id,product_id)
);
COMMENT ON TABLE public.products_tags IS 'Mapping table';

-- Column comments

COMMENT ON COLUMN public.products_tags.product_id IS 'Foreign key references projects';
COMMENT ON COLUMN public.products_tags.tag_id IS 'Foreign key references tags';

-- 02.06.21; manufacturers table creation --
-- ###################################### --

CREATE TABLE public.manufacturers (
	id serial NOT NULL,
	"name" varchar(20) NOT NULL,
	address_id int NOT NULL,
	"date of foundation" date NOT NULL,
	country_id int NOT NULL,
	"manufacturer description" varchar(100) NULL,
	CONSTRAINT manufacturers_pk PRIMARY KEY (id)
);
COMMENT ON TABLE public.manufacturers IS 'Countries';

-- Column comments

COMMENT ON COLUMN public.manufacturers.id IS 'Manufacturer id';
COMMENT ON COLUMN public.manufacturers."name" IS 'Manufacturer name';
COMMENT ON COLUMN public.manufacturers.address_id IS 'Foreign key references to addresses';
COMMENT ON COLUMN public.manufacturers."date of foundation" IS 'Date of foundation';
COMMENT ON COLUMN public.manufacturers.country_id IS 'Foreign key references to countries';
COMMENT ON COLUMN public.manufacturers."manufacturer description" IS 'Description ';

-- 02.06.21; countries table creation --
-- ###################################### --

CREATE TABLE public.countries (
	id serial NOT NULL,
	code varchar(15) NOT NULL,
	"name" varchar(20) NOT NULL,
	CONSTRAINT countries_pk PRIMARY KEY (id)
);

-- Column comments

COMMENT ON COLUMN public.countries.id IS 'Country id';
COMMENT ON COLUMN public.countries.code IS 'Country code';
COMMENT ON COLUMN public.countries."name" IS 'Country name';

-- 02.06.21; addresses table creation --
-- ###################################### --

CREATE TABLE public.addresses (
	id serial NOT NULL,
	street varchar(150) NOT NULL,
	country_id int NOT NULL,
	CONSTRAINT addresses_pk PRIMARY KEY (id)
);

-- Column comments

COMMENT ON COLUMN public.addresses.id IS 'Address id';
COMMENT ON COLUMN public.addresses.street IS 'Information for street';
COMMENT ON COLUMN public.addresses.country_id IS 'Foreign key references to countries';

-- 02.06.21; positions table creation --
-- ###################################### --

CREATE TABLE public.positions (
	id serial NOT NULL,
	code varchar(20) NOT NULL,
	"name" varchar(20) NOT NULL,
	description varchar(100) NULL,
	CONSTRAINT positions_pk PRIMARY KEY (id)
);

-- Column comments

COMMENT ON COLUMN public.positions.id IS 'Position id';
COMMENT ON COLUMN public.positions.code IS 'Position code';
COMMENT ON COLUMN public.positions."name" IS 'Position name';

-- 02.06.21; types table creation --
-- ###################################### --

CREATE TABLE public."types" (
	id serial NOT NULL,
	code varchar(20) NOT NULL,
	"name" varchar(50) NOT NULL,
	description varchar(100) NULL,
	main_type_id int NULL,
	CONSTRAINT types_pk PRIMARY KEY (id)

);

-- Column comments

COMMENT ON COLUMN public."types".id IS 'Types id';
COMMENT ON COLUMN public."types".code IS 'Code';
COMMENT ON COLUMN public."types"."name" IS 'Type name';
COMMENT ON COLUMN public."types".description IS 'Type full description';

-- 03.06.21; creating table relations --
-- ###################################### --

ALTER TABLE public."employees" ADD CONSTRAINT employees_positions_fk FOREIGN KEY (position_id) REFERENCES public.positions(id);

ALTER TABLE public."employees" ADD CONSTRAINT employees_countries_fk FOREIGN KEY (country_id) REFERENCES public.countries(id);

ALTER TABLE public."employees" ADD CONSTRAINT employees_shops_fk FOREIGN KEY (shop_id) REFERENCES public.shops(id);

ALTER TABLE public."addresses" ADD CONSTRAINT addresses_countries_fk FOREIGN KEY (country_id) REFERENCES public.countries(id);

ALTER TABLE public."shops" ADD CONSTRAINT shops_addresses_fk FOREIGN KEY (address_id) REFERENCES public.addresses(id);

ALTER TABLE public."types" ADD CONSTRAINT types_fk FOREIGN KEY (main_type_id) REFERENCES public.types(id);

ALTER TABLE public."manufacturers" ADD CONSTRAINT manufacturers_addresses_fk FOREIGN KEY (address_id) REFERENCES public.addresses(id);

ALTER TABLE public."products" ADD CONSTRAINT products_manufacturers_fk FOREIGN KEY (manufacturer_id) REFERENCES public.manufacturers(id);

ALTER TABLE public."products" ADD CONSTRAINT products_types_fk FOREIGN KEY (type_id) REFERENCES public.types(id);

ALTER TABLE public."products_shops" ADD CONSTRAINT products_shops_fk FOREIGN KEY (product_id) REFERENCES public.products(id);

ALTER TABLE public."products_shops" ADD CONSTRAINT shops_products_fk FOREIGN KEY (shop_id) REFERENCES public.shops(id);

ALTER TABLE public."products_tags" ADD CONSTRAINT products_tags_fk FOREIGN KEY (product_id) REFERENCES public.products(id);

ALTER TABLE public."products_tags" ADD CONSTRAINT tags_products_fk FOREIGN KEY (tag_id) REFERENCES public.tags(id);