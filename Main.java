class List{
    static Node head;
    static class Node{
        int value;
        Node next;
    }

    public static void pushFront(int value){
        Node node = new Node();
        node.value = value;
        node.next = head;
        head = node;
    }

    public static void popFront(){
        if(head != null) {
            head = head.next;
        }
    }

    public static void print(){
        Node cur = head;
        while(cur != null){
            System.out.printf("%d ", cur.value);
            cur = cur.next;
        }
        System.out.println();
    }

    public static boolean contains(int value){
        Node cur = head;
        while(cur != null){
            if(cur.value == value){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public static void pushBack(int value){
        Node node = new Node();
        node.value = value;

        if(head == null){
            head = node;
        }else{
            Node cur = head;
            while(cur.next != null){
                cur = cur.next;
            }
            cur.next = node;
        }
    }

    public static void popBack(){
        if(head != null) {
            if(head.next == null){
                head = null;
            }else{
                Node cur = head;
                while(cur.next.next != null){
                    cur = cur.next;
                }
                cur.next = null;
            }
        }
    }
}

class dList{
    static Node head;
    static Node tail;
    static class Node{
        int value;
        Node next;
        Node prev;
    }

    public static void pushFront(int value){
        Node node = new Node();
        node.value = value;

        if(head == null){
            tail = node;
        }else{
            node.next = head;
            head.prev = node;
        }
        head = node;
    }

    public static void popFront(){
        if(head != null) {
            if(head.next == null){
                head = null;
                tail = null;
            }else {
                head = head.next;
                head.prev = null;
            }
        }
    }

    public static void print(){
        Node cur = head;
        while(cur != null){
            System.out.printf("%d ", cur.value);
            cur = cur.next;
        }
        System.out.println();
    }

    public static boolean contains(int value){
        Node cur = head;
        while(cur != null){
            if(cur.value == value){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public static void pushBack(int value){
        Node node = new Node();
        node.value = value;

        if(tail == null){
            head = node;
        }else{
            node.prev = tail;
            tail.next = node;
        }
        tail = node;
    }

    public static void popBack(){
        if(tail != null) {
            if(tail.prev == null){
                head = null;
                tail = null;
            }else {
                tail = tail.prev;
                tail.next = null;
            }
        }
    }

    public static void sort(){
        boolean needSort = true;
        do{
            needSort = false;
            Node node = head;
            while(node != null && node.next != null){
                if(node.value > node.next.value){
                    Node before = node.prev;
                    Node cur = node;
                    Node next = node.next;
                    Node after = next.next;

                    cur.next = after;
                    cur.prev = next;
                    next.next = cur;
                    next.prev = before;

                    if(before != null) {
                        before.next = next;
                    }else{
                        head = next;
                    }

                    if(after != null) {
                        after.prev = cur;
                    }else{
                        tail = cur;
                    }

                    needSort = true;
                }
                node = node.next;
            }

        }while(needSort);
    }
    public static void reverse() {
        Node current = head;
        Node temp = null;

        while (current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;

            current = current.prev;
        }

        if (temp != null) {
            head = temp.prev;
        }
    }
}
public class Main {
    public static void main(String[] args) {
        dList list = new dList();
        for(int i=1; i<=10; i++){
            list.pushFront(i);
        }

        list.print();

        list.popFront();
        list.popFront();

        list.print();

        list.pushBack(7);

        list.print();

        list.popBack();

        list.sort();

        list.print();

        list.reverse();

        list.print();
    }
}
