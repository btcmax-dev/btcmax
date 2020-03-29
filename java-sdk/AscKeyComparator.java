import java.util.*;

/**
 * 对HashMap进行排序
 */
public class AscKeyComparator implements Comparator<Map.Entry<String, String>> {
    @Override
    public int compare(Map.Entry<String, String> item1, Map.Entry<String, String> item2) {
        return item1.getKey().compareTo(item2.getKey());    //升序排序
    }

}
