package com.grails.validation

@grails.validation.Validateable
class LoginCommand {

	String emailId
	String password
	
	static constraints = {
		emailId blank:false,nullable:false,email:true
		password blank:false,nullable:false
	}

}
