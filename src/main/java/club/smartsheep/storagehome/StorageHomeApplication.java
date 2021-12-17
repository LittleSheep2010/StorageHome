package club.smartsheep.storagehome;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("club.smartsheep.storagehome")
@EnableCaching
public class StorageHomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(StorageHomeApplication.class, args);
    }

}
