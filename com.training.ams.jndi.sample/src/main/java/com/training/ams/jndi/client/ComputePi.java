package com.training.ams.jndi.client;

import com.training.ams.jndi.api.Compute;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.math.BigDecimal;
import java.util.Hashtable;

public class ComputePi {
    public static void main(String args[]) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {

            final Hashtable<String, Object> env = new Hashtable<>();
            env.put(Context.INITIAL_CONTEXT_FACTORY,
                    "com.sun.jndi.rmi.registry.RegistryContextFactory");
            env.put(Context.PROVIDER_URL, "rmi://" + args[0]);

            final Context registry = new InitialContext(env);

            final String name = "Compute";
            final Compute comp = (Compute) registry.lookup(name);
            final Pi task = new Pi(Integer.parseInt(args[1]));
            final BigDecimal pi = comp.executeTask(task);
            System.out.println(pi);
        } catch (Exception e) {
            System.err.println("ComputePi exception:");
            e.printStackTrace();
        }
    }
}