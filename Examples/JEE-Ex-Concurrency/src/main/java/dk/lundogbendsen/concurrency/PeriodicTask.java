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

public class PeriodicTask extends Task {
    private int counter;

    public PeriodicTask(String n) {
        super(n, "PERIODIC");
    }

    @Override
    public void run() {
        sendToWebService("started run #" + counter);
        try {
            Thread.sleep(1500);
        } catch (Exception e) {
        }
        sendToWebService("finished run #" + (counter++));
    }

}
