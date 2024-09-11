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

// App.java
package com.nhnacademy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

    private static final Object resource1 = new Object();
    private static final Object resource2 = new Object();

    public static void main(String[] args) {

        //TODO#1 Thread-1 tries to acquire resource1 and then resource2.
        Thread thread1 = new Thread(() -> {
            synchronized (resource1) {
                log.debug("{}: locked resource 1", Thread.currentThread().getName());

                try {
                    Thread.sleep(1000); // Simulate work
                } catch (InterruptedException e) {
                    log.debug(e.getMessage());
                }

                synchronized (resource2) {
                    log.debug("{}: locked resource 2", Thread.currentThread().getName());
                }
            }
        });
        thread1.setName("Thread-1");

        //TODO#2 Thread-2 tries to acquire resource2 and then resource1.
        Thread thread2 = new Thread(() -> {
            synchronized (resource2) { // Note: resource2 is acquired before resource1
                log.debug("{}: locked resource 2", Thread.currentThread().getName());

                try {
                    Thread.sleep(1000); // Simulate work
                } catch (InterruptedException e) {
                    log.debug(e.getMessage());
                }

                synchronized (resource1) {
                    log.debug("{}: locked resource 1", Thread.currentThread().getName());
                }
            }
        });
        thread2.setName("Thread-2");

        thread1.start();
        thread2.start();
    }
}
