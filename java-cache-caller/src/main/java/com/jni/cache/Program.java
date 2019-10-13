package com.jni.cache;

/**
 * Main class to demonstrate the cache example.
 *
 * @author Yasitha Thilakaratne
 */
public class Program {

    public static void main(String... args) throws InterruptedException {

        Cache<Model> cache = new Cache<>(); // Initiating the cache entity.
        cache.add(new Model(1, "Model 1", true), 1); // Adding entries to the cache
        cache.add(new Model(2, "Model 2", false), 2);
        cache.add(new Model(3, "Model 3", true), 3);

        System.out.println("Java log: Name for key [2] -> " + cache.get(2).getName()); // Retrieving from the cache
        System.out.println("Java log: Name for key [3] -> " + cache.get(3).getName());
        System.out.println("Java log: Name for key [2] -> " + cache.get(2).getName());
        Model obj100 = cache.get(100);
        System.out.println("Java log: Value for key [100] -> " + (obj100 == null ? "NULL" : obj100.getName())); // Retrieving unavailable record from the cache

        Thread t = new Thread(() -> {
            System.out.println("Java log: Name for key [1] -> " + cache.get(1).getName()); // Retrieving with another thread
        });
        t.start();

        Thread.sleep(500);

        cache.delete(1); // Remove from cache + deallocate memory
        cache.delete(2);
        cache.delete(3);

        Model obj1 = cache.get(1);
        System.out.println("Java log: Value for key [1] -> " + (obj1 == null ? "NULL" : obj1.getName())); // Trying to retrieve after removing from the cache
    }
}
