package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Main {
  public static void main(String[] args) {
    try {

      Registry reg = LocateRegistry.createRegistry(1099);
      Service service = new ServiceImpl();
      reg.rebind(Service.SERVICE_NAME, service);
      System.out.println("Server is running!");


    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
