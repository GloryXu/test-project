package codes.chapter11.two.four;

/**
 * 二叉链表存储的思想是让每个节点都能“记住”它的左右两个子节点。
 * 为每个节点增加left、right两个指针，分别引用该节点的左右两个子节点。
 */
class Node<T> {
    T data;
    Node left;
    Node right;
}

public class TwoLinkBinTree<E> {
    public static class TreeNode {
        Object data;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(Object data) {
            this.data = data;
        }

        public TreeNode(Object data, TreeNode left, TreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
    private TreeNode root;
    // 以默认的构造器创建二叉树
    public TwoLinkBinTree() {
        this.root = new TreeNode();
    }
    // 以指定根元素创建二叉树
    public TwoLinkBinTree(E data) {
        this.root = new TreeNode(data);
    }

    public TreeNode addNode(TreeNode parent, E data, Boolean isLeft) {
        if(parent == null) {
            throw new RuntimeException(parent + "节点null，无法添加子节点");
        }
        if (isLeft && parent.left != null) {
            throw new RuntimeException(parent + "节点已有左子节点，无法添加左子节点");
        }
        if (!isLeft && parent.right != null) {
            throw new RuntimeException(parent + "节点已有右子节点，无法添加右子节点");
        }
        TreeNode newNode = new TreeNode(data);
        if(isLeft) {
            // 让父节点的left引用指向新节点
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        return newNode;
    }
    // 判断二叉树是否为空
    public boolean empty() {
        // 根据根元素判断二叉树是否为空
        return root.data == null;
    }
    // 返回根节点
    public TreeNode root() {
        if(empty()) {
            throw new RuntimeException("树为空，无法访问根节点");
        }
        return root;
    }
    // 返回指定节点（非根节点）的父节点
    public E parent(TreeNode node) {
        // 对于二叉链表存储法，如果要访问指定节点的父节点必须遍历二叉树
        return null;
    }

    // 返回指定节点（非叶子）的左子节点，当左子节点不存在时返回null
    public E leftChild(TreeNode parent) {
        if (parent == null) {

        }
    }
}
