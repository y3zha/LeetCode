package leetcode.coding;

import java.util.HashMap;
import java.util.Map;

public class _1166_设计文件系统 {

    class FileSystem {

        // 路径 -> 值
        Map<String, Integer> map;

        public FileSystem() {
            map = new HashMap<>();
            // 缓存根节点路径
            map.put("", -1);

        }

        public boolean createPath(String path, int value) {
            if (map.containsKey(path)) return false;
            // 获取父路径
            int lastIndex = path.lastIndexOf("/");
            String parentPath = path.substring(0, lastIndex);
            // 父路径不存在，则返回false
            if (!map.containsKey(parentPath)){
                return false;
            }
            map.put(path,value);
            return true;

        }

        public int get(String path) {
            return map.getOrDefault(path,-1);
        }
    }
}