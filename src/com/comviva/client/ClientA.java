package com.comviva.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.comviva.reconciliation.service.*;
import com.comviva.reconciliation.entity.Transaction;

public class ClientA {

	private static ApplicationContext ctx;
	
	static{
		ctx = new ClassPathXmlApplicationContext("beans.xml");
	}
	
	public static void main(String[] args) {
		try{System.out.println("main start");
		ITransactionService ser = ctx.getBean("trasactionService",ITransactionService.class);
        System.out.println(ser.display());
        List<Transaction> list = ser.getFailedTransactionsFromDB();
        for(Transaction transaction:list) {
        	System.out.print("inside For loop");
        	System.out.println(transaction);
        }}
		catch (Exception e) {
			e.printStackTrace();
		}
        
       /* System.out.println("demand for second time");
        ser = ctx.getBean("myservice", ISer.class);
        System.out.println(ser.getService());*/
	}

}







