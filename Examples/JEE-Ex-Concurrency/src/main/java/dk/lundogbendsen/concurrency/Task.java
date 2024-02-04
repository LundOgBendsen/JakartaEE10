/*
 * Copyright (c), Eclipse Foundation, Inc. and its licensors.
 *
 * All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v1.0, which is available at
 * https://www.eclipse.org/org/documents/edl-v10.php
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */
package dk.lundogbendsen.concurrency;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* The tasks just notify the JAX-RS web service in the EJB */
public abstract class Task implements Runnable {
    private static final String WS_URL =
            "http://localhost:8080/taskcreator/jaxrs/taskinfo";

    private final String name;
    private final String type;
    private final DateFormat dateFormat;
    private final Client client;

    public Task(String n, String t) {
        name = n;
        type = t;
        dateFormat = new SimpleDateFormat("HH:mm:ss");
        client = ClientBuilder.newClient();
    }

      protected void sendToWebService(String details) {
        String time = dateFormat.format(Calendar.getInstance().getTime());
        String msg = time + " - "  + type + " Task " + name + " " + details;
        Response resp = client.target(WS_URL)
                              .request(MediaType.TEXT_PLAIN)
                              .post(Entity.html(msg));
    }
    
    public String getName() {
        return name;
    }

}
