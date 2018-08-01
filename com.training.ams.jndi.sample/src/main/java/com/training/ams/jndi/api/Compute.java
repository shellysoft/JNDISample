package com.training.ams.jndi.api;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Compute extends Remote {

    <T> T executeTask(Task<T> t) throws RemoteException;
}
