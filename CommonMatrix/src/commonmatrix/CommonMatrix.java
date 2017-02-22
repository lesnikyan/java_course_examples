/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commonmatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @author Less
 * @param <T>
 */
public class CommonMatrix<T extends Number> {
	
	private int width = 1;
	private int height = 1;
	List<T> data = new ArrayList<T>(1);

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		//test();
		testLambda();
	}
	
	public static void p(Object x){
		System.out.println(x);
	}
	
	public static void testLambda(){
		LambdaMatrix matr = new LambdaMatrix();
		matr.test();
	}
	
	public static void test(){
		//CommonMatrix<Integer> matrix = new CommonMatrix<>(new Integer[][]{{1,2},{3,4},{5,6}});
		CommonMatrix<Integer> matrix = new CommonMatrix<>(3, 2);
		int[][] values = new int[][]{{1,2},{3,4},{5,6}};
		for(int i = 0; i < 3; ++i){
			for(int j = 0; j < 2; ++j){
				matrix.set(i, j, values[i][j]);
			}
		}
		p(matrix);
		matrix.print(matrix.plus(10.0));
	}
	
	public void iter(Function<Integer, Void> fun){
		for(int i = 0; i < 3; ++i){
			for(int j = 0; j < 2; ++j){
				fun.apply(index(i, j));
			}
		}
	}
	
	public CommonMatrix(T[][] data){
		//
	}
	
	public CommonMatrix(int x, int y){
		width = x;
		height = y;
		data = new ArrayList<T>(x * y);
		int size = x * y;
		for(int count=0; count<size; ++count){
			data.add(null);
		}
	}
	
	public void set(int x, int y, T val){
		p(String.format("%1$d, %2$d, %3$s, index = %4$d", x, y, val.toString(), index(x, y)));
		data.set(index(x, y), val);
	}
	
	public T get(int x, int y){
		return data.get(index(x, y));
	}
	
	public <K extends Number> double[][] plus(K val){
		double[][] res = new double[width][height];
		for(int i=0; i<width; ++i){
			for(int j=0;j<height;++j){
				res[i][j] = data.get(index(i,j)).doubleValue() + val.doubleValue();
			}
		}
		return res;
	}
	
	public void print(double[][] data){
		StringBuilder res = new StringBuilder();
		for(int i=0; i<width; ++i){
			for(int j=0;j<height;++j){
				res.append(data[i][j]);
				if(j < height - 1)
					res.append(" ");
			}
			if(i < width - 1){
				res.append("\n");
			}
		}
		System.out.println(res.toString());
	}
	
	private int index(int x, int y){
		return y * width + x;
	}
	
	@Override
	public String toString(){
		StringBuilder res = new StringBuilder();
		for(int i=0; i<width; ++i){
			for(int j=0;j<height;++j){
				res.append(data.get(index(i, j)));
				if(j < height - 1)
					res.append(" ");
			}
			if(i < width - 1){
				res.append("\n");
			}
		}
		return res.toString();
	}
	
}
