/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package generics;

import java.util.ArrayList;

/**
 *
 * @author Less
 */
public class SortedList<T> {
	private ArrayList<T> _list;
	private int _count = 10;
	
	SortedList(){
		_list = new ArrayList<T>();
	}
}
