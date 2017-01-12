/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abdo.kafka.demo.consumer;

import org.junit.Test;

/**
 *
 * https://dzone.com/articles/7-popular-unit-test-naming.
 *
 * First Given_Preconditions_When_StateUnderTest_Then_ExpectedBehavior example
 * --->
 * Given_UserIsAuthenticated_When_InvalidAccountNumberIsUsedToWithdrawMoney_Then_TransactionsWillFail
 *
 * Or may be we remove the Given part
 *
 *
 *
 * Second
 *
 * When_StateUnderTest_Expect_ExpectedBehavior example --->
 * When_AgeLessThan18_Expect_isAdultAsFalse
 *
 * @author atawakol
 */
public class MyKafkaConsumerTest {

    @Test
    public final void testMessageRecieveByAssign() {
        MyKafkaConsumer.recieveMesssageByAssign();

    }

    @Test
    public final void testMessageRecieveBySubscribe() {
        //MyKafkaConsumer.recieveMesssageBySubscribe();

    }
}
