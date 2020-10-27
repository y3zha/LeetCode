package leetcode.interviewbook;

import java.util.LinkedList;
import java.util.Queue;

public class 面试题03_06_动物收容所 {

    class AnimalShelf {
        Queue<int[]> queue;

        public AnimalShelf() {
            queue = new LinkedList<>();
        }

        public void enqueue(int[] animal) {
            queue.offer(animal);
        }

        public int[] dequeueAny() {
            if(queue.isEmpty())
                return new int[]{-1,-1};
            return queue.poll();
        }

        public int[] dequeueDog() {
            for(int[] animal : queue)
                if(animal[1] == 1){
                    queue.remove(animal);
                    return animal;
                }
            return new int[]{-1, -1};
        }

        public int[] dequeueCat() {
            for(int[] animal : queue)
                if(animal[1] == 0){
                    queue.remove(animal);
                    return animal;
                }
            return new int[]{-1, -1};
        }
    }


}