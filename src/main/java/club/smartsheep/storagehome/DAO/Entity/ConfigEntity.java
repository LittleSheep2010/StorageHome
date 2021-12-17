package club.smartsheep.storagehome.DAO.Entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("configs")
@AllArgsConstructor
@NoArgsConstructor
public class ConfigEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;
    private String value;
}
