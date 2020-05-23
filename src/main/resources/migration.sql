# Schema
create schema if not exists bank_db collate latin1_swedish_ci;

# Tables

create table if not exists users
(
    id           int auto_increment primary key,
    username     varchar(50)                             not null,
    password     varchar(50)                             not null,
    name         varchar(50)                             not null,
    last_name    varchar(50)                             not null,
    email        varchar(45)                             not null,
    user_id_type enum ('ID_CARD', 'FI_CARD', 'PASSPORT') not null,
    user_id      varchar(25)                             null,
    phone        bigint(10)                              null,
    address      varchar(100)                            not null,
    country      varchar(50)                             not null,
    gender       enum ('MALE', 'FEMALE')                 not null,
    constraint password_UNIQUE
        unique (password),
    constraint user_name_UNIQUE
        unique (username),
    constraint users_UNIQUE
        unique (id)
);

create table if not exists accounts
(
    id             int auto_increment primary key,
    account_number varchar(25)       not null,
    balance        int default 10000 not null,
    user_id        int               not null,
    constraint id_UNIQUE unique (id),
    constraint fk_accounts_users foreign key (user_id) references users (id) on update cascade on delete cascade
);

create table if not exists otps
(
    id         int auto_increment primary key,
    otp        int(6) not null,
    id_account int    null,
    constraint fk_otps_accounts foreign key (id_account) references accounts (id)
);

create table if not exists transactions
(
    id                 int auto_increment primary key,
    type               enum ('INCOMING', 'SENT')               not null,
    value              float                                   not null,
    date               datetime                                not null,
    thirdparty_account varchar(25)                             not null,
    thirdparty_id      bigint(10)                              null,
    thirdparty_id_type enum ('ID_CARD', 'FI_CARD', 'PASSPORT') not null,
    thirdparty_name    varchar(50)                             not null,
    approval           tinyint(1)                              null,
    id_account         int                                     not null,
    constraint fk_transactions_accounts foreign key (id_account) references accounts (id)
);

# Indexes

create index fk_accounts_users_idx
    on accounts (user_id);

create index fk_otps_accounts_idx
    on otps (id_account);

create index fk_transactions_accounts_idx
    on transactions (id_account);
