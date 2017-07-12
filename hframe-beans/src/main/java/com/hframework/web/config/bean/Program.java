package com.hframework.web.config.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.hframework.web.config.bean.program.*;

/**
 * generated by hframework on 2016.
 */@XStreamAlias("program")
public class Program   {

	@XStreamAlias("code")
	private String code;
	@XStreamAlias("name")
	private String name;
	@XStreamAlias("description")
	private String description;
	@XStreamAlias("modules")
	private Modules modules;
	@XStreamAlias("template")
	private Template template;
	@XStreamAlias("welcome")
	private String welcome;
    @XStreamAlias("login")
    private Login login;
    @XStreamAlias("auth-instance")
    private AuthInstance authInstance;
	@XStreamAlias("superManager")
	private SuperManager superManager;
    @XStreamAlias("company")
    private String company;

    public Program() {
    }
   
 	 	 
     public String getCode(){
     	return code;
     }

     public void setCode(String code){
     	this.code = code;
     }
	 	 	 
     public String getName(){
     	return name;
     }

     public void setName(String name){
     	this.name = name;
     }
	 	 	 
     public String getDescription(){
     	return description;
     }

     public void setDescription(String description){
     	this.description = description;
     }
	 	 	 
     public Modules getModules(){
     	return modules;
     }

     public void setModules(Modules modules){
     	this.modules = modules;
     }
	 	 	 
     public Template getTemplate(){
     	return template;
     }

     public void setTemplate(Template template){
     	this.template = template;
     }
	 	 	 
     public String getWelcome(){
     	return welcome;
     }

     public void setWelcome(String welcome){
     	this.welcome = welcome;
     }
	 	 	 
     public SuperManager getSuperManager(){
     	return superManager;
     }

     public void setSuperManager(SuperManager superManager){
     	this.superManager = superManager;
     }

     public String getCompany() {
      return company;
     }

     public void setCompany(String company) {
      this.company = company;
     }

     public AuthInstance getAuthInstance() {
      if(authInstance == null) authInstance = new AuthInstance();
      return authInstance;
     }

     public void setAuthInstance(AuthInstance authInstance) {
      this.authInstance = authInstance;
     }

     public Login getLogin() {
      if(login == null) login = new Login();
      return login;
     }

     public void setLogin(Login login) {
      this.login = login;
     }
}

