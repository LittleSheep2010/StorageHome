package club.smartsheep.storagehome.DAO.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.util.List;

@Data
@TableName("storage_region")
public class StorageRegionEntity {

    @TableId(type = IdType.AUTO)
    Long id;

    String name;

    @TableField(typeHandler = JacksonTypeHandler.class)
    List<String> position;
}
