package trees;

public class BTree<E> {
	public static class Node<F>{
		// data fields
		private F data;
		private Node<F> left;
		private Node<F> right;
		
		// constructors
		public Node(F data, Node<F> left, Node<F> right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
		
		public Node(F data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}
	}
	
	// data fields
	private Node<E> root;
	private int size; // number of nodes
	
	// constructors
	public BTree() {
		root = null;
		size = 0;
	}
	
	public BTree(E data) {
		root = new Node<E>(data);
		size = 1;
	}
	
	public BTree(E data, BTree<E> leftTree, BTree<E> rightTree) {
		root = new Node<E>(data);
		
		if(leftTree != null) {
			root.left = leftTree.root;
		} else {
			root.left = null;
		}
		
		if(rightTree != null) {
			root.right = rightTree.root;
		} else {
			root.right = null;
		}
		//size = 1 + size(leftTree) + size(rightTree)
		size = 1 + leftTree.size + rightTree.size;
	}
	
	public BTree(Node<E> root){
		this.root = root;
		//size = 1 + size(root.left) + size(root.right);
	}
	
	// methods
	public BTree<E> getLeftSubTree(){
		if(root != null && root.left != null) {
			return new BTree<E>(root.left);
		}
		return null;
	}
	
	public void preOrder(Node<E> node) {
		if(node == null) {
			return;
		}
		System.out.println(node.data);
		preOrder(node.left);
		preOrder(node.right);
	}
	
	public void postOrder(Node<E> node) {
		if(node == null) {
			return;
		}
		postOrder(node.left);
		postOrder(node.right);
		System.out.println(node.data);
	}
	
	public void inOrder(Node<E> node) {
		if(node == null) {
			return;
		}
		inOrder(node.left);
		System.out.println(node.data);
		inOrder(node.right);
	}
	
	public int size(Node<E> node) {
		if(node == null) {
			return 0;
		}
		return 1 + size(node.left) + size(node.right);
	}
	
	public boolean isLeaf(Node<E> node) {
		if(node == null) {
			return false;
		} else {
			if(node.left == null && node.right == null) {
				return true;
			}
		}
		return false;
	}
	
	public String toString(Node<E> current, int level) {
		StringBuilder s = new StringBuilder();
		
		for(int i = 0; i < level; i++) {
			s.append("-");
		}
		
		if(current == null) {
			s.append("null\n");
		} else {
			s.append(current.data.toString() + "\n");
			s.append(toString(current.left, level + 1));
			s.append(toString(current.right, level + 1));
		}
		return s.toString();
	}
	
	public static void main(String[] args) {
		BTree<Integer> t1 = new BTree<>(7, new BTree<>(), new BTree<>());
		BTree<Integer> t2 = new BTree<>(33, new BTree<>(27, new BTree<>(), new BTree<>()), new BTree<>());
		BTree<Integer> t3 = new BTree<>(23, t1, t2);
		
		System.out.println(t3.toString(t3.root, 1));
	}
}
