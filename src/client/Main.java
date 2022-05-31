package client;

import onp.ONP;
import java.rmi.Naming;
import server.Service;

public class Main {
  public static void main(String[] args) {
    try {
      Service service = (Service) Naming.lookup(Service.SERVICE_NAME);
      ONP onp = service.getOnp();
      System.out.println("Start");

      String[] data = {"(7+1)*((4-2)^2)^(7+1)*((4-2)^2)=", "(8+1)/((4-2)^2)=", "(2+3)*(2-3)+8="};
      for(String eq : data) {
        System.out.println(eq);
        String onpEq = onp.eqToOnp(eq);
        String result = onp.calculate(onpEq);
        System.out.println(onpEq + " " + result);
      }

    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
