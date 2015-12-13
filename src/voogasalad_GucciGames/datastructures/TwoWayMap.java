package voogasalad_GucciGames.datastructures;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TwoWayMap<K, V> implements Map<K, V> {

	private Map<K, V> myForwardMap;
	private Map<V, K> myReverseMap;

	public TwoWayMap() {
		myForwardMap = new HashMap<>();
		myReverseMap = new HashMap<>();
	}

	public TwoWayMap(int size) {
		myForwardMap = new HashMap<>(size);
		myReverseMap = new HashMap<>(size);
	}

	public V put(K k, V v) {
		myForwardMap.put(k, v);
		myReverseMap.put(v, k);
		return v;
	}

	@Override
	public void clear() {
		myForwardMap.clear();
		myReverseMap.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		return myForwardMap.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return myReverseMap.containsKey(value);
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return myForwardMap.entrySet();
	}

	@Override
	public V get(Object key) {
		return myForwardMap.get(key);
	}

	public K getKey(Object value) {
		return myReverseMap.get(value);
	}

	@Override
	public boolean isEmpty() {
		return myForwardMap.isEmpty() && myReverseMap.isEmpty();
	}

	@Override
	public Set<K> keySet() {
		return myForwardMap.keySet();
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		myForwardMap.putAll(m);
		m.keySet().stream().forEach((k) -> myReverseMap.put(m.get(k), k));
	}

	@Override
	public V remove(Object key) {
		V removed = myForwardMap.remove(key);
		myReverseMap.remove(removed);
		return removed;
	}

	public K removeKey(Object key) {
		K removed = myReverseMap.remove(key);
		myForwardMap.remove(removed);
		return removed;
	}

	@Override
	public int size() {
		return myForwardMap.size();
	}

	@Override
	public Collection<V> values() {
		return myForwardMap.values();
	}

}
