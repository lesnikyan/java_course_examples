/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lesson.simple;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Less
 */
public class ObjWithList {
	private List<String> units;
	private Map<String, Object> options;

	/**
	 * @param units the units to set
	 */
	public void setUnits(List<String> units) {
		this.units = units;
	}

	/**
	 * @param options the options to set
	 */
	public void setOptions(Map<String, Object> options) {
		this.options = options;
	}

	/**
	 * @return the units
	 */
	public List<String> getUnits() {
		return units;
	}

	/**
	 * @return the options
	 */
	public Map<String, Object> getOptions() {
		return options;
	}

}
