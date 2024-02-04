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
import java.util.logging.Logger;

/* The tasks just notify the JAX-RS web service in the EJB */
public class DelayedTask extends Task {
    public DelayedTask(String n) {
        super(n, "DELAYED");
        sendToWebService("submitted");
    }

    @Override
    public void run() {
        sendToWebService("started");
        try {
            Thread.sleep(1500);
        } catch (Exception e) {
        }
        sendToWebService("finished");
    }


}
