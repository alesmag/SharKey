PGDMP     !                    {           panicatthecisco     14.7 (Ubuntu 14.7-1.pgdg20.04+1)     15.2 (Ubuntu 15.2-1.pgdg20.04+1)                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            	           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            
           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16384    panicatthecisco    DATABASE     {   CREATE DATABASE panicatthecisco WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'it_IT.UTF-8';
    DROP DATABASE panicatthecisco;
                postgres    false                        2615    2200    public    SCHEMA     2   -- *not* creating schema, since initdb creates it
 2   -- *not* dropping schema, since initdb creates it
                postgres    false                       0    0    SCHEMA public    ACL     Q   REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;
                   postgres    false    4            �            1259    16429    prodotti    TABLE     "  CREATE TABLE public.prodotti (
    id character varying(30) NOT NULL,
    prezzo double precision,
    nome character varying(50),
    descrizione character varying(300),
    immagine character varying(300),
    pegi integer,
    data_uscita date,
    id_venditore character varying(50)
);
    DROP TABLE public.prodotti;
       public         heap    postgres    false    4            �            1259    32797 
   recensioni    TABLE     �   CREATE TABLE public.recensioni (
    id integer NOT NULL,
    titolo character varying(50),
    testo character varying(300),
    voto integer,
    data character varying(16),
    username character varying(50)
);
    DROP TABLE public.recensioni;
       public         heap    postgres    false    4            �            1259    32796    recensioni_id_seq    SEQUENCE     �   CREATE SEQUENCE public.recensioni_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.recensioni_id_seq;
       public          postgres    false    212    4                       0    0    recensioni_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.recensioni_id_seq OWNED BY public.recensioni.id;
          public          postgres    false    211            �            1259    16424    utenti    TABLE     �   CREATE TABLE public.utenti (
    username character varying(50) NOT NULL,
    email character varying(50),
    psw character varying(50),
    nome character varying(50),
    cognome character varying(50)
);
    DROP TABLE public.utenti;
       public         heap    postgres    false    4            n           2604    32800    recensioni id    DEFAULT     n   ALTER TABLE ONLY public.recensioni ALTER COLUMN id SET DEFAULT nextval('public.recensioni_id_seq'::regclass);
 <   ALTER TABLE public.recensioni ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    212    211    212                      0    16429    prodotti 
   TABLE DATA           l   COPY public.prodotti (id, prezzo, nome, descrizione, immagine, pegi, data_uscita, id_venditore) FROM stdin;
    public          postgres    false    210   �                 0    32797 
   recensioni 
   TABLE DATA           M   COPY public.recensioni (id, titolo, testo, voto, data, username) FROM stdin;
    public          postgres    false    212                    0    16424    utenti 
   TABLE DATA           E   COPY public.utenti (username, email, psw, nome, cognome) FROM stdin;
    public          postgres    false    209   Z                  0    0    recensioni_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.recensioni_id_seq', 14, true);
          public          postgres    false    211            r           2606    16435    prodotti prodotti_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.prodotti
    ADD CONSTRAINT prodotti_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.prodotti DROP CONSTRAINT prodotti_pkey;
       public            postgres    false    210            t           2606    32802    recensioni recensioni_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.recensioni
    ADD CONSTRAINT recensioni_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.recensioni DROP CONSTRAINT recensioni_pkey;
       public            postgres    false    212            p           2606    16428    utenti utenti_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.utenti
    ADD CONSTRAINT utenti_pkey PRIMARY KEY (username);
 <   ALTER TABLE ONLY public.utenti DROP CONSTRAINT utenti_pkey;
       public            postgres    false    209            u           2606    16441 #   prodotti prodotti_id_venditore_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.prodotti
    ADD CONSTRAINT prodotti_id_venditore_fkey FOREIGN KEY (id_venditore) REFERENCES public.utenti(username) ON UPDATE CASCADE;
 M   ALTER TABLE ONLY public.prodotti DROP CONSTRAINT prodotti_id_venditore_fkey;
       public          postgres    false    210    3184    209            v           2606    32803 #   recensioni recensioni_username_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.recensioni
    ADD CONSTRAINT recensioni_username_fkey FOREIGN KEY (username) REFERENCES public.utenti(username);
 M   ALTER TABLE ONLY public.recensioni DROP CONSTRAINT recensioni_username_fkey;
       public          postgres    false    209    3184    212               4  x��T�r�6>SO��
Iɖ�l9b;ve'�����@���~�>J_�g�X�=i�Ɍg8��.���Z��z6�D�(.ć��,;Z���vGp�ZS�	Vg�]x�b���4�H��ڨ�Q���k�BMZ#xr��|�W:Z���.�O����V+��z�Ig:%a�e���x��D�3>���y>�١{O�h��ڏÎ���l�dE��y1�B�e�bKZ��鑸�\߉b��D�W���>{ؒ���
e>�}������0��xU֩����Ŀ�������z����0N�ߋp��kg��� ���+5�}~����%�b�R��(Jf�"�fy7��T�cy%~�|�ɦI�>����*cj[��7�G믵a%���Ƶ4�@�рD�����`�]�J��wO�V�M�T>�fmlB��U)�gsq����W����Z��L=�Rk�"��PB��(���� ��ћd�VċV=�~��;�N�u4�i�yPp�Bh�`ץ��c���HA`��8��������=uu2[�����4%	���=���g�u
7
��w@�uBr�{��^*'�j�;d �;�s5�o]�fp(��n�z1ĒdG0�S�Na��Ox�?I��9�qr��Ɠ�͝wˋ�+��v�A��t�C::�ޱ�&�>��{D~��}�tLz�s�~�pHG�-��0�����2_��6�g(��Kf�c�r>BY�kc;���o�R�0L-eT	$K��_�g�%�P�U��-���M��#�I|LK�	�P�[�?�|���A.�DQ�A�FJ3�m4����         I  x�eT�n�T^��� u�$�-�tׂ�S1!F�@B���kqr��������+^�o`��R�9�����ٯܹ��#$��.F&�V�	���1̜p�e"���[��3E�`�`K~)t u�����⽫]S5��W/�
�ק���0p�e����]�r,}�� �Xˁ%j��$y��W���@z�;��8��0�WC'�wS�x%a�#�/�%����S�)�"�:�ʫժwBʖފ�,��CЪ����WEK��A_u�$[�\r&ؖ��:V/O�7��qן>��w�|�^�v�*vKC<*j�>��Y�g	��1�E8�O=��T%O����75U4�*1Jd#�u�B1"|a�r�46�$AU�Z64K�^���w2�e4L��F��5|#c	WE��~ρ��d�O��[	�C��T�k��긴v��۸�����ڝ+x�q��k�������`.�Åڴ�T�3��F������R	��{��2����lZd)���q�Ԇ���k�՚-���#Kb�ѵ���*����ٓ������;G]�ȳ��!��u�3o��~�X��s+��=Ż���|@!�c7e�9~���3'��Ӊ6�is�P�7���j��[F�|���6�}0]��pm��9�N��V�2!���ˌ���5JEѫ�����{�����k=���\szR9�iTs��cw����=k�(�l^�>��� ;����y��S^{~�Ϛa�'�,ig�T���+h�a���,�=��� ��H/��_�\�+`DU��*`#���Y���&�v'u�;yd[C�9�*�Fް�~^�V�>���         
  x�u��j�0Dϫ�����&�^j(M9������XRI���p{�ewx�4��0Y�U�|�z.���p8T/Gh���if\�lV&L{R|�O�{h59ǲ{��hY�-�S�]����:f��dT���L�o��\���K��,� ��f �x��:˙nrci<�5����}�]Xi���#��f���Ě%y3�Å�X���(�Π�D��=�9�AY�o�[���*���Hp��n<�e;=���?O�jbT_�R��     