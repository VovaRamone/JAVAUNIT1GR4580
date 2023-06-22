
class HashMap {
    class Entity {
        int key;
        int value;
    }

    class Basket {
        Node head;

        class Node {
            Entity entity;
            Node next;
        }

        private boolean push(Entity entity) {
            Node node = new Node();
            node.entity = entity;

            if (head == null) {
                head = node;
            } else {
                Node cur = head;
                while (cur != null) {
                    if (cur.entity.key == entity.key) {
                        return false;
                    }
                    if (cur.next == null) {
                        cur.next = node;
                        return true;
                    }
                    cur = cur.next;
                }
            }

            return true;
        }

        private Integer find(int key) {
            Node cur = head;
            while (cur != null) {
                if (cur.entity.key == key) {
                    return cur.entity.value;
                }
                cur = cur.next;
            }
            return null;
        }

        private boolean remove(int key) {
            if (head != null) {
                if (head.entity.key == key) {
                    head = head.next;
                    return true;
                } else {
                    Node cur = head;
                    while (cur.next != null) {
                        if (cur.next.entity.key == key) {
                            cur.next = cur.next.next;
                            return true;
                        }
                        cur = cur.next;
                    }
                }
            }
            return false;
        }
    }

    static final int INIT_SIZE = 16;
    Basket[] baskets;

    public HashMap() {
        this(INIT_SIZE);
    }

    public HashMap(int size) {
        baskets = new Basket[size];
    }

    private int getIndex(int key) {
        return (key % baskets.length + baskets.length) % baskets.length;
    }

    public boolean push(int key, int value) {
        int index = getIndex(key);
        Basket basket = baskets[index];
        if (basket == null) {
            basket = new Basket();
            baskets[index] = basket;
        }
        Entity entity = new Entity();
        entity.key = key;
        entity.value = value;
        return basket.push(entity);
    }

    public Integer find(int key) {
        int index = getIndex(key);
        Basket basket = baskets[index];
        if (basket != null) {
            return basket.find(key);
        }
        return null;
    }

    public boolean remove(int key) {
        int index = getIndex(key);
        Basket basket = baskets[index];
        if (basket != null) {
            return basket.remove(key);
        }
        return false;
    }

    public void test() {
        System.out.println("----- Testing HashMap -----");
        System.out.println("Finding elements:");
        System.out.println("Key: -1, Value: " + find(-1));
        System.out.println("Key: 17, Value: " + find(17));
        System.out.println("Key: 3, Value: " + find(3));
        System.out.println("Key: 5, Value: " + find(5));
        System.out.println("Removing element with key 17...");
        remove(17);
        System.out.println("Key: 17, Value: " + find(17));
        System.out.println();
    }
}

class RedBlackTree {
    private Node root;

    class Node {
        int value;
        Node left;
        Node right;
        Color color;
    }

    enum Color {
        BLACK,
        RED
    }

    public void insert(int value) {
        if (root == null) {
            root = new Node();
            root.value = value;
            root.color = Color.BLACK;
        } else {
            insert(root, value);
            root = balance(root);
        }
    }

    private Node balance(Node root) {
        if (isRed(root.right) && !isRed(root.left)) {
            root = rotateLeft(root);
        }
        if (isRed(root.left) && isRed(root.left.left)) {
            root = rotateRight(root);
        }
        if (isRed(root.left) && isRed(root.right)) {
            flipColors(root);
        }
        return root;
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        }
        return node.color == Color.RED;
    }

    private Node rotateLeft(Node node) {
        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        newRoot.color = node.color;
        node.color = Color.RED;
        return newRoot;
    }

    private Node rotateRight(Node node) {
        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        newRoot.color = node.color;
        node.color = Color.RED;
        return newRoot;
    }

    private void flipColors(Node node) {
        node.color = Color.RED;
        node.left.color = Color.BLACK;
        node.right.color = Color.BLACK;
    }

    private void insert(Node node, int value) {
        if (node.value != value) {
            if (node.value < value) {
                if (node.right == null) {
                    node.right = new Node();
                    node.right.value = value;
                    node.right.color = Color.RED;
                } else {
                    insert(node.right, value);
                }
            } else {
                if (node.left == null) {
                    node.left = new Node();
                    node.left.value = value;
                    node.left.color = Color.RED;
                } else {
                    insert(node.left, value);
                }
            }
        }
    }

    public void test() {
        System.out.println("----- Testing Red-Black Tree -----");
        System.out.println("Inserting elements: 1, 2, 3, 4, 5");
        insert(1);
        insert(2);
        insert(3);
        insert(4);
        insert(5);
        System.out.println("Tree structure:");
        printTree(root, 0);
        System.out.println();
    }

    private void printTree(Node node, int level) {
        if (node == null) {
            return;
        }
        printTree(node.right, level + 1);
        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
        System.out.println(node.value + " (" + (node.color == Color.RED ? "RED" : "BLACK") + ")");
        printTree(node.left, level + 1);
    }
}

public class Final {
    public static void main(String[] args) {
        HashMap map = new HashMap();

        map.push(-1, 2);
        map.push(17, 3);
        map.push(3, 4);

        System.out.println("HashMap:");
        map.test();

        RedBlackTree tree = new RedBlackTree();
        tree.test();
    }
}