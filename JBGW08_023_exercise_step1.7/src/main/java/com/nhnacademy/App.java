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

import com.nhnacademy.thread.CounterHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {
    public static void main(String[] args) {
        // counterHandlerA 객체를 생성 합니다. countMaxSize : 10
        CounterHandler counterHandlerA = new CounterHandler(10L);

        // threadA 생성시 counterHandlerA 객체를 paramter로 전달 합니다.
        Thread threadA = new Thread(counterHandlerA);

        // threadA의 name을 'my-counter-A' 로 설정 합니다.
        threadA.setName("my-counter-A");

        // Log the state of threadA before it starts
        log.debug("threadA-state:{}", threadA.getState());

        // counterHandlerB 객체를 생성 합니다. countMaxSize : 10
        CounterHandler counterHandlerB = new CounterHandler(10L);

        // threadB 생성시 counterHandlerB 객체를 paramter로 전달 합니다.
        Thread threadB = new Thread(counterHandlerB);

        // threadB의 name을 'my-counter-B' 로 설정 합니다.
        threadB.setName("my-counter-B");

        // Log the state of threadB before it starts
        log.debug("threadB-state:{}", threadB.getState());

        // Start threadA
        threadA.start();
        // Log the state of threadA after it starts
        log.debug("threadA-state:{}", threadA.getState());

        // Start threadB
        threadB.start();
        // Log the state of threadB after it starts
        log.debug("threadB-state:{}", threadB.getState());

        // TODO#1 Main Thread가 threadA, threadB가 종료될 때 까지 대기 합니다.
        try {
            threadA.join(); // Wait for threadA to finish
            threadB.join(); // Wait for threadB to finish
        } catch (InterruptedException e) {
            log.error("Main thread was interrupted while waiting for threads to finish", e);
            Thread.currentThread().interrupt(); // Restore interrupted status
        }

        // Log the final message
        log.debug("Application exit!");
    }
}
