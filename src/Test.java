import com.data.ServiceInfo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        //some serilization
        String fileName = "test";
        ServiceInfo service1 = new ServiceInfo("name1", "type1", 19.99);
        ArrayList<ServiceInfo> services = new ArrayList<>();
        services.add(service1);
        services.add(service1);
        System.out.print(services);
        System.out.println();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(services);
            out.flush();
            out.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            ArrayList<ServiceInfo> loaded = (ArrayList<ServiceInfo>) in.readObject();
            System.out.println(loaded);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
