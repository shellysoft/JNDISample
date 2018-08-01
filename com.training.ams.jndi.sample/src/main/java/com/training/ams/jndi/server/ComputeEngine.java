package com.training.ams.jndi.server;


import com.training.ams.jndi.api.Compute;
import com.training.ams.jndi.api.Task;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ComputeEngine implements Compute {

    public ComputeEngine() {
        super();
    }

    public <T> T executeTask(Task<T> t) {
        return t.execute();
    }

    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            final Registry registry = LocateRegistry.createRegistry(1091);
            final String name = "Compute";
            final Compute engine = new ComputeEngine();
            final Compute stub =
                    (Compute) UnicastRemoteObject.exportObject(engine, 0);
            //Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
            System.out.println("ComputeEngine bound");
        } catch (Exception e) {
            System.err.println("ComputeEngine exception:");
            e.printStackTrace();
        }
    }
}