package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.List;

/**
 * Handles file-based persistence of inventory and booking history.
 *
 * @author ZeroTrace7
 * @version 12.0
 */
public class PersistenceService {
    private final File file;

    public PersistenceService(String filePath) {
        this.file = new File(filePath);
    }

    public void save(PersistenceData data) throws IOException {
        if (data == null) {
            throw new IllegalArgumentException("data is required");
        }
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(data);
        }
    }

    public PersistenceData load() throws IOException {
        if (!file.exists()) {
            return new PersistenceData(Collections.emptyMap(), List.of());
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = in.readObject();
            if (obj instanceof PersistenceData) {
                return (PersistenceData) obj;
            }
        } catch (ClassNotFoundException | IOException ex) {
            return new PersistenceData(Collections.emptyMap(), List.of());
        }
        return new PersistenceData(Collections.emptyMap(), List.of());
    }
}

