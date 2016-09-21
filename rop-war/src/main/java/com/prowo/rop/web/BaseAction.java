package com.prowo.rop.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction  extends ActionSupport{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Autowired


	public BaseAction() {
		//this.meta = metaService.getMeta(this.getRequest());
	}


	/**
	 * 发送Ajax请求结果json
	 *
	 * @throws ServletException
	 * @throws IOException
	 */
	public void sendAjaxResultByJson(String json) {
		this.getResponse().setContentType("application/json;charset=UTF-8");
		this.getResponse().setCharacterEncoding("UTF-8");
		try {
			PrintWriter out = this.getResponse().getWriter();
			out.write(json);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}


}
