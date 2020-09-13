
drop table if exists auctions CASCADE;
drop table if exists items CASCADE;
drop table if exists roles CASCADE;
drop table if exists user_bids CASCADE;
drop table if exists user_roles CASCADE;
drop table if exists users CASCADE;
--
-- TOC entry 198 (class 1259 OID 24863)
-- Name: auctions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE auctions (
    id bigint NOT NULL,
    create_date timestamp without time zone,
    modify_date timestamp without time zone,
    base_price real,
    status integer,
    step_rate real,
    item_id bigint
);

--
-- TOC entry 197 (class 1259 OID 24861)
-- Name: auctions_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE auctions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- TOC entry 200 (class 1259 OID 24871)
-- Name: items; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE items (
    id bigint NOT NULL,
    create_date timestamp without time zone,
    modify_date timestamp without time zone,
    item_code character varying(255),
    name character varying(255),
    price real,
    seller_id bigint
);

--
-- TOC entry 199 (class 1259 OID 24869)
-- Name: items_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE items_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 202 (class 1259 OID 24882)
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE roles (
    id bigint NOT NULL,
    create_date timestamp without time zone,
    modify_date timestamp without time zone,
    name character varying(60)
);


--
-- TOC entry 201 (class 1259 OID 24880)
-- Name: roles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- TOC entry 204 (class 1259 OID 24890)
-- Name: user_bids; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE user_bids (
    id bigint NOT NULL,
    create_date timestamp without time zone,
    modify_date timestamp without time zone,
    bid_amount real,
    auction_id bigint,
    buyer_id bigint
);

--
-- TOC entry 203 (class 1259 OID 24888)
-- Name: user_bids_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE user_bids_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 205 (class 1259 OID 24896)
-- Name: user_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE user_roles (
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);

--
-- TOC entry 207 (class 1259 OID 24903)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE users (
    id bigint NOT NULL,
    create_date timestamp without time zone,
    modify_date timestamp without time zone,
    email character varying(50),
    name character varying(50),
    password character varying(100),
    username character varying(50)
);

--
-- TOC entry 206 (class 1259 OID 24901)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;





--
-- TOC entry 2919 (class 0 OID 24863)
-- Dependencies: 198
-- Data for Name: auctions; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO auctions (id, create_date, modify_date, base_price, status, step_rate, item_id) VALUES (1, '2020-09-12 17:37:06.996902', '2020-09-12 17:37:06.996902', 6, 0, 1, 1);
INSERT INTO auctions (id, create_date, modify_date, base_price, status, step_rate, item_id) VALUES (2, '2020-09-12 17:37:19.562885', '2020-09-12 17:37:19.562885', 6, 0, 1, 2);


--
-- TOC entry 2921 (class 0 OID 24871)
-- Dependencies: 200
-- Data for Name: items; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO items (id, create_date, modify_date, item_code, name, price, seller_id) VALUES (1, '2020-09-12 17:35:07.739299', '2020-09-12 17:35:07.739299', '1e90e367-7a02-4d23-baba-0c51015210ca', 'Item_1', 2, 1);
INSERT INTO items (id, create_date, modify_date, item_code, name, price, seller_id) VALUES (2, '2020-09-12 17:35:33.519379', '2020-09-12 17:35:33.519379', 'b1a02fd2-e35d-49cd-9bcd-2cbd73e2b2b7', 'Item_1', 3, 1);


--
-- TOC entry 2923 (class 0 OID 24882)
-- Dependencies: 202
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO roles (id, create_date, modify_date, name) VALUES (1, NULL, NULL, 'ROLE_USER');
INSERT INTO roles (id, create_date, modify_date, name) VALUES (3, NULL, NULL, 'ROLE_ADMIN');
INSERT INTO roles (id, create_date, modify_date, name) VALUES (4, NULL, NULL, 'ROLE_SELLER');
INSERT INTO roles (id, create_date, modify_date, name) VALUES (5, NULL, NULL, 'ROLE_BUYER');


--
-- TOC entry 2925 (class 0 OID 24890)
-- Dependencies: 204
-- Data for Name: user_bids; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO user_bids (id, create_date, modify_date, bid_amount, auction_id, buyer_id) VALUES (1, '2020-09-12 23:25:23.062', '2020-09-12 23:25:23.062', 5, 1, 1);


--
-- TOC entry 2926 (class 0 OID 24896)
-- Dependencies: 205
-- Data for Name: user_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 1);


--
-- TOC entry 2928 (class 0 OID 24903)
-- Dependencies: 207
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO users (id, create_date, modify_date, email, name, password, username) VALUES (1, '2020-09-12 23:00:38.851', '2020-09-12 23:00:38.851', 'seller@sample.com', 'seller', '$2a$10$Iq0aGURoDHM.aJlg/Zaho.MJSDVu903OQCTSXuVRKw/9cGJvDIeDq', 'seller');
INSERT INTO users (id, create_date, modify_date, email, name, password, username) VALUES (2, '2020-09-12 23:02:23.465', '2020-09-12 23:02:23.465', 'buyer@sample.com', 'buyer', '$2a$10$zIjs60RSVsxPFsmldHWedu0QAeJ7wTAVgBN0EyFLrVF6Ze4YF6ySy', 'buyer');


--
-- TOC entry 2771 (class 2606 OID 24868)
-- Name: auctions auctions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auctions
    ADD CONSTRAINT auctions_pkey PRIMARY KEY (id);


--
-- TOC entry 2773 (class 2606 OID 24879)
-- Name: items items_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY items
    ADD CONSTRAINT items_pkey PRIMARY KEY (id);


--
-- TOC entry 2775 (class 2606 OID 24887)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- TOC entry 2783 (class 2606 OID 24914)
-- Name: users uk6dotkott2kjsp8vw4d0m25fb7; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);


--
-- TOC entry 2777 (class 2606 OID 24910)
-- Name: roles uk_nb4h0p6txrmfc0xbrd1kglp9t; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY roles
    ADD CONSTRAINT uk_nb4h0p6txrmfc0xbrd1kglp9t UNIQUE (name);


--
-- TOC entry 2785 (class 2606 OID 24962)
-- Name: users uk_sx468g52bpetvlad2j9y0lptc; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT uk_sx468g52bpetvlad2j9y0lptc UNIQUE (email);


--
-- TOC entry 2787 (class 2606 OID 24912)
-- Name: users ukr43af9ap4edm43mmtq01oddj6; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT ukr43af9ap4edm43mmtq01oddj6 UNIQUE (username);


--
-- TOC entry 2779 (class 2606 OID 24895)
-- Name: user_bids user_bids_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_bids
    ADD CONSTRAINT user_bids_pkey PRIMARY KEY (id);


--
-- TOC entry 2781 (class 2606 OID 24900)
-- Name: user_roles user_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_roles
    ADD CONSTRAINT user_roles_pkey PRIMARY KEY (user_id, role_id);


--
-- TOC entry 2789 (class 2606 OID 24908)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2794 (class 2606 OID 24935)
-- Name: user_bids fkf9go974ys6wv75as2p9sa23li; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_bids
    ADD CONSTRAINT fkf9go974ys6wv75as2p9sa23li FOREIGN KEY (id) REFERENCES auctions(id);


--
-- TOC entry 2795 (class 2606 OID 24940)
-- Name: user_roles fkh8ciramu9cc9q3qcqiv4ue8a6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_roles
    ADD CONSTRAINT fkh8ciramu9cc9q3qcqiv4ue8a6 FOREIGN KEY (role_id) REFERENCES roles(id);


--
-- TOC entry 2796 (class 2606 OID 24945)
-- Name: user_roles fkhfh9dx7w3ubf1co1vdev94g3f; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_roles
    ADD CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f FOREIGN KEY (user_id) REFERENCES users(id);


--
-- TOC entry 2792 (class 2606 OID 24925)
-- Name: user_bids fkp926hdy8pui41oer07kbeecm3; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_bids
    ADD CONSTRAINT fkp926hdy8pui41oer07kbeecm3 FOREIGN KEY (auction_id) REFERENCES auctions(id);


--
-- TOC entry 2790 (class 2606 OID 24915)
-- Name: auctions fkpvixwpkykdb4kcebribl4ucwc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auctions
    ADD CONSTRAINT fkpvixwpkykdb4kcebribl4ucwc FOREIGN KEY (item_id) REFERENCES items(id);


--
-- TOC entry 2793 (class 2606 OID 24930)
-- Name: user_bids fkqf9kxw2907mch3ble3baaos8k; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_bids
    ADD CONSTRAINT fkqf9kxw2907mch3ble3baaos8k FOREIGN KEY (buyer_id) REFERENCES users(id);


--
-- TOC entry 2791 (class 2606 OID 24920)
-- Name: items fksm9ro5ntn6yaav2m7ydato0fc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY items
    ADD CONSTRAINT fksm9ro5ntn6yaav2m7ydato0fc FOREIGN KEY (seller_id) REFERENCES users(id);


-- Completed on 2020-09-13 21:00:07 IST

--
-- PostgreSQL database dump complete
--

