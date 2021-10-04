package hashmap;

public class HashPair<K, V> {

	private K key;
	private V value;
	protected HashPair<K, V> next;
	
	public HashPair(K key, V value) {
		this.key = key;
		this.value = value;
		this.next = null;
	}
	
	protected void setNext(HashPair<K, V> next) {
		this.next = next;
	}
	
	protected K getKey() {
		return this.key;
	}
	
	protected V getValue() {
		return this.value;
	}

	public void setValue(V val) {
		this.value = val;
	}
	
}
