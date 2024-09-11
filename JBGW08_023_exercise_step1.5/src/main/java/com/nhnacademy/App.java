/*
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * + Copyright 2024. NHN Academy Corp. All rights reserved.
 * + * While every precaution has been taken in the preparation of this resource,  assumes no
 * + responsibility for errors or omissions, or for damages resulting from the use of the information
 * + contained herein
 * + No part of this resource may be reproduced, stored in a retrieval system, or transmitted, in any
 * + form or by any means, electronic, mechanical, photocopying, recording, or otherwise, without the
 * + prior written permission.
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */

//App.java
package com.nhnacademy;

import com.nhnacademy.thread.CounterHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    public static void main(String[] args) {
        // Create CounterHandler instance with countMaxSize set to 10
        CounterHandler counterHandler = new CounterHandler(10L);

        // Create a new Thread with CounterHandler as the target
        Thread thread = new Thread(counterHandler);

        // Log the state of the thread before it starts
        log.debug("Initial thread-state: {}", thread.getState());

        // Set the name of the thread
        thread.setName("my-counter");

        // Start the thread
        thread.start();

        try {
            // TODO#1 Wait for the thread to finish execution
            thread.join();
        } catch (InterruptedException e) {
            log.error("Main thread was interrupted while waiting for the worker thread to finish", e);
            Thread.currentThread().interrupt(); // Restore interrupted status
        }

        // Log after the thread has finished execution
        log.debug("Application exit!");
        log.debug("Final thread-state: {}", thread.getState());
    }
}