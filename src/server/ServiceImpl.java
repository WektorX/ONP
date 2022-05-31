package server;

import onp.ONP;

import java.io.Serial;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServiceImpl extends UnicastRemoteObject implements Service {

    @Serial
    private static final long serialVersionUID = 1L;

    protected ServiceImpl() throws RemoteException {
        super();
    }

    public ONP getOnp() throws RemoteException {
        return new ONP();
    }
}
