package com.grails.validation

import com.grails.Student
import com.grails.Student.Gender
import com.grails.Student.HigestQulification
import com.grails.Student.Languages
import com.grails.StudentService;

@grails.validation.Validateable
class StudentRegisterCommand {

	Long id
	String firstName
	String lastName
	Date dateOfBirth
	Gender gender
	
	String address
	
	HigestQulification higestQulification
	String languages
	String hobby
	Boolean termAndCondition
	
    static constraints = {
		importFrom Student, exclude: ["emailId","password","isActive"] 
		firstName nullable:false
		lastName nullable:false
		dateOfBirth blank:false,nullable:false
		gender blank:false,nullable:false
		address blank:false,nullable:false
		higestQulification blank:false,nullable:false
		languages nullable:false
		hobby nullable:false
		termAndCondition nullable:false
    }
	
	static mapping = {
		firstName(type:"text")
		lastName(type:"text")
		address(type:"text")
		languages(type:"text")
		hobby(type:"text")
	}
}
