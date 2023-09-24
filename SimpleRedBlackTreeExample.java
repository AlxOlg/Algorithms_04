package Algorithms_04;
enum Color {
    RED,
    BLACK
}

class SimpleNode {
    int value;
    Color color;
    SimpleNode left;
    SimpleNode right;
    
    public SimpleNode(int value) {
        this.value = value;
        this.color = Color.RED; // Новый - красный
        this.left = null;
        this.right = null;
    }
}

public class SimpleRedBlackTreeExample {
    private SimpleNode root;

    public void insert(int value) {
        root = insertRecursive(root, value);
        root.color = Color.BLACK; // Корень всегда черный
    }

    private SimpleNode insertRecursive(SimpleNode node, int value) {
        if (node == null) {
            return new SimpleNode(value);
        }

        if (value < node.value) {
            node.left = insertRecursive(node.left, value);
        } else if (value > node.value) {
            node.right = insertRecursive(node.right, value);
        } else {
            return node;
        }

        // Правила балансировки
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            colorSwap(node);
        }

        return node;
    }

    // Boolean значение цвета
    private boolean isRed(SimpleNode node) {
        if (node != null && node.color == Color.RED) {
            return true;
        }
        return false;
    }

    // Правый поворот
    private SimpleNode rotateRight(SimpleNode node) {
        SimpleNode right = node.right;
        node.right = right.left;
        right.left = node;
        right.color = node.color;
        node.color = Color.RED;
        return right;
    }

    // Левый поворот
    private SimpleNode rotateLeft(SimpleNode node) {
        SimpleNode left = node.left;
        node.left = left.right;
        left.right = node;
        left.color = node.color;
        node.color = Color.RED;
        return left;
    }

    // Смена цвета
    private void colorSwap(SimpleNode node) {
        node.color = Color.RED;
        node.left.color = Color.BLACK;
        node.right.color = Color.BLACK;
    }
    
    // Вспомогательный метод для вывода дерева
    public void printTree() {
        printTreeRecursive(root, "");
    }

    // Вывод дерева в консоль
    public static void printTreeRecursive(SimpleNode node, String prefix) {
        if (node != null) {
            System.out.println(prefix + node.value + "(" + node.color + ")");
            printTreeRecursive(node.left, prefix + "-");
            printTreeRecursive(node.right, prefix + "-");
        }
    }
    
    public static void main(String[] args) {
        SimpleRedBlackTreeExample tree = new SimpleRedBlackTreeExample();

        tree.insert(10);
        tree.insert(1);
        tree.insert(7);
        tree.insert(14);
        tree.insert(9);
        tree.insert(11);
        tree.insert(2);
        tree.insert(13);
        tree.insert(4);
        tree.insert(15);
        tree.insert(6);
        tree.insert(12);
        tree.insert(8);
        tree.insert(3);
        tree.insert(5);

        tree.printTree();
    }
}
