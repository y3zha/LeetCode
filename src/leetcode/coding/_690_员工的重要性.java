package leetcode.coding;

import java.util.*;

/**
 * 输入一个公司的所有员工信息，以及单个员工id，返回这个员工和他所有下属的重要度之和。
 */
public class _690_员工的重要性 {

    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

    //DFS
    Map<Integer, Employee> map; //id->employee
    public int getImportance(List<Employee> employees, int id) {
        if (employees == null) {
            return 0;
        }
        map = new HashMap<>();
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        return dfs(id);
    }
    private int dfs(int id) {
        Employee employee = map.get(id);
        int res = employee.importance;
        for (Integer subordinate : employee.subordinates) {
            res += dfs(subordinate);
        }
        return res;
    }

    //BFS
    public int getImportance2(List<Employee> employees, int id) {
        if (employees == null) {
            return 0;
        }
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee employee : employees) {
            map.put(employee.id, employee);
        }
        Queue<Employee> queue = new LinkedList<>();
        queue.offer(map.get(id));
        int res = 0;
        while (!queue.isEmpty()) {
            Employee e = queue.poll();
            res += e.importance;
            for (Integer subordinateId : e.subordinates) {
                queue.offer(map.get(subordinateId));
            }
        }
        return res;
    }

}