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

//CounterThread.java
package com.nhnacademy.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CounterThread extends Thread {
    private final long countMaxSize;
    private long count;

    public CounterThread(String name, long countMaxSize) {
        super(name); // Call the Thread constructor to set the thread name

        // TODO#2 name <-- null 이거나 공백 문자열이면 IllegalArgumentException이 발생 합니다.
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Thread name cannot be null or empty");
        }

        // TODO#3 countMaxSize <=0 이면 IllegalArgumentException이 발생 합니다.
        if (countMaxSize <= 0) {
            throw new IllegalArgumentException("countMaxSize must be greater than 0");
        }

        this.countMaxSize = countMaxSize;
        this.count = 0;
    }

    @Override
    public void run() {
        // TODO#4 run method를 구현 합니다.
        while (count < countMaxSize) {
            try {
                Thread.sleep(1000); // Sleep for 1 second
                count++;
                log.info("thread:{}, count:{}", this.getName(), count);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted status
                log.error("Thread was interrupted", e);
                break; // Exit the loop if interrupted
            }
        }
    }
}
