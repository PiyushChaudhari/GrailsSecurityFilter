package com.grails

import com.grails.Student.Languages

class StudentService {

	static transactional = false
	

	def createStudent(signUpCommand){
		def studentInstance = new Student()
		studentInstance.firstName = null
		studentInstance.lastName = null
		studentInstance.emailId = signUpCommand?.emailId
		studentInstance.password = signUpCommand?.password?.encodeAsSHA1Hex()
		studentInstance.dateOfBirth = null
		studentInstance.gender = null
		studentInstance.address = null
		studentInstance.higestQulification = null
		studentInstance.languages = null
		studentInstance.hobby = null
		studentInstance.termAndCondition = null
		studentInstance.isActive = false
		studentInstance.save(validate:false,flash:true)
		return studentInstance
	}
	
	def saveStudent(studentRegisterCommand){
		def studentInstance = Student?.get(studentRegisterCommand?.id)
		studentInstance?.properties = studentRegisterCommand?.properties
		studentInstance.isActive = true
		studentInstance.save(flash:true)
		return studentInstance
	}    
}
