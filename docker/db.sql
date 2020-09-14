--
-- PostgreSQL database dump
--

-- Dumped from database version 10.14 (Debian 10.14-1.pgdg90+1)
-- Dumped by pg_dump version 12.2

-- Started on 2020-09-13 21:00:06 IST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE testdb1;
--
-- TOC entry 2934 (class 1262 OID 24860)
-- Name: testdb1; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE testdb1 WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.utf8' LC_CTYPE = 'en_US.utf8';


ALTER DATABASE testdb1 OWNER TO postgres;

\connect testdb1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2 (class 3079 OID 24950)
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


--
-- TOC entry 2935 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


SET default_tablespace = '';

--
-- TOC entry 198 (class 1259 OID 24863)
-- Name: auctions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.auctions (
    id bigint NOT NULL,
    create_date timestamp without time zone,
    modify_date timestamp without time zone,
    base_price real,
    status integer,
    step_rate real,
    item_id bigint
);


ALTER TABLE public.auctions OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 24861)
-- Name: auctions_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.auctions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.auctions_id_seq OWNER TO postgres;

--
-- TOC entry 2936 (class 0 OID 0)
-- Dependencies: 197
-- Name: auctions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.auctions_id_seq OWNED BY public.auctions.id;


--
-- TOC entry 200 (class 1259 OID 24871)
-- Name: items; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.items (
    id bigint NOT NULL,
    create_date timestamp without time zone,
    modify_date timestamp without time zone,
    item_code character varying(255),
    name character varying(255),
    price real,
    seller_id bigint
);


ALTER TABLE public.items OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 24869)
-- Name: items_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.items_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.items_id_seq OWNER TO postgres;

--
-- TOC entry 2937 (class 0 OID 0)
-- Dependencies: 199
-- Name: items_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.items_id_seq OWNED BY public.items.id;


--
-- TOC entry 202 (class 1259 OID 24882)
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    id bigint NOT NULL,
    create_date timestamp without time zone,
    modify_date timestamp without time zone,
    name character varying(60)
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 24880)
-- Name: roles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.roles_id_seq OWNER TO postgres;

--
-- TOC entry 2938 (class 0 OID 0)
-- Dependencies: 201
-- Name: roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;


--
-- TOC entry 204 (class 1259 OID 24890)
-- Name: user_bids; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_bids (
    id bigint NOT NULL,
    create_date timestamp without time zone,
    modify_date timestamp without time zone,
    bid_amount real,
    auction_id bigint,
    buyer_id bigint
);


ALTER TABLE public.user_bids OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 24888)
-- Name: user_bids_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_bids_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_bids_id_seq OWNER TO postgres;

--
-- TOC entry 2939 (class 0 OID 0)
-- Dependencies: 203
-- Name: user_bids_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_bids_id_seq OWNED BY public.user_bids.id;


--
-- TOC entry 205 (class 1259 OID 24896)
-- Name: user_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_roles (
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE public.user_roles OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 24903)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    create_date timestamp without time zone,
    modify_date timestamp without time zone,
    email character varying(50),
    name character varying(50),
    password character varying(100),
    username character varying(50)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 24901)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 2940 (class 0 OID 0)
-- Dependencies: 206
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 2765 (class 2604 OID 24866)
-- Name: auctions id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.auctions ALTER COLUMN id SET DEFAULT nextval('public.auctions_id_seq'::regclass);


--
-- TOC entry 2766 (class 2604 OID 24874)
-- Name: items id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.items ALTER COLUMN id SET DEFAULT nextval('public.items_id_seq'::regclass);


--
-- TOC entry 2767 (class 2604 OID 24885)
-- Name: roles id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);


--
-- TOC entry 2768 (class 2604 OID 24893)
-- Name: user_bids id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_bids ALTER COLUMN id SET DEFAULT nextval('public.user_bids_id_seq'::regclass);


--
-- TOC entry 2769 (class 2604 OID 24906)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 2919 (class 0 OID 24863)
-- Dependencies: 198
-- Data for Name: auctions; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.auctions (id, create_date, modify_date, base_price, status, step_rate, item_id) VALUES (1, '2020-09-12 17:37:06.996902', '2020-09-12 17:37:06.996902', 6, 0, 1, 1);
INSERT INTO public.auctions (id, create_date, modify_date, base_price, status, step_rate, item_id) VALUES (2, '2020-09-12 17:37:19.562885', '2020-09-12 17:37:19.562885', 6, 0, 1, 2);


--
-- TOC entry 2921 (class 0 OID 24871)
-- Dependencies: 200
-- Data for Name: items; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.items (id, create_date, modify_date, item_code, name, price, seller_id) VALUES (1, '2020-09-12 17:35:07.739299', '2020-09-12 17:35:07.739299', '1e90e367-7a02-4d23-baba-0c51015210ca', 'Item_1', 2, 1);
INSERT INTO public.items (id, create_date, modify_date, item_code, name, price, seller_id) VALUES (2, '2020-09-12 17:35:33.519379', '2020-09-12 17:35:33.519379', 'b1a02fd2-e35d-49cd-9bcd-2cbd73e2b2b7', 'Item_1', 3, 1);


--
-- TOC entry 2923 (class 0 OID 24882)
-- Dependencies: 202
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.roles (id, create_date, modify_date, name) VALUES (1, NULL, NULL, 'ROLE_USER');
INSERT INTO public.roles (id, create_date, modify_date, name) VALUES (2, NULL, NULL, 'ROLE_PM');
INSERT INTO public.roles (id, create_date, modify_date, name) VALUES (3, NULL, NULL, 'ROLE_ADMIN');
INSERT INTO public.roles (id, create_date, modify_date, name) VALUES (4, NULL, NULL, 'ROLE_SELLER');
INSERT INTO public.roles (id, create_date, modify_date, name) VALUES (5, NULL, NULL, 'ROLE_BUYER');


--
-- TOC entry 2925 (class 0 OID 24890)
-- Dependencies: 204
-- Data for Name: user_bids; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_bids (id, create_date, modify_date, bid_amount, auction_id, buyer_id) VALUES (1, '2020-09-12 23:25:23.062', '2020-09-12 23:25:23.062', 5, 1, 1);


--
-- TOC entry 2926 (class 0 OID 24896)
-- Dependencies: 205
-- Data for Name: user_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO public.user_roles (user_id, role_id) VALUES (2, 1);


--
-- TOC entry 2928 (class 0 OID 24903)
-- Dependencies: 207
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users (id, create_date, modify_date, email, name, password, username) VALUES (1, '2020-09-12 23:00:38.851', '2020-09-12 23:00:38.851', 'seller@sample.com', 'seller', '$2a$10$Iq0aGURoDHM.aJlg/Zaho.MJSDVu903OQCTSXuVRKw/9cGJvDIeDq', 'seller');
INSERT INTO public.users (id, create_date, modify_date, email, name, password, username) VALUES (2, '2020-09-12 23:02:23.465', '2020-09-12 23:02:23.465', 'buyer@sample.com', 'buyer', '$2a$10$zIjs60RSVsxPFsmldHWedu0QAeJ7wTAVgBN0EyFLrVF6Ze4YF6ySy', 'buyer');


--
-- TOC entry 2941 (class 0 OID 0)
-- Dependencies: 197
-- Name: auctions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.auctions_id_seq', 1, false);


--
-- TOC entry 2942 (class 0 OID 0)
-- Dependencies: 199
-- Name: items_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.items_id_seq', 1, false);


--
-- TOC entry 2943 (class 0 OID 0)
-- Dependencies: 201
-- Name: roles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.roles_id_seq', 5, true);


--
-- TOC entry 2944 (class 0 OID 0)
-- Dependencies: 203
-- Name: user_bids_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_bids_id_seq', 1, true);


--
-- TOC entry 2945 (class 0 OID 0)
-- Dependencies: 206
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 2, true);


--
-- TOC entry 2771 (class 2606 OID 24868)
-- Name: auctions auctions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.auctions
    ADD CONSTRAINT auctions_pkey PRIMARY KEY (id);


--
-- TOC entry 2773 (class 2606 OID 24879)
-- Name: items items_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.items
    ADD CONSTRAINT items_pkey PRIMARY KEY (id);


--
-- TOC entry 2775 (class 2606 OID 24887)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- TOC entry 2783 (class 2606 OID 24914)
-- Name: users uk6dotkott2kjsp8vw4d0m25fb7; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);


--
-- TOC entry 2777 (class 2606 OID 24910)
-- Name: roles uk_nb4h0p6txrmfc0xbrd1kglp9t; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT uk_nb4h0p6txrmfc0xbrd1kglp9t UNIQUE (name);


--
-- TOC entry 2785 (class 2606 OID 24962)
-- Name: users uk_sx468g52bpetvlad2j9y0lptc; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_sx468g52bpetvlad2j9y0lptc UNIQUE (email);


--
-- TOC entry 2787 (class 2606 OID 24912)
-- Name: users ukr43af9ap4edm43mmtq01oddj6; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT ukr43af9ap4edm43mmtq01oddj6 UNIQUE (username);


--
-- TOC entry 2779 (class 2606 OID 24895)
-- Name: user_bids user_bids_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_bids
    ADD CONSTRAINT user_bids_pkey PRIMARY KEY (id);


--
-- TOC entry 2781 (class 2606 OID 24900)
-- Name: user_roles user_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id);


--
-- TOC entry 2789 (class 2606 OID 24908)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2794 (class 2606 OID 24935)
-- Name: user_bids fkf9go974ys6wv75as2p9sa23li; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_bids
    ADD CONSTRAINT fkf9go974ys6wv75as2p9sa23li FOREIGN KEY (id) REFERENCES public.auctions(id);


--
-- TOC entry 2795 (class 2606 OID 24940)
-- Name: user_roles fkh8ciramu9cc9q3qcqiv4ue8a6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT fkh8ciramu9cc9q3qcqiv4ue8a6 FOREIGN KEY (role_id) REFERENCES public.roles(id);


--
-- TOC entry 2796 (class 2606 OID 24945)
-- Name: user_roles fkhfh9dx7w3ubf1co1vdev94g3f; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_roles
    ADD CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 2792 (class 2606 OID 24925)
-- Name: user_bids fkp926hdy8pui41oer07kbeecm3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_bids
    ADD CONSTRAINT fkp926hdy8pui41oer07kbeecm3 FOREIGN KEY (auction_id) REFERENCES public.auctions(id);


--
-- TOC entry 2790 (class 2606 OID 24915)
-- Name: auctions fkpvixwpkykdb4kcebribl4ucwc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.auctions
    ADD CONSTRAINT fkpvixwpkykdb4kcebribl4ucwc FOREIGN KEY (item_id) REFERENCES public.items(id);


--
-- TOC entry 2793 (class 2606 OID 24930)
-- Name: user_bids fkqf9kxw2907mch3ble3baaos8k; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_bids
    ADD CONSTRAINT fkqf9kxw2907mch3ble3baaos8k FOREIGN KEY (buyer_id) REFERENCES public.users(id);


--
-- TOC entry 2791 (class 2606 OID 24920)
-- Name: items fksm9ro5ntn6yaav2m7ydato0fc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.items
    ADD CONSTRAINT fksm9ro5ntn6yaav2m7ydato0fc FOREIGN KEY (seller_id) REFERENCES public.users(id);


-- Completed on 2020-09-13 21:00:07 IST

--
-- PostgreSQL database dump complete
--

