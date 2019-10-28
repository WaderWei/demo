package wade.wei.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import wade.wei.entity.base.SysDept;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TreeDto {
    private Integer id;

    private String name;

    private Integer parentId;

    private Integer seq;

    private List<TreeDto> children = new ArrayList<>();

    public static TreeDto adapt(SysDept sysDept) {
        TreeDto treeDto = new TreeDto();
        BeanUtils.copyProperties(sysDept, treeDto);
        return treeDto;
    }
}
