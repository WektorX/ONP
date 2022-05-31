package server;

import onp.ONP;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote {
    static final String SERVICE_NAME = "Service";
    ONP getOnp() throws RemoteException;
}