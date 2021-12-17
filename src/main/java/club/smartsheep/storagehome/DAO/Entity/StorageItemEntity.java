package club.smartsheep.storagehome.DAO.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("storage_items")
public class StorageItemEntity {

    @TableId(type = IdType.AUTO)
    Long id;

    Long regionId;

    String name;
    String type;

    Long ownerId;
}
