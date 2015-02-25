/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commonmatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Less
 */
public class LambdaMatrix {
	public void test(){
		int[][] initData = new int[][]{{1,2,3},{4,5,6}};
		MatrixImplementation<Integer> matrix = new MatrixImplementation<>(3, 2);
		for(int y = 0; y < 2; ++y){
			for(int x = 0; x < 3; ++x){
				matrix.set(x, y, initData[y][x]);
			}
		}
		p(matrix);
		p(matrix.turn());
	}
	
	public void p(Object x){
		System.out.println(x);
	}
}

class MatrixImplementation<T extends Number> {
	private int w = 1;
	private int h = 1;
	private List<T> data;
	
	public MatrixImplementation(int w, int h){
		this.w = w;
		this.h = h;
		int size = w * h;
		data = new ArrayList<T>(Collections.nCopies(w * h, null));
	}

	public MatrixImplementation(T[][] values){
		this(values.length, values[0].length);
		for(int y = 0; y < 2; ++y){
			for(int x = 0; x < 3; ++x){
				set(x, y, values[y][x]);
			}
		}
	}
	
	public final void set(int x, int y, T val){
		data.set(index(x, y), val);
	}
	
	public MatrixImplementation<T> turn(){
		MatrixImplementation<T>  newData = new MatrixImplementation<>(h, w);
		newData.forEach((val, x, y) -> {newData.set(x, y, data.get(index(y, x))); return null;});
		return newData;
	}
	
	public void forEach(Function3Arg<T, Integer, Integer, Object> fun){
		for(int y=0; y < h; ++y){
			for(int x = 0; x < w; ++x){
				fun.apply(data.get(index(x, y)), x, y);
			}
		}
	}
	
	@Override
	public String toString(){
		StringBuilder res = new StringBuilder();
		forEach((T val, Integer x, Integer y) -> res.append(val).append((x+1)%w == 0 ? "\n" : " ")); //  
		return res.toString();
	}
	
	public final int index(int x, int y){
		return y * w + x;
	}
}

interface Function3Arg<A, B, C, R> {
	public R apply(A a, B b, C c);
}
