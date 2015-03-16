/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lesson.simple;

/**
 *
 * @author Less
 */
public class ServiceFactory {
	public SimpleRestService getRest(){
		return new SimpleRestService();
	}
	
	public SimpleWebService getWeb(){
		return new SimpleWebService();
	}
}
