package hashmap;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Implementing a hash map that accepts key-value pairs.
 * 
 * @author Jolene
 *
 * @param <K> key
 * @param <V> value
 */
public class MyHashMap<K, V> {

	private HashPair<K, V>[] list;
	private int capacity; //number of buckets in hash table
	private int size;
	private final static double loadFactor = 0.75;
	
	public MyHashMap() {
		this.size = 0;
		this.capacity = 127;
		this.list = new HashPair[capacity];
	}
	
	//Return number of key-value mappings
	public int size() {
		return size;
	}
	
	//Return true if no key-value mappings
	public boolean isEmpty() {
		return (size == 0);
	}
	
	/**
	 * Returns corresponding value for specified key
	 * @return null if no mapping for key, value otherwise
	 */
	public V get(K key) {
		int idx = hash(key, capacity);
		V getVal = null;
		
		if (list[idx] == null) {
			return null;
		//In case there is a collision
		} else if (list[idx].next != null) {
			HashPair<K, V> curr = list[idx];
			do {
				if (curr.getKey() == key) {
					return curr.getValue();
				}
				curr = curr.next;
			} while (curr.next != null);
		} else {
			getVal = list[idx].getValue();
		}
		
		return getVal;
	}
	
	/**
	 * Associates or inserts key-value pair in map.
	 * @param ins
	 * @return the old value if replaced, null if there was no value
	 * 	previously associated or no mapping done
	 *  (am I understanding the docs right?)
	 */
	public V put(K key, V value) {
		int idx = hash(key, capacity);
		HashPair<K, V> ins = new HashPair<K, V>(key, value);  
		
		//Handling a bucket with >1 element
		if (list[idx] != null) {
			HashPair<K, V> curr = list[idx];
			
			//Check if key already exists
			do {
				//Case where the same key is found
				if (curr.getKey() == key) {
					//If duplicate entry found, don't do anything.
					if (curr.getValue() == value) {
						return null;
					//If same key, then replace old value
					} else if (curr.getValue() != value) {
						V oldVal = curr.getValue();
						curr.setValue(value);
						return oldVal;
					}
				}
				curr = curr.next;
			} while (curr.next != null);
			
			//No existing key found, then add to tail
			if (Math.floor(size + 1/ capacity) == loadFactor) {
				resize();
			}
			curr.setNext(ins);
			size++;
			return null;
			
		//Resize list if loading factor reached
		} else {
			if (Math.floor(size + 1/ capacity) == loadFactor) {
				resize();
			}
			list[idx] = ins;
			size++;
			return null;
		}
	}

	
	//-------------------------TODO-------------------------//
	/*
	public void putAll(Map<K, V> map) {
		for (int i = 0; i < map.size(); i++) {
			
		}
	}
	
	public V remove(K key) {
		return null;
		
	}
	
	public void clear() {
		
	}
	
	public boolean containsValue(V value) {
	}
	
	public Set<K> keySet() {
		
	}
	
	public Collection<V> values() {
		
	}
	
	public Set<Map, Entry<K, V>> entrySet() {
		
	}
	
	public V getOrDefault(K key, V defaultValue) {
		
	}
	
	public V putIfAbsent(K key, V value) {
		
	}
	
	public boolean remove(K key, V value) {
		
	}
	
	public boolean replace(K key, V oldValue, V newValue) {
		
	}
	
	public V replace(K key, V value) {
		
	}
	
	public V computeIfAbsent(K key,
            Function<? super K,? extends V> mappingFunction) {}
	
	public V computeIfPresent(K key,
            BiFunction<? super K,? super V,? extends V> remappingFunction) {}
	
	public V compute(K key,
            BiFunction<? super K,? super V,? extends V> remappingFunction) {}
	
	public V merge(K key,
            V value,
            BiFunction<? super V,? super V,? extends V> remappingFunction) {}
	
	public void forEach(BiConsumer<? super K,? super V> action) {}
	
	public void replaceAll(BiFunction<? super K,? super V,? extends V> function) {}
	
	public Object clone() {}
	*/
	
	//-------------------------private methods-------------------------//
	private int hash(K key, int capacity) {
		int hashedKey = key.hashCode();
		return hashedKey % capacity;
	}
	
	/**
	 * Resize the list to twice its capacity.
	 * Remaps all key-value pairs.
	 */
	private void resize() {
		int newCapacity = capacity * 2;
		HashPair<K, V>[] newList = new HashPair[2 * newCapacity];
		
		//Go through original list for mappings to move over to new list
		for (int i = 0; i < capacity; i++) {
			if (list[i] != null ) {
				
				HashPair<K, V> curr = list[i]; //elem to insert
				//rehash to update index
				int newInsertIdx = hash(curr.getKey(), newCapacity); 
				
				//If bucket in old map has chaining, make sure to 
				//rehash and add all the elements in the chain
				if (curr.next != null) {
					do {
						insertAt(newInsertIdx, newList, curr);
						curr = curr.next;
					} while (curr.next != null);
				} else {
					insertAt(newInsertIdx, newList, curr);
				}
			}
		}
		this.list = newList;
	}
	
	/**
	 * Attemps to insert key-value pair into index in list. 
	 * If key already exists, update value.
	 * @param curr pair to insert
	 */
	private void insertAt(int index, HashPair[] list, HashPair<K, V> insElem) {
		HashPair<K, V> listElem = list[index];
		
		if (listElem != null) {
			//Check for duplicate key, if so, update value
			do {
				if (insElem.getKey() == listElem.getKey()) {
					listElem.setValue(insElem.getValue());
					return;
				} 
			} while (listElem.next != null);
			
			//No duplicate key found
			HashPair<K, V> last = getLast(listElem);
			last.setNext(insElem);
		} else {
			list[index] = insElem;
		}
	}
	
	private HashPair<K, V> getLast(HashPair<K, V> current) {
		do {
			current = current.next;
		} while (current.next != null);
		
		return current;
	}
}
