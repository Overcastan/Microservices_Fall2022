package org.example;

public class SingleFileStorageTest extends AbstractSingleFileStorageTest{
    @Override
    protected StorageImplementation<String, String> buildStringsStorage(String path) throws MalformedDataException {
        return new StorageImplementation<>(path);
    }

    @Override
    protected StorageImplementation<Integer, Double> buildNumbersStorage(String path) throws MalformedDataException {
        return new StorageImplementation<>(path);
    }

    @Override
    protected StorageImplementation<StudentKey, Student> buildPojoStorage(String path) throws MalformedDataException {
        return new StorageImplementation<>(path);
    }
}
