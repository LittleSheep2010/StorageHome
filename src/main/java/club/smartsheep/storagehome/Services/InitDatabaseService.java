package club.smartsheep.storagehome.Services;

import club.smartsheep.storagehome.DAO.Mappers.ExecuteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitDatabaseService {

    @Autowired
    ExecuteMapper executeMapper;

    public void init() {
        // Configs table creation
        executeMapper.execute("create table if not exists configs\n" +
                "(\n" +
                "id int auto_increment\n" +
                "primary key,\n" +
                "name text not null,\n" +
                "value text not null,\n" +
                "constraint configs_id_uindex\n" +
                "unique (id)\n" +
                ");\n");

        // Storage items table creation
        executeMapper.execute("create table if not exists storage_items\n" +
                "(\n" +
                "id int auto_increment,\n" +
                "region_id int not null,\n" +
                "name text not null,\n" +
                "type text not null,\n" +
                "owner_id int not null,\n" +
                "constraint storage_items_id_uindex\n" +
                "unique (id)\n" +
                ");\n");

        // Storage region table creation
        executeMapper.execute(
                "create table if not exists storage_region\n" +
                        "(\n" +
                        "id int auto_increment\n" +
                        "primary key,\n" +
                        "name text not null,\n" +
                        "position json not null,\n" +
                        "constraint storage_region_id_uindex\n" +
                        "unique (id)\n" +
                        ");\n");

        // User table creation
        executeMapper.execute(
                "create table if not exists users\n" +
                        "(\n" +
                        "uuid varchar(32) not null\n" +
                        "primary key,\n" +
                        "role text not null,\n" +
                        "username text not null,\n" +
                        "email text not null,\n" +
                        "create_time datetime not null,\n" +
                        "password text not null,\n" +
                        "constraint users_uuid_uindex\n" +
                        "unique (uuid)\n" +
                        ");\n");
    }
}
