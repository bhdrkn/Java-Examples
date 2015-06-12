package com.bahadirakin;

import com.google.common.collect.Collections2;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Hello world!
 */
public class App {

    private static final Collection<User> users = new ArrayList<User>() {{
        add(new User("bhdrkn", "bhdrkn@gmail.com", "123456"));
        add(new User("bhdrkn1", "bhdrkn1@gmail.com", "123456"));
        add(new User("bhdrkn2", "bhdrkn2@gmail.com", "123456"));
        add(new User("bhdrkn3", "bhdrkn3@gmail.com", "123456"));
        add(new User("bhdrkn4", "bhdrkn4@gmail.com", "123456"));
        add(new User("bhdrkn5", "bhdrkn5@gmail.com", "123456"));
    }};

    public static void main(String[] args) {
        writeArguments(); // Writes startup arguments
        System.out.print("\n-----------------------\n");
        final Collection<User> filteredUsers = Collections2.filter(users, user -> user.getUsername().equals("bhdrkn"));
        System.out.println("Is collection null or empty?: " + (filteredUsers == null || filteredUsers.isEmpty()));
        final User bhdrkn = filteredUsers.iterator().next();
        System.out.println(bhdrkn);
        System.out.print("-----------------------\n");
    }

    public static void writeArguments() {
        final RuntimeMXBean runtimemxBean = ManagementFactory.getRuntimeMXBean();
        System.out.print("\n-----------------------\n");
        System.out.print("VM Vendor: " + runtimemxBean.getVmVendor());
        System.out.print("\nVM Name: " + runtimemxBean.getVmName());
        System.out.print("\nVM Version: " + runtimemxBean.getSpecVersion() + "(" + runtimemxBean.getVmVersion() + ")");
        System.out.print("\nVM Start time: " + new Date(runtimemxBean.getStartTime()));
        System.out.print("\nVM arguments: \n");

        final List<String> arguments = runtimemxBean.getInputArguments();
        for (String string : arguments) {
            System.out.print("\t");
            System.out.print(string);
            System.out.print("\n");
        }
        System.out.print("\nEnd Time: " + new Date());
        System.out.print("\n-----------------------\n");
    }

}
