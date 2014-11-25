package com.grails.validation

import com.grails.Student

@grails.validation.Validateable
class SignUpCommand {

	String emailId
	String password
	String confirmPassword

	static constraints = {
		importFrom Student, include: ["emailId","password","confirmPassword"]
		emailId validator:{value,obj ->
			if(Student?.findByEmailIdIlike(value)){
					return['email.exists']
			}
		}
		password nullable:false
		confirmPassword blank:false,nullable:false,validator:{value,obj ->
			if(value != obj?.password){
					return['NotMatch']
			}
		}
	}
}
