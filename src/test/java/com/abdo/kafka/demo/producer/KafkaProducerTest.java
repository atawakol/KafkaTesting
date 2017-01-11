/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abdo.kafka.demo.producer;

import org.junit.Test;

/**
 *
 * https://dzone.com/articles/7-popular-unit-test-naming.
 * 
 * First
 * Given_Preconditions_When_StateUnderTest_Then_ExpectedBehavior
 *  example --->  Given_UserIsAuthenticated_When_InvalidAccountNumberIsUsedToWithdrawMoney_Then_TransactionsWillFail
 * 
 * Or may be we remove the Given part
 * 
 * 
 * 
 * Second
 * 
 * When_StateUnderTest_Expect_ExpectedBehavior
 *    example --->  When_AgeLessThan18_Expect_isAdultAsFalse
 * 
 * @author atawakol
 */
public class KafkaProducerTest {
    
    
    @Test
    public final void testMessageSendWithKeyValyePair(){
        
        for (int i = 1 ; i <= 150; i++) {
             MyKafkaProducer.sendMessage(Integer.toString(i) , "Producer Message " + i);
        }
        
       
    }
    
    
    @Test
    public final void testMessageSendWithValueOnly(){
        
        for (int i = 0 ; i < 150; i++){
          MyKafkaProducer.sendMessage("Producer Message " + i);  
        }
        
        
    }
}
