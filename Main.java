import hashmap.HashPair;
import hashmap.MyHashMap;

public class Main {

	public static void main(String[] args) {
		//Generate some bananas
		Banana ring1 = new Banana();
		Banana ring2 = new Banana();
		Banana ring3 = new Banana();
		Banana ring4 = new Banana();
		Banana ring5 = new Banana();
		Banana ring6 = new Banana();
		
		ArrList<Banana> bananaPhone = new ArrList<>();
		bananaPhone.add(ring1);
		bananaPhone.add(ring2);
		bananaPhone.add(ring3);
		bananaPhone.set(0, ring6);
		//System.out.println(bananaPhone.contains(ring6));
		
		MyHashMap<Integer> hashMap = new MyHashMap<>();
		HashPair testBucket = new HashPair();
		int printHash = hashMap.hash(ring5);
		System.out.println(printHash);
	}

}
