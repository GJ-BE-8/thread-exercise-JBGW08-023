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

//CounterHandler.java
package com.nhnacademy.thread;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// TODO#1 Runnable interface을 implements(구현)
public class CounterHandler implements Runnable {
    private final long countMaxSize;
    private long count;

    public CounterHandler(long countMaxSize) {
        // TODO#2 countMaxSize <=0 이면 IllegalArgumentException()이 발생 합니다.
        if (countMaxSize <= 0) {
            throw new IllegalArgumentException("countMaxSize must be greater than 0");
        }

        this.countMaxSize = countMaxSize;
        this.count = 0;
    }

    @Override
    public void run() {
        // TODO#3 run method를 구현 합니다.
        while (count < countMaxSize) {
            try {
                Thread.sleep(1000); // Sleep for 1 second
                count++;
                log.info("thread:{}, count:{}", Thread.currentThread().getName(), count);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted status
                log.error("Thread was interrupted", e);
                break; // Exit the loop if interrupted
            }
        }
    }
}

