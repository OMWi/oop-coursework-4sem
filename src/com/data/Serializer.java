package com.data;

import java.io.*;
import java.util.ArrayList;

public class Serializer {

    public void dump(ArrayList<Serializable> obj, String fileName) throws Exception {
        ObjectOutputStream out =
                new ObjectOutputStream(new FileOutputStream(fileName));
        out.writeObject(obj);
        out.flush();
        out.close();
    }

    public Serializable load(String fileName) throws Exception {
        Serializable obj;
        ObjectInputStream in =
                new ObjectInputStream(new FileInputStream(fileName));
        return (Serializable) in.readObject();
    }

}
