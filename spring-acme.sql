PGDMP         !                }            spring-acme    15.2    15.2 N    j           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            k           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            l           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            m           1262    62495    spring-acme    DATABASE     �   CREATE DATABASE "spring-acme" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Colombia.1252';
    DROP DATABASE "spring-acme";
                postgres    false            �            1259    95409    comment_mentions    TABLE     p   CREATE TABLE public.comment_mentions (
    comment_id bigint NOT NULL,
    mentioned_user_id bigint NOT NULL
);
 $   DROP TABLE public.comment_mentions;
       public         heap    postgres    false            �            1259    95383    comments    TABLE     �  CREATE TABLE public.comments (
    id bigint NOT NULL,
    comment_id bigint,
    post_id bigint NOT NULL,
    user_id bigint NOT NULL,
    content character varying(200) NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT comments_content_check CHECK (((length((content)::text) >= 1) AND (length((content)::text) <= 200)))
);
    DROP TABLE public.comments;
       public         heap    postgres    false            �            1259    95382    comments_id_seq    SEQUENCE     �   CREATE SEQUENCE public.comments_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.comments_id_seq;
       public          postgres    false    228            n           0    0    comments_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.comments_id_seq OWNED BY public.comments.id;
          public          postgres    false    227            �            1259    95343    follows    TABLE     �   CREATE TABLE public.follows (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    followed_id bigint NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
    DROP TABLE public.follows;
       public         heap    postgres    false            �            1259    95342    follows_id_seq    SEQUENCE     �   CREATE SEQUENCE public.follows_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.follows_id_seq;
       public          postgres    false    224            o           0    0    follows_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.follows_id_seq OWNED BY public.follows.id;
          public          postgres    false    223            �            1259    95325    likes    TABLE     �   CREATE TABLE public.likes (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    post_id bigint NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
    DROP TABLE public.likes;
       public         heap    postgres    false            �            1259    95324    likes_id_seq    SEQUENCE     �   CREATE SEQUENCE public.likes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.likes_id_seq;
       public          postgres    false    222            p           0    0    likes_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.likes_id_seq OWNED BY public.likes.id;
          public          postgres    false    221            �            1259    95361    notifications    TABLE     p  CREATE TABLE public.notifications (
    id bigint NOT NULL,
    sender_id bigint NOT NULL,
    receiver_id bigint NOT NULL,
    subject text NOT NULL,
    description text NOT NULL,
    is_read boolean DEFAULT false NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);
 !   DROP TABLE public.notifications;
       public         heap    postgres    false            �            1259    95360    notifications_id_seq    SEQUENCE     �   CREATE SEQUENCE public.notifications_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.notifications_id_seq;
       public          postgres    false    226            q           0    0    notifications_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.notifications_id_seq OWNED BY public.notifications.id;
          public          postgres    false    225            �            1259    95309    post_tag    TABLE     Z   CREATE TABLE public.post_tag (
    post_id bigint NOT NULL,
    tag_id bigint NOT NULL
);
    DROP TABLE public.post_tag;
       public         heap    postgres    false            �            1259    95286    posts    TABLE     w  CREATE TABLE public.posts (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    image_url character varying(255),
    description character varying(500),
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    CONSTRAINT posts_description_check CHECK (((length((description)::text) >= 5) AND (length((description)::text) <= 500)))
);
    DROP TABLE public.posts;
       public         heap    postgres    false            �            1259    95285    posts_id_seq    SEQUENCE     �   CREATE SEQUENCE public.posts_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.posts_id_seq;
       public          postgres    false    217            r           0    0    posts_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.posts_id_seq OWNED BY public.posts.id;
          public          postgres    false    216            �            1259    95301    tags    TABLE     ^   CREATE TABLE public.tags (
    id bigint NOT NULL,
    tag character varying(255) NOT NULL
);
    DROP TABLE public.tags;
       public         heap    postgres    false            �            1259    95300    tags_id_seq    SEQUENCE     �   CREATE SEQUENCE public.tags_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.tags_id_seq;
       public          postgres    false    219            s           0    0    tags_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.tags_id_seq OWNED BY public.tags.id;
          public          postgres    false    218            �            1259    95264    users    TABLE     2  CREATE TABLE public.users (
    id bigint NOT NULL,
    name character varying(100) NOT NULL,
    username character varying(50) NOT NULL,
    celphone character varying(20) NOT NULL,
    email character varying(255) NOT NULL,
    date_birth timestamp(6) without time zone NOT NULL,
    password character varying(255) NOT NULL,
    bibliography text DEFAULT ''::text,
    profile_picture text DEFAULT 'https://images.unsplash.com/photo-1511367461989-f85a21fda167?fm=jpg&q=60&w=3000&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8cHJvZmlsZXxlbnwwfHwwfHx8MA%3D%3D'::text,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    role character varying(255),
    CONSTRAINT users_role_check CHECK (((role)::text = 'USER'::text))
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    95263    users_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    215            t           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    214            �           2604    103477    comments id    DEFAULT     j   ALTER TABLE ONLY public.comments ALTER COLUMN id SET DEFAULT nextval('public.comments_id_seq'::regclass);
 :   ALTER TABLE public.comments ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    227    228    228            �           2604    103528 
   follows id    DEFAULT     h   ALTER TABLE ONLY public.follows ALTER COLUMN id SET DEFAULT nextval('public.follows_id_seq'::regclass);
 9   ALTER TABLE public.follows ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    224    223    224            �           2604    103553    likes id    DEFAULT     d   ALTER TABLE ONLY public.likes ALTER COLUMN id SET DEFAULT nextval('public.likes_id_seq'::regclass);
 7   ALTER TABLE public.likes ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    221    222    222            �           2604    103578    notifications id    DEFAULT     t   ALTER TABLE ONLY public.notifications ALTER COLUMN id SET DEFAULT nextval('public.notifications_id_seq'::regclass);
 ?   ALTER TABLE public.notifications ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    225    226    226            �           2604    103631    posts id    DEFAULT     d   ALTER TABLE ONLY public.posts ALTER COLUMN id SET DEFAULT nextval('public.posts_id_seq'::regclass);
 7   ALTER TABLE public.posts ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    216    217            �           2604    103679    tags id    DEFAULT     b   ALTER TABLE ONLY public.tags ALTER COLUMN id SET DEFAULT nextval('public.tags_id_seq'::regclass);
 6   ALTER TABLE public.tags ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    219    218    219            �           2604    103697    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    214    215            g          0    95409    comment_mentions 
   TABLE DATA           I   COPY public.comment_mentions (comment_id, mentioned_user_id) FROM stdin;
    public          postgres    false    229   �_       f          0    95383    comments 
   TABLE DATA           e   COPY public.comments (id, comment_id, post_id, user_id, content, created_at, updated_at) FROM stdin;
    public          postgres    false    228   �_       b          0    95343    follows 
   TABLE DATA           G   COPY public.follows (id, user_id, followed_id, created_at) FROM stdin;
    public          postgres    false    224   �`       `          0    95325    likes 
   TABLE DATA           A   COPY public.likes (id, user_id, post_id, created_at) FROM stdin;
    public          postgres    false    222   a       d          0    95361    notifications 
   TABLE DATA           z   COPY public.notifications (id, sender_id, receiver_id, subject, description, is_read, created_at, updated_at) FROM stdin;
    public          postgres    false    226   za       ^          0    95309    post_tag 
   TABLE DATA           3   COPY public.post_tag (post_id, tag_id) FROM stdin;
    public          postgres    false    220   �a       [          0    95286    posts 
   TABLE DATA           \   COPY public.posts (id, user_id, image_url, description, created_at, updated_at) FROM stdin;
    public          postgres    false    217   �a       ]          0    95301    tags 
   TABLE DATA           '   COPY public.tags (id, tag) FROM stdin;
    public          postgres    false    219   Sc       Y          0    95264    users 
   TABLE DATA           �   COPY public.users (id, name, username, celphone, email, date_birth, password, bibliography, profile_picture, created_at, updated_at, role) FROM stdin;
    public          postgres    false    215   �c       u           0    0    comments_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.comments_id_seq', 11, true);
          public          postgres    false    227            v           0    0    follows_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.follows_id_seq', 7, true);
          public          postgres    false    223            w           0    0    likes_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.likes_id_seq', 45, true);
          public          postgres    false    221            x           0    0    notifications_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.notifications_id_seq', 1, false);
          public          postgres    false    225            y           0    0    posts_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.posts_id_seq', 5, true);
          public          postgres    false    216            z           0    0    tags_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.tags_id_seq', 6, true);
          public          postgres    false    218            {           0    0    users_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.users_id_seq', 27, true);
          public          postgres    false    214            �           2606    119840    tags Tag_ui 
   CONSTRAINT     G   ALTER TABLE ONLY public.tags
    ADD CONSTRAINT "Tag_ui" UNIQUE (tag);
 7   ALTER TABLE ONLY public.tags DROP CONSTRAINT "Tag_ui";
       public            postgres    false    219            �           2606    103467 &   comment_mentions comment_mentions_pkey 
   CONSTRAINT        ALTER TABLE ONLY public.comment_mentions
    ADD CONSTRAINT comment_mentions_pkey PRIMARY KEY (comment_id, mentioned_user_id);
 P   ALTER TABLE ONLY public.comment_mentions DROP CONSTRAINT comment_mentions_pkey;
       public            postgres    false    229    229            �           2606    103479    comments comments_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.comments DROP CONSTRAINT comments_pkey;
       public            postgres    false    228            �           2606    103530    follows follows_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.follows
    ADD CONSTRAINT follows_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.follows DROP CONSTRAINT follows_pkey;
       public            postgres    false    224            �           2606    103555    likes likes_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.likes
    ADD CONSTRAINT likes_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.likes DROP CONSTRAINT likes_pkey;
       public            postgres    false    222            �           2606    103580     notifications notifications_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT notifications_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.notifications DROP CONSTRAINT notifications_pkey;
       public            postgres    false    226            �           2606    103621    post_tag post_tag_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY public.post_tag
    ADD CONSTRAINT post_tag_pkey PRIMARY KEY (post_id, tag_id);
 @   ALTER TABLE ONLY public.post_tag DROP CONSTRAINT post_tag_pkey;
       public            postgres    false    220    220            �           2606    103633    posts posts_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.posts
    ADD CONSTRAINT posts_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.posts DROP CONSTRAINT posts_pkey;
       public            postgres    false    217            �           2606    103681    tags tags_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.tags
    ADD CONSTRAINT tags_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.tags DROP CONSTRAINT tags_pkey;
       public            postgres    false    219            �           2606    95280    users users_celphone_key 
   CONSTRAINT     W   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_celphone_key UNIQUE (celphone);
 B   ALTER TABLE ONLY public.users DROP CONSTRAINT users_celphone_key;
       public            postgres    false    215            �           2606    95282    users users_email_key 
   CONSTRAINT     Q   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);
 ?   ALTER TABLE ONLY public.users DROP CONSTRAINT users_email_key;
       public            postgres    false    215            �           2606    103766    users users_password_key 
   CONSTRAINT     W   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_password_key UNIQUE (password);
 B   ALTER TABLE ONLY public.users DROP CONSTRAINT users_password_key;
       public            postgres    false    215            �           2606    103699    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    215            �           2606    95278    users users_username_key 
   CONSTRAINT     W   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);
 B   ALTER TABLE ONLY public.users DROP CONSTRAINT users_username_key;
       public            postgres    false    215            �           2606    103485 1   comment_mentions comment_mentions_comment_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.comment_mentions
    ADD CONSTRAINT comment_mentions_comment_id_fkey FOREIGN KEY (comment_id) REFERENCES public.comments(id) ON DELETE CASCADE;
 [   ALTER TABLE ONLY public.comment_mentions DROP CONSTRAINT comment_mentions_comment_id_fkey;
       public          postgres    false    3257    228    229            �           2606    103700 8   comment_mentions comment_mentions_mentioned_user_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.comment_mentions
    ADD CONSTRAINT comment_mentions_mentioned_user_id_fkey FOREIGN KEY (mentioned_user_id) REFERENCES public.users(id) ON DELETE CASCADE;
 b   ALTER TABLE ONLY public.comment_mentions DROP CONSTRAINT comment_mentions_mentioned_user_id_fkey;
       public          postgres    false    3239    215    229            �           2606    103501 !   comments comments_comment_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_comment_id_fkey FOREIGN KEY (comment_id) REFERENCES public.comments(id) ON DELETE CASCADE;
 K   ALTER TABLE ONLY public.comments DROP CONSTRAINT comments_comment_id_fkey;
       public          postgres    false    228    3257    228            �           2606    103634    comments comments_post_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_post_id_fkey FOREIGN KEY (post_id) REFERENCES public.posts(id) ON DELETE CASCADE;
 H   ALTER TABLE ONLY public.comments DROP CONSTRAINT comments_post_id_fkey;
       public          postgres    false    228    217    3243            �           2606    103705    comments comments_user_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.comments
    ADD CONSTRAINT comments_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE;
 H   ALTER TABLE ONLY public.comments DROP CONSTRAINT comments_user_id_fkey;
       public          postgres    false    215    3239    228            �           2606    103710     follows follows_followed_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.follows
    ADD CONSTRAINT follows_followed_id_fkey FOREIGN KEY (followed_id) REFERENCES public.users(id) ON DELETE CASCADE;
 J   ALTER TABLE ONLY public.follows DROP CONSTRAINT follows_followed_id_fkey;
       public          postgres    false    3239    224    215            �           2606    103715    follows follows_user_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.follows
    ADD CONSTRAINT follows_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE;
 F   ALTER TABLE ONLY public.follows DROP CONSTRAINT follows_user_id_fkey;
       public          postgres    false    215    3239    224            �           2606    103639    likes likes_post_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.likes
    ADD CONSTRAINT likes_post_id_fkey FOREIGN KEY (post_id) REFERENCES public.posts(id) ON DELETE CASCADE;
 B   ALTER TABLE ONLY public.likes DROP CONSTRAINT likes_post_id_fkey;
       public          postgres    false    222    3243    217            �           2606    103720    likes likes_user_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.likes
    ADD CONSTRAINT likes_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE;
 B   ALTER TABLE ONLY public.likes DROP CONSTRAINT likes_user_id_fkey;
       public          postgres    false    215    222    3239            �           2606    103725 ,   notifications notifications_receiver_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT notifications_receiver_id_fkey FOREIGN KEY (receiver_id) REFERENCES public.users(id) ON DELETE CASCADE;
 V   ALTER TABLE ONLY public.notifications DROP CONSTRAINT notifications_receiver_id_fkey;
       public          postgres    false    226    215    3239            �           2606    103730 *   notifications notifications_sender_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT notifications_sender_id_fkey FOREIGN KEY (sender_id) REFERENCES public.users(id) ON DELETE CASCADE;
 T   ALTER TABLE ONLY public.notifications DROP CONSTRAINT notifications_sender_id_fkey;
       public          postgres    false    226    215    3239            �           2606    103644    post_tag post_tag_post_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.post_tag
    ADD CONSTRAINT post_tag_post_id_fkey FOREIGN KEY (post_id) REFERENCES public.posts(id) ON DELETE CASCADE;
 H   ALTER TABLE ONLY public.post_tag DROP CONSTRAINT post_tag_post_id_fkey;
       public          postgres    false    3243    217    220            �           2606    103682    post_tag post_tag_tag_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.post_tag
    ADD CONSTRAINT post_tag_tag_id_fkey FOREIGN KEY (tag_id) REFERENCES public.tags(id) ON DELETE CASCADE;
 G   ALTER TABLE ONLY public.post_tag DROP CONSTRAINT post_tag_tag_id_fkey;
       public          postgres    false    219    220    3247            �           2606    103735    posts posts_user_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.posts
    ADD CONSTRAINT posts_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE;
 B   ALTER TABLE ONLY public.posts DROP CONSTRAINT posts_user_id_fkey;
       public          postgres    false    215    217    3239            g      x������ � �      f   �   x�}�M
�0���)rCf&���[7RC���o_�(b���<�����1`�=�b^�X�>��}�t��BS)�����	��D���xJ�eh��F��)���G�|�%M㒦�ٟ�P*���F���#Kd�ܾ���BѦC?X ������?Ev[��%����T�      b   r   x�]���0C�s�"�a�5���:�������	���/��q:˭b�M7s�7�-�
\�Vڐq 7�w�M�r���ڒ������&�W�B+S�d@�|�N�̕L.ʭ"�	�&*      `   f   x�m̱�0��Pf>���뿎�"v�oVI���R@C%��k��[T{b�>�$���3�%k&�ٝ,���Ykv��m ��� %����f����      d      x������ � �      ^      x�3�4����� y"      [   �  x���Mo�0��ɯp/{��$�H+T�V�v�@��q�I���Q���P)��ã�ѫ�`��!LsM�V������t�A����N���%˴��c���T;FZ������z�?L]v�Fu�f��l4���Ywl�AfF���38K��l��jFqQ��bE-����ŏ�YR�����᥋mjY��q(�r=C��ϲ}j��x�l�NQ���sp:���Z�$�T��,���]��JG����7���x��Ȼ���ه�ǻ����g�o.>}����]��d�W_����j>��6�hV*�~[R~8�$��W�Ѵ�4L2v��H�ZP6
4W�hË�l�ج��M���z3t}�ˮGur��.��j�`���9ʿ�<Ͽ���W      ]   )   x�3��H,(�t�,*�pI��2�tJ��I�)�K����� � 	�      Y   #  x�Օ�n�J�����Ev��x�����9����9 ��5���c�6y��,�����m�>�f�#y�ͷ����I u7�V���abRf��Y�*j�� �$֐c;-�[��iC�.��SO�M1ػ�������b��\v|x��-ː��n�X�^1�,�k�$��<9��V��(Ҽma�7"��ӈ����F$E�B!�,�5��m�1Z�1��:>ۦ�O�3?UgB�)��py�-[&�����6!U=�v���ړQ����8�'��W��~G�bVGKYU���Sۓ�_�U��!�-��m
�&��&�E�^��S�Y(�>��8h<a�@�`B(�U�P�����Q�Ch�jA�����ܯqi���r1��`<��Ҿ���Ҹ�&�����������qf:Fm|�	k �=q&�@d�+y�aY�"�ģ��r��ߪ�c��nA�۳�k������м�ei҅�r�}�.�n�a��}�@nB� 5�t�UG�3ѡ�ư�	�l���艳�ub�\��\�1_�]6���9�Sd�Y3 / �з��nD|~�n��ᰝV,�g�>�/˼���.�r�r^^�[�� 7�"�6�J����'�P���90�TDQ̥�ox��cuN�͚Po�[�yE;����-��%���M���i�����]�<����7Q^{���*���w�i�<j�Ts F���V�7_�ܸ	�x)ն"Ӏ��,C�Ŧ�z�?�bm��Xwl��EO\�։�<K��3�����z'Z��4ޤǦ�d�6u,�Y�U?/��Z�BP؂�[���/.R2���+RU����(}T�|�ڭW�汾��e"�[㎜M�v�y�VJ=�!�9��н0/D����d��N��Kd��?�B�  �/�|���`A��@d+�RȢ�4cЂ�/�3�n��d��P ����^��RHŖ��B�+��<,³��9R���m�I�W�6�8-�ER���<8�upf����
�]m��95�^����ŕװjzh��)^�4ݶ�I+�`�8��vD�c>#�NNN��z     