//approach 1:

import java.util.ArrayDeque;
import java.util.Deque;

class MyQueue {
    Deque<Integer> s1,s2;
    public MyQueue() {
        s1= new ArrayDeque<>();
        s2= new ArrayDeque<>();
    }
    
    public void push(int x) {
        while(!s1.isEmpty()){
            s2.push(s1.pop());
        }
        s1.push(x);
        while(!s2.isEmpty()){
            s1.push(s2.pop());
        }
    }
    
    public int pop() {
        return s1.pop();
    }
    
    public int peek() {
        return s1.peek();
    }
    
    public boolean empty() {
        return s1.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */

//approach 2:

class MyQueue {
    Deque<Integer> s1,s2;
    public MyQueue() {
        s1= new ArrayDeque<>();
        s2= new ArrayDeque<>();
    }
    
    public void push(int x) {
        s1.push(x);
    }
    
    public int pop() {
        if(!s2.isEmpty()){
            return s2.pop();
        }else{
            while(!s1.isEmpty()){
                s2.push(s1.pop());
            }
            return s2.pop();

        }
    }
    
    public int peek() {
        if(!s2.isEmpty()){
            return s2.peek();
        }else{
            while(!s1.isEmpty()){
                s2.push(s1.pop());
            }
            return s2.peek();

        }
    }
    
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */