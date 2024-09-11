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

package com.nhnacademy.livelock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Counter {
    private int count = 0;
    private final Lock lock = new ReentrantLock();

    public void increment() {
        while (true) {
            if (lock.tryLock()) {
                try {
                    count++;
                    log.debug("{} count++ : {}", Thread.currentThread().getName(), count );
                    break;
                } finally {
                    lock.unlock();
                }
            } else {
                log.debug("{} lock 획득 시도...", Thread.currentThread().getName());
            }
        }
    }

    public int getCount() {
        return count;
    }
}