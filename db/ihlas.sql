--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- Name: user_group; Type: TYPE; Schema: public; Owner: ihlas
--

CREATE TYPE user_group AS ENUM (
    'admin',
    'user'
);


ALTER TYPE public.user_group OWNER TO ihlas;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: app_types; Type: TABLE; Schema: public; Owner: ihlas; Tablespace: 
--

CREATE TABLE app_types (
    id integer NOT NULL,
    name character varying NOT NULL
);


ALTER TABLE public.app_types OWNER TO ihlas;

--
-- Name: app_types_id_seq; Type: SEQUENCE; Schema: public; Owner: ihlas
--

CREATE SEQUENCE app_types_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.app_types_id_seq OWNER TO ihlas;

--
-- Name: app_types_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ihlas
--

ALTER SEQUENCE app_types_id_seq OWNED BY app_types.id;


--
-- Name: applications; Type: TABLE; Schema: public; Owner: ihlas; Tablespace: 
--

CREATE TABLE applications (
    id integer NOT NULL,
    type integer NOT NULL,
    indigent integer NOT NULL,
    applied_on date,
    reason character varying
);


ALTER TABLE public.applications OWNER TO ihlas;

--
-- Name: applications_id_seq; Type: SEQUENCE; Schema: public; Owner: ihlas
--

CREATE SEQUENCE applications_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.applications_id_seq OWNER TO ihlas;

--
-- Name: applications_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ihlas
--

ALTER SEQUENCE applications_id_seq OWNED BY applications.id;


--
-- Name: doc_types; Type: TABLE; Schema: public; Owner: ihlas; Tablespace: 
--

CREATE TABLE doc_types (
    id integer NOT NULL,
    name character varying NOT NULL
);


ALTER TABLE public.doc_types OWNER TO ihlas;

--
-- Name: doc_types_id_seq; Type: SEQUENCE; Schema: public; Owner: ihlas
--

CREATE SEQUENCE doc_types_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.doc_types_id_seq OWNER TO ihlas;

--
-- Name: doc_types_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ihlas
--

ALTER SEQUENCE doc_types_id_seq OWNED BY doc_types.id;


--
-- Name: docs; Type: TABLE; Schema: public; Owner: ihlas; Tablespace: 
--

CREATE TABLE docs (
    id integer NOT NULL,
    type integer NOT NULL,
    application integer,
    indigent integer,
    image bytea NOT NULL,
    notes character varying
);


ALTER TABLE public.docs OWNER TO ihlas;

--
-- Name: docs_id_seq; Type: SEQUENCE; Schema: public; Owner: ihlas
--

CREATE SEQUENCE docs_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.docs_id_seq OWNER TO ihlas;

--
-- Name: docs_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ihlas
--

ALTER SEQUENCE docs_id_seq OWNED BY docs.id;


--
-- Name: indigents; Type: TABLE; Schema: public; Owner: ihlas; Tablespace: 
--

CREATE TABLE indigents (
    id integer NOT NULL,
    last_name character varying NOT NULL,
    first_name character varying NOT NULL,
    birth_date date NOT NULL,
    surname character varying
);


ALTER TABLE public.indigents OWNER TO ihlas;

--
-- Name: indigents_id_seq; Type: SEQUENCE; Schema: public; Owner: ihlas
--

CREATE SEQUENCE indigents_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.indigents_id_seq OWNER TO ihlas;

--
-- Name: indigents_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ihlas
--

ALTER SEQUENCE indigents_id_seq OWNED BY indigents.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: ihlas; Tablespace: 
--

CREATE TABLE users (
    id integer NOT NULL,
    name character varying NOT NULL,
    password character varying NOT NULL,
    full_name character varying
);


ALTER TABLE public.users OWNER TO ihlas;

--
-- Name: users_groups; Type: TABLE; Schema: public; Owner: ihlas; Tablespace: 
--

CREATE TABLE users_groups (
    id integer NOT NULL,
    "user" integer NOT NULL,
    name user_group NOT NULL
);


ALTER TABLE public.users_groups OWNER TO ihlas;

--
-- Name: users_groups_id_seq; Type: SEQUENCE; Schema: public; Owner: ihlas
--

CREATE SEQUENCE users_groups_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_groups_id_seq OWNER TO ihlas;

--
-- Name: users_groups_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ihlas
--

ALTER SEQUENCE users_groups_id_seq OWNED BY users_groups.id;


--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: ihlas
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO ihlas;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: ihlas
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: ihlas
--

ALTER TABLE ONLY app_types ALTER COLUMN id SET DEFAULT nextval('app_types_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: ihlas
--

ALTER TABLE ONLY applications ALTER COLUMN id SET DEFAULT nextval('applications_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: ihlas
--

ALTER TABLE ONLY doc_types ALTER COLUMN id SET DEFAULT nextval('doc_types_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: ihlas
--

ALTER TABLE ONLY docs ALTER COLUMN id SET DEFAULT nextval('docs_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: ihlas
--

ALTER TABLE ONLY indigents ALTER COLUMN id SET DEFAULT nextval('indigents_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: ihlas
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: ihlas
--

ALTER TABLE ONLY users_groups ALTER COLUMN id SET DEFAULT nextval('users_groups_id_seq'::regclass);


--
-- Name: app_types_name_key; Type: CONSTRAINT; Schema: public; Owner: ihlas; Tablespace: 
--

ALTER TABLE ONLY app_types
    ADD CONSTRAINT app_types_name_key UNIQUE (name);


--
-- Name: app_types_pkey; Type: CONSTRAINT; Schema: public; Owner: ihlas; Tablespace: 
--

ALTER TABLE ONLY app_types
    ADD CONSTRAINT app_types_pkey PRIMARY KEY (id);


--
-- Name: applications_pkey; Type: CONSTRAINT; Schema: public; Owner: ihlas; Tablespace: 
--

ALTER TABLE ONLY applications
    ADD CONSTRAINT applications_pkey PRIMARY KEY (id);


--
-- Name: doc_types_name_key; Type: CONSTRAINT; Schema: public; Owner: ihlas; Tablespace: 
--

ALTER TABLE ONLY doc_types
    ADD CONSTRAINT doc_types_name_key UNIQUE (name);


--
-- Name: doc_types_pkey; Type: CONSTRAINT; Schema: public; Owner: ihlas; Tablespace: 
--

ALTER TABLE ONLY doc_types
    ADD CONSTRAINT doc_types_pkey PRIMARY KEY (id);


--
-- Name: docs_pkey; Type: CONSTRAINT; Schema: public; Owner: ihlas; Tablespace: 
--

ALTER TABLE ONLY docs
    ADD CONSTRAINT docs_pkey PRIMARY KEY (id);


--
-- Name: indigents_pkey; Type: CONSTRAINT; Schema: public; Owner: ihlas; Tablespace: 
--

ALTER TABLE ONLY indigents
    ADD CONSTRAINT indigents_pkey PRIMARY KEY (id);


--
-- Name: users_groups_pkey; Type: CONSTRAINT; Schema: public; Owner: ihlas; Tablespace: 
--

ALTER TABLE ONLY users_groups
    ADD CONSTRAINT users_groups_pkey PRIMARY KEY (id);


--
-- Name: users_groups_user_name_key; Type: CONSTRAINT; Schema: public; Owner: ihlas; Tablespace: 
--

ALTER TABLE ONLY users_groups
    ADD CONSTRAINT users_groups_user_name_key UNIQUE ("user", name);


--
-- Name: users_name_key; Type: CONSTRAINT; Schema: public; Owner: ihlas; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_name_key UNIQUE (name);


--
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: ihlas; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: applications_indigent_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ihlas
--

ALTER TABLE ONLY applications
    ADD CONSTRAINT applications_indigent_fkey FOREIGN KEY (indigent) REFERENCES indigents(id);


--
-- Name: applications_type_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ihlas
--

ALTER TABLE ONLY applications
    ADD CONSTRAINT applications_type_fkey FOREIGN KEY (type) REFERENCES app_types(id);


--
-- Name: docs_application_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ihlas
--

ALTER TABLE ONLY docs
    ADD CONSTRAINT docs_application_fkey FOREIGN KEY (application) REFERENCES applications(id);


--
-- Name: docs_indigent_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ihlas
--

ALTER TABLE ONLY docs
    ADD CONSTRAINT docs_indigent_fkey FOREIGN KEY (indigent) REFERENCES indigents(id);


--
-- Name: docs_type_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ihlas
--

ALTER TABLE ONLY docs
    ADD CONSTRAINT docs_type_fkey FOREIGN KEY (type) REFERENCES doc_types(id);


--
-- Name: users_groups_user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: ihlas
--

ALTER TABLE ONLY users_groups
    ADD CONSTRAINT users_groups_user_fkey FOREIGN KEY ("user") REFERENCES users(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- Name: app_types; Type: ACL; Schema: public; Owner: ihlas
--

REVOKE ALL ON TABLE app_types FROM PUBLIC;
REVOKE ALL ON TABLE app_types FROM ihlas;
GRANT ALL ON TABLE app_types TO ihlas;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE app_types TO ihlas_web;


--
-- Name: app_types_id_seq; Type: ACL; Schema: public; Owner: ihlas
--

REVOKE ALL ON SEQUENCE app_types_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE app_types_id_seq FROM ihlas;
GRANT ALL ON SEQUENCE app_types_id_seq TO ihlas;
GRANT USAGE ON SEQUENCE app_types_id_seq TO ihlas_web;


--
-- Name: applications; Type: ACL; Schema: public; Owner: ihlas
--

REVOKE ALL ON TABLE applications FROM PUBLIC;
REVOKE ALL ON TABLE applications FROM ihlas;
GRANT ALL ON TABLE applications TO ihlas;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE applications TO ihlas_web;


--
-- Name: applications_id_seq; Type: ACL; Schema: public; Owner: ihlas
--

REVOKE ALL ON SEQUENCE applications_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE applications_id_seq FROM ihlas;
GRANT ALL ON SEQUENCE applications_id_seq TO ihlas;
GRANT USAGE ON SEQUENCE applications_id_seq TO ihlas_web;


--
-- Name: doc_types; Type: ACL; Schema: public; Owner: ihlas
--

REVOKE ALL ON TABLE doc_types FROM PUBLIC;
REVOKE ALL ON TABLE doc_types FROM ihlas;
GRANT ALL ON TABLE doc_types TO ihlas;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE doc_types TO ihlas_web;


--
-- Name: doc_types_id_seq; Type: ACL; Schema: public; Owner: ihlas
--

REVOKE ALL ON SEQUENCE doc_types_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE doc_types_id_seq FROM ihlas;
GRANT ALL ON SEQUENCE doc_types_id_seq TO ihlas;
GRANT USAGE ON SEQUENCE doc_types_id_seq TO ihlas_web;


--
-- Name: docs; Type: ACL; Schema: public; Owner: ihlas
--

REVOKE ALL ON TABLE docs FROM PUBLIC;
REVOKE ALL ON TABLE docs FROM ihlas;
GRANT ALL ON TABLE docs TO ihlas;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE docs TO ihlas_web;


--
-- Name: docs_id_seq; Type: ACL; Schema: public; Owner: ihlas
--

REVOKE ALL ON SEQUENCE docs_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE docs_id_seq FROM ihlas;
GRANT ALL ON SEQUENCE docs_id_seq TO ihlas;
GRANT USAGE ON SEQUENCE docs_id_seq TO ihlas_web;


--
-- Name: indigents; Type: ACL; Schema: public; Owner: ihlas
--

REVOKE ALL ON TABLE indigents FROM PUBLIC;
REVOKE ALL ON TABLE indigents FROM ihlas;
GRANT ALL ON TABLE indigents TO ihlas;
GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE indigents TO ihlas_web;


--
-- Name: indigents_id_seq; Type: ACL; Schema: public; Owner: ihlas
--

REVOKE ALL ON SEQUENCE indigents_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE indigents_id_seq FROM ihlas;
GRANT ALL ON SEQUENCE indigents_id_seq TO ihlas;
GRANT USAGE ON SEQUENCE indigents_id_seq TO ihlas_web;


--
-- PostgreSQL database dump complete
--

