package org.example;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.HashMap;
import java.util.Iterator;

public class StorageImplementation<K, V> implements KeyValueStorage<K, V> {
    private HashMap<K, V> storage;
    private Path directory_path;
    private Path file_path;
    private String storage_name = "My_storage";

    public StorageImplementation(String path) {
        this.directory_path = Paths.get(path);
        this.storage = new HashMap<>();

        if (!Files.exists(this.directory_path)) {
            throw new MalformedDataException("Directory does not exist");
        }

        this.file_path = Paths.get(path, storage_name);
        if (!Files.exists(file_path)) {
            try {
                Files.createFile(file_path);
            } catch(IOException e) {
                throw new MalformedDataException(e);
            }
        } else {
            uploadStorage();
        }

    }

    @Override
    public V read(K key) {
        return this.storage.get(key);
    }

    @Override
    public boolean exists(K key) {
        return this.storage.containsKey(key);
    }

    @Override
    public void write(K key, V value) {
        this.storage.put(key, value);
    }

    @Override
    public void delete(K key) {
        this.storage.remove(key);
    }

    @Override
    public Iterator<K> readKeys() {
        return this.storage.keySet().iterator();
    }

    @Override
    public int size() {
        return this.storage.size();
    }

    @Override
    public void flush() {
        try (FileOutputStream out = new FileOutputStream(file_path.toString()); ObjectOutputStream objOut = new ObjectOutputStream(out)) {
            objOut.writeObject(this.storage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws IOException {
        flush();
        this.storage = null;
    }

    private void uploadStorage() {
        try (InputStream in = Files.newInputStream(file_path); ObjectInputStream objectInput = new ObjectInputStream(in)) {
            Object storage_object = objectInput.readObject();
            this.storage = (HashMap<K, V>) storage_object;
        } catch (IOException | ClassNotFoundException e) {
            throw new MalformedDataException(String.valueOf(e));
        }
    }
}
