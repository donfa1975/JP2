/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;
import javax.validation.ConstraintViolation;

/**
 *
 * @author fabricio.diaz
 */
public  class clsTran {
	
	private clsTran()
	{
		
	}
	/*public static UserTransaction getTransaccion()
	{
			try
		{
			
			UserTransaction t = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			
			return t;
			
		}
		catch(javax.validation.ConstraintViolationException  e)
			{
				//e.printStackTrace();
				for(ConstraintViolation cv:e.getConstraintViolations())
				{
					cv.getMessage();
				}
				return null;
				
			}
		catch(Exception e1)
		{
			return null;
		}
	}*/
}
