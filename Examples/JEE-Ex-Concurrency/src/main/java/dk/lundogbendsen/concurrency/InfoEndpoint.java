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

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.event.Observes;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

/* Notify the clients so that they can refresh the log textarea */
@Dependent
@ServerEndpoint("/wsinfo")
public class InfoEndpoint {

    private static final Logger log = Logger.getLogger("InfoEndpoint");
    /* Keep a list of clients */
    private static final Queue<Session> sessions =
            new ConcurrentLinkedQueue<>();
    
    @OnOpen
    public void onOpen(Session session) {
        log.info("[InfoEndpoint] Connection opened");
        sessions.add(session);
    }
    
    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }
    
    @OnError
    public void onError(Session session, Throwable t) { }
    
    @OnMessage
    public void onMessage(String msg) { }
    
    /* Observe the event fired from the EJB and notify clients.
     * The clients use JavaScript to make a JSF AJAX request to refresh
     * the log textarea. */
    public static void pushAlert(@Observes String event) {
        for (Session s : sessions) {
            if (s.isOpen())
                try {
                    s.getBasicRemote().sendText(event);
                    log.info("[InfoEndpoint] Event sent");
                } catch (IOException ex) { }
        }
    }
}
