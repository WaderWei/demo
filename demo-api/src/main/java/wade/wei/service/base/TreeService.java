package wade.wei.service.base;

import org.aspectj.weaver.ast.Var;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import wade.wei.dto.TreeDto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
@Service
public class TreeService {
    public List<TreeDto> GenerateTree(List<TreeDto> allDataList) {
        List<TreeDto> rootList = allDataList.stream()
                .filter(dto -> dto.getParentId().equals(0))
                .sorted(treeSortComparator)
                .collect(Collectors.toList());

        RecursiveList(allDataList, rootList);
        return rootList;
    }

    private void RecursiveList(List<TreeDto> allDataList, List<TreeDto> rootList) {
        for (TreeDto rootDto : rootList) {
            List<TreeDto> children = allDataList.stream()
                    .filter(dto -> dto.getParentId().equals(rootDto.getId()))
                    .sorted(treeSortComparator)
                    .collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(children)) {
                rootDto.setChildren(children);
                RecursiveList(allDataList, children);
            }
        }
    }

    private Comparator<TreeDto> treeSortComparator = new Comparator<TreeDto>() {
        @Override
        public int compare(TreeDto o1, TreeDto o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };
}
