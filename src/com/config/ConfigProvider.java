package com.config;

import java.io.*;

public class ConfigProvider {
    private String filePath;

    public void dump(ConfigModel config) throws Exception {
        ObjectOutputStream out =
                new ObjectOutputStream(new FileOutputStream(filePath));
        out.writeObject(config);
        out.flush();
        out.close();
    }

    public Serializable load() throws Exception {
        ObjectInputStream in =
                new ObjectInputStream(new FileInputStream(filePath));
            return (Serializable) in.readObject();
    }

    public ConfigProvider(String filePath) {
        this.filePath = filePath;
    }
}
