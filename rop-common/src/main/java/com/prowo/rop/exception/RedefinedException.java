package com.prowo.rop.exception;

/**
 * 重复定义的method 和 version
 * @author dengcheng
 *
 */
public class RedefinedException extends RopException{
	/**
	 *
	 */
	private static final long serialVersionUID = 8768155790007183873L;

	public RedefinedException(){
		super("重复定义 method 以及版本");
	}
}
