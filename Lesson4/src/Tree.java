public class Tree {
    public final int NUMBER_OF_TREE_NODES = 20;

    public class RedBlackTree {
        Node root;
        private final int BLACK = 0;
        private final int RED = 1;

        private class Node {
            int value;
            Node left;
            Node right;
            Node parent;
            int color;

            Node(int value) {
                this.value = value;
                this.color = RED;
            }
        }

        public boolean find(int Value) {
            Node current = root;
            while (current != null) {
                if (current.value == Value)
                    return true;

                if (Value < current.value) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }
            return false;
        }

        public void push(int value) {
            Node newNode = new Node(value);
            if (root == null) {
                root = newNode;
                root.color = BLACK;
            } else {
                Node parent = null;
                Node current = root;
                while (true) {
                    parent = current;
                    if (value < current.value) {
                        current = current.left;
                        if (current == null) {
                            parent.left = newNode;
                            newNode.parent = parent;
                            break;
                        }
                    } else {
                        current = current.right;
                        if (current == null) {
                            parent.right = newNode;
                            newNode.parent = parent;
                            newNode.color = BLACK;
                            break;
                        }
                    }
                }
            }
            balanceInsertion(newNode);
        }

        public void prettyPrintTree() {
            prettyPrintTree(this.root, "", true);
        }

        private void prettyPrintTree(Node node, String indent, boolean isRight) {
            if (node != null) {
                System.out.println(indent
                        + (isRight ? " /-- " : " \\-- ")
                        + "(" + (node.color == RED ? "R" : "B") + ")"
                        + node.value);
                prettyPrintTree(node.left, indent + (isRight ? " |   " : "     "), false);
                prettyPrintTree(node.right, indent + (isRight ? "     " : " |   "), true);
            }
        }

        private Node rotateLeft(Node node) {
            Node rightNode = node.right;
            node.right = rightNode.left;
            if (node.right != null)
                node.right.parent = node;
            rightNode.parent = node.parent;
            if (node.parent == null)
                root = rightNode;
            else if (node == node.parent.left)
                node.parent.left = rightNode;
            else
                node.parent.right = rightNode;
            rightNode.left = node;
            node.parent = rightNode;
            return rightNode;
        }

        private Node rotateRight(Node node) {
            Node leftNode = node.left;
            node.left = leftNode.right;
            if (node.left != null)
                node.left.parent = node;
            leftNode.parent = node.parent;
            if (node.parent == null)
                root = leftNode;
            else if (node == node.parent.right)
                node.parent.right = leftNode;
            else
                node.parent.left = leftNode;
            leftNode.right = node;
            node.parent = leftNode;
            return leftNode;
        }

        private void balanceInsertion(Node node) {
            node.color = RED;
            while (node != null && node != root && node.parent.color == RED) {
                if (node == node.parent.left) {
                    Node y = rightOf(parentOf(parentOf(node)));
                    if (colorOf(y) == RED) {
                        setColor(parentOf(node), BLACK);
                        setColor(y, BLACK);
                        setColor(parentOf(parentOf(node)), RED);
                        node = parentOf(parentOf(node));
                    } else {
                        if (node == rightOf(parentOf(node))) {
                            node = parentOf(node);
                            rotateLeft(node);
                        }
                        setColor(parentOf(node), BLACK);
                        setColor(parentOf(parentOf(node)), RED);
                        if (parentOf(parentOf(node)) != null)
                            rotateRight(parentOf(parentOf(node)));
                    }
                } else {
                    Node y = leftOf(parentOf(parentOf(node)));
                    if (colorOf(y) == RED) {
                        setColor(parentOf(node), BLACK);
                        setColor(y, BLACK);
                        setColor(parentOf(parentOf(node)), RED);
                        node = parentOf(parentOf(node));
                    } else {
                        if (node == leftOf(parentOf(node))) {
                            node = parentOf(node);
                            rotateRight(node);
                        }
                        setColor(parentOf(node), BLACK);
                        setColor(parentOf(parentOf(node)), RED);
                        if (parentOf(parentOf(node)) != null)
                            rotateLeft(parentOf(parentOf(node)));
                    }
                }
            }
            root.color = BLACK;
        }

        private Node parentOf(Node node) {
            return node == null ? null : node.parent;
        }

        private int colorOf(Node node) {
            return node == null ? BLACK : node.color;
        }

        private Node leftOf(Node node) {
            return node == null ? null : node.left;
        }

        private Node rightOf(Node node) {
            return node == null ? null : node.right;
        }

        private void setColor(Node node, int color) {
            if (node != null)
                node.color = color;
        }
    }


    public static void main(String[] args) {
        Tree anyTree = new Tree();
        RedBlackTree tree = anyTree.new RedBlackTree();

        for (int i = 1; i <= anyTree.NUMBER_OF_TREE_NODES; i++) {
            tree.push(i);
            tree.prettyPrintTree();
            System.out.println();
        }

        for (int i = 1; i <= anyTree.NUMBER_OF_TREE_NODES + 2; i++) {
            if (tree.find(i)) {
                System.out.println(i + " found in tree.");
            } else {
                System.out.println(i + " not found in tree.");
            }
        }
    }
}
