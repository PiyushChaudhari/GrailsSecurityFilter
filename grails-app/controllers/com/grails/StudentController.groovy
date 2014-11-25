package com.grails

import java.text.SimpleDateFormat

import com.grails.validation.LoginCommand
import com.grails.validation.SignUpCommand;
import com.grails.validation.StudentRegisterCommand

class StudentController {

	def studentService
	
	def index(){
		render view:'/index'
	}
	
    def signUp() {
		SignUpCommand signUpCommand =  new SignUpCommand()
		bindData(signUpCommand,params)
		[studentInstance:signUpCommand]
	}
	
	def login(){
		LoginCommand loginCommand=new LoginCommand()
		bindData(loginCommand, params)
		if(loginCommand.validate()){
			if(Student.countByEmailIdIlikeAndPassword(loginCommand?.emailId, loginCommand?.password?.encodeAsSHA1Hex())>0){
				if(Student.countByEmailIdIlikeAndPasswordAndIsActive(loginCommand?.emailId, loginCommand?.password?.encodeAsSHA1Hex(),true as Boolean) > 0){
					session.student = Student.findByEmailIdIlikeAndPassword(loginCommand?.emailId, loginCommand?.password?.encodeAsSHA1Hex())
					chain(action: "list")
				}else{
					def studentInstance = Student.findByEmailIdIlikeAndPassword(loginCommand?.emailId, loginCommand?.password?.encodeAsSHA1Hex())
					chain(action:'register',id:studentInstance?.id)
				}
			}else{
				flash.message="Invalid E-mail address or Password."
				render view:'/index',model:[student:loginCommand]
			}
		}else{
			render view:'/index',model:[student:loginCommand]
		}
	}
	
	def register(){
		def studentInstance =Student.get(params?.id)
		[studentInstance: studentInstance,edit:(params?.edit ? params?.edit :'')]
	}
	
	def create(){
		SignUpCommand signUpCommand =  new SignUpCommand()
		bindData(signUpCommand,params)
		if(signUpCommand?.validate()){
			def studentInstance = studentService?.createStudent(signUpCommand)
			if(studentInstance){
				flash.message=" new account created successfully with ${studentInstance?.emailId}."
				chain(action: "index")
			}else{
				render(view:"signUp",model:[studentInstance:signUpCommand])
			}
		}else{
			render(view:"signUp",model:[studentInstance:signUpCommand])
		}
	}
	
	def save(){
		StudentRegisterCommand studentRegisterCommand=new StudentRegisterCommand()
		params?.dateOfBirth = params?.dateOfBirth ? new SimpleDateFormat("MM/dd/yyyy").parse(params?.dateOfBirth) : null
		bindData(studentRegisterCommand,params)
		if(studentRegisterCommand.validate()){
				def student = studentService?.saveStudent(studentRegisterCommand)
				if(session?.student){
					if(session.student?.id == student?.id)
						session.student = student
				}else{
					session.student = student
				}
			chain(action: "list")
		}else{
			render(view:"register",model:[studentInstance:studentRegisterCommand,edit:(params?.edit ? params?.edit :'')])
		}
	}
	
	def list(){
		[studentList:Student?.list()]
	}
	
	def logout(){
		if(session?.student){
			session.invalidate()
			chain(action: "index")
		}else{
			chain(action: "index")
		}
	}
	
	def delete(){
		def studentInstance = Student?.get(params?.id)
		studentInstance.delete(flash:true)
		chain(action: "list")
	}
}
