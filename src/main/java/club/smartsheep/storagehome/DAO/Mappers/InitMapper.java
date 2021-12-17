package club.smartsheep.storagehome.DAO.Mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InitMapper {

    @Insert("create table if not exists configs" +
            "(" +
            "    id    int auto_increment" +
            "        constraint `PRIMARY`" +
            "        primary key," +
            "    name  text not null," +
            "    value text not null," +
            "    constraint configs_id_uindex" +
            "        unique (id)" +
            ");" +
            "" +
            "create table if not exists storage_items" +
            "(" +
            "    id        int auto_increment," +
            "    region_id int  not null," +
            "    name      text not null," +
            "    type      text not null," +
            "    owner_id  int  not null," +
            "    constraint storage_items_id_uindex" +
            "        unique (id)" +
            ");" +
            "" +
            "create table if not exists storage_region" +
            "(" +
            "    id       int auto_increment" +
            "        constraint `PRIMARY`" +
            "        primary key," +
            "    name     text not null," +
            "    position json not null," +
            "    constraint storage_region_id_uindex" +
            "        unique (id)" +
            ");" +
            "" +
            "create table if not exists users" +
            "(" +
            "    uuid        varchar(32) not null" +
            "        constraint `PRIMARY`" +
            "        primary key," +
            "    role        text        not null," +
            "    username    text        not null," +
            "    email       text        not null," +
            "    create_time datetime    not null," +
            "    password    text        not null," +
            "    constraint users_uuid_uindex" +
            "        unique (uuid)" +
            ");")
    void defaultInitScript();
    
    @Insert("${script}")
    void customInitScript(String script);
}
