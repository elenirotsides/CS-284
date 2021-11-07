package trees;

public class BSTree<Key extends Comparable<Key>, Value> {
	
	public class Node{
		private Key key;
		private Value value;
		private Node right;
		private Node left;
		private int size;
		
		public Node() {
			key = null;
			value = null;
			right = null;
			left = null;
			size = 1;
		}
		
		public Node(Key key, Value value, int size) {
			this.key = key;
			this.value = value;
			this.size = size;
			this.left = null;
			this.right = null;
		}
		
	}
	
	private Node root;
	
	public void inorder() {
		inorder(root);
	}
	
	private void inorder(Node node) {
		if(node != null) {
			inorder(node.left);
			System.out.println(node.key);
			inorder(node.right);
		}
	}
	
	public void insert(Key key, Value value) {
		if(key == null || value == null)
			throw new IllegalArgumentException("Key/Value is null!");
		root = insert(root, key, value);
	}
	
	private Node insert(Node x, Key key, Value value) {
		if (x == null)
			x = new Node(key, value, 1);
		//key < x.key -> -1, key > x.key -> 1, key = x.key -> 0
		int compare = key.compareTo(x.key);
		if(compare < 0) 
			x.left = insert(x.left, key, value);
		else if (compare > 0)
			x.right = insert(x.right, key, value);
		x.size = 1 + size(x.left) +  size(x.right);
		return x;
	}
	
	public Node search(Key key) {
		if (key == null)
			throw new IllegalArgumentException("Called with null key to search");
		return search(root, key);
	}
	
	private Node search(Node x, Key key) {
		if(x == null || x.key == key)
			return x;
		int compare = key.compareTo(x.key);
		if (compare < 0)
			return search(x.left, key);
		if (compare > 0)
			return search(x.right, key);
		return x;
	}
	
	public Node min(Node x) {
		if (x.left  == null) return x;
		else return min(x.left);
	}
	
	public Node minIter(Node x) {
		while (x != null && x.left != null) {
			x = x.left;
		}
		return x;
	}
	
	public Node max(Node x) {
		if(x.right == null) return x;
		else return max(x.right);
	}
	
	public Node predecessor(Node root, Key key) {
		Node x = search(key);
		if(x.left != null) {
			return max(x.left);
		}
		Node predecessor = null;
		while(root != null) {
			int compare = key.compareTo(root.key);
			if(compare < 0) {
				root = root.left;
			} else if(compare > 0) {
				predecessor = root;
				root = root.right;
			} else {
				break;
			}
		}
		return predecessor;
	}
	
	public Node successor(Node root, Key key) {
		Node x = search(key);
		if (x.right != null)
			return min(x.right);
		Node successor = null;
		while(root != null) {
			int compare = key.compareTo(root.key);
			if(compare < 0) {
				successor = root;
				root = root.left;
			}else if(compare > 0)
				root = root.right;
			else
				break;	
		}
		return successor;
	}
	
	public int size() {
		return size(root);
	}
	
	public int size(Node x) {
		if (x == null) return 0;
		else return x.size;
	}
	
	public void deleteMin() {
		root = deleteMin(root);
	}
	
	private Node deleteMin(Node x) {
		if (x.left == null) return x.right;
		x.left = deleteMin(x.left);
		x.size = size(x.left)+size(x.right)+1;
		return x;
	}
	
	public void delete(Key key) {
		if(key == null)
			throw new IllegalArgumentException("Called delete() with null key");
		root = delete(root, key);
	}
	
	private Node delete(Node x, Key key) {
		if(x == null)
			return null;
		int compare = key.compareTo(x.key);
		if (compare < 0)
			x.left = delete(x.left, key);
		else if (compare > 0)
			x.right = delete(x.right, key);
		else {
			if (x.right == null)
				return x.left;
			else if (x.left == null)
				return x.right;
			
			Node temp = x;
			x = min(temp.right);
			x.right = deleteMin(temp.right);
			x.left = temp.left;
		}
		x.size = size(x.left) +  size(x.right) + 1;
		return x;
	}
	
	public static void main(String[] args) {
		BSTree<Integer, Integer> btree = new BSTree<Integer, Integer>();
		int value = 1;
		btree.insert(22, value);
		btree.insert(10, value);
		btree.insert(30, value);
		btree.insert(3, value);
		btree.insert(12, value);
		btree.insert(5, value);
		btree.insert(28, value);
		btree.insert(34, value);
		btree.insert(25, value);
		btree.insert(29, value);
		
		System.out.println("In order Traversal Called:");
		btree.inorder();
		
		System.out.println("Delete 5 in the tree");
		btree.delete(22);
		btree.inorder();
	}
	

}
