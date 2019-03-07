package hu.zsofi.test.jiraproject;

import java.util.HashMap;

public class StringIntMap<String, Integer> extends HashMap {
    public StringIntMap() {
    }

    public StringIntMap(String[] keysOrdered, Integer[] valuesOrdered) throws IllegalArgumentException {
        if (keysOrdered.length != valuesOrdered.length) {
            throw new IllegalArgumentException("Length of keys and values does not match");
        }

        for (int i = 0; i < keysOrdered.length; i++) {
            this.put(keysOrdered[i], valuesOrdered[i]);
        }
    }

    public void incrementValueByKey(String key) {
        if (this.containsKey(key)) {
            Object value = this.get(key);
            this.put(key, (int) value + 1);
        } else {
            this.put(key, 1);
        }
    }
}
