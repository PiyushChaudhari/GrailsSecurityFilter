package com.grails



import grails.test.mixin.*

import java.text.SimpleDateFormat

import org.junit.*

import com.grails.validation.LoginCommand
import com.grails.validation.SignUpCommand
import com.grails.validation.StudentRegisterCommand

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(StudentController)
@Mock(Student)
class StudentControllerTests {

	
	@Before
	void setUp() {
		controller.studentService = new StudentService()
		mockCommandObject(LoginCommand)
		mockCommandObject(SignUpCommand)
		mockCommandObject(StudentRegisterCommand)
	}
	
	def studentCreateValidParams(params) {
		// TODO: Populate valid properties like...
		params["id"] = '1'
		params["firstName"] = 'Piyush'
		params["lastName"] = 'Chaudhari'
		params["emailId"] = 'piyu181203@gmail.com'
		params["password"] = '123'.encodeAsSHA1Hex()
		params["dateOfBirth"] = null
		params["gender"] = 'Male'
		params["address"] = null
		params["higestQulification"] = 'MCA'//com.grails.Student.HigestQulification?.MCA?.toString()
		params["languages"] = 'Gujarati,Tamil'
		params["hobby"] = null
		params["termAndCondition"] = null
		params["isActive"] = true
	}
	
	void testIndex() {
		controller.index()
		assert view == "/index"
	}
	
	void testSignUp(){
		SignUpCommand signUpCommand =  new SignUpCommand()
		def model = controller.signUp()
		assert model.studentInstance instanceof SignUpCommand
	}
	
	void testCreate(){
		params["emailId"] = null
		params["password"] = null
		params["confirmPassword"] = null
		controller.create()
		assert view == "/student/signUp"
		assert controller?.modelAndView?.model?.studentInstance instanceof SignUpCommand
		
		controller.response.reset()
		
		params["emailId"] = 'piyu1812'
		params["password"] = '123'
		params["confirmPassword"] = '123'
		controller.create()
		assert view == "/student/signUp"
		assert controller?.modelAndView?.model?.studentInstance instanceof SignUpCommand
		
		controller.response.reset()
		
		params["emailId"] = 'piyu1812@gmail.com'
		params["password"] = '123'
		params["confirmPassword"] = '1234'
		controller.create()
		assert view == "/student/signUp"
		assert controller?.modelAndView?.model?.studentInstance instanceof SignUpCommand
		
		controller.response.reset()
		
		String.metaClass.'static'.encodeAsSHA1Hex = { }
		studentCreateValidParams(params)
		def newStudent = new Student(params)
		newStudent.save(validate:false,flash:true)
		assert newStudent == Student?.get(1)
		
		params["emailId"] = 'piyu181203@gmail.com'
		params["password"] = '123'
		params["confirmPassword"] = '123'
		controller.create()
		assert view == "/student/signUp"
		assert controller?.modelAndView?.model?.studentInstance instanceof SignUpCommand
		
		params["emailId"] = 'piyu1812@yahoo.co.in'
		params["password"] = '123'
		params["confirmPassword"] = '123'
		controller.create()
		assert controller.flash.message == " new account created successfully with piyu1812@yahoo.co.in."
		assert response.redirectedUrl == '/student/index'
	}
	
	void testLogin(){
		
		String.metaClass.'static'.encodeAsSHA1Hex = { }
		studentCreateValidParams(params)
		def newStudent = new Student(params)
		newStudent.save(validate:false,flash:true)
		assert newStudent == Student?.get(1)
		
		params["emailId"] = null
		params["password"] = null
		controller.login()
		assert view == "/index"
		assert controller?.modelAndView?.model?.student instanceof LoginCommand
		
		controller.response.reset()
		
		String.metaClass.'static'.encodeAsSHA1Hex = { }
		params["emailId"] = 'piyu'
		params["password"] = '123'
		controller.login()
		assert view == "/index"
		assert controller?.modelAndView?.model?.student instanceof LoginCommand
		
		controller.response.reset()
		
		String.metaClass.'static'.encodeAsSHA1Hex = { }
		params["emailId"] = 'piyu1812@gmail.com'
		params["password"] = '1234'
		controller.login()
		assert view == "/index"
		assert controller.flash.message == "Invalid E-mail address or Password."
		assert controller?.modelAndView?.model?.student instanceof LoginCommand
		
		controller.response.reset()
		
		studentCreateValidParams(params)
		params["emailId"] = 'piyu1812@yahoo.co.in'
		params["isActive"] = false
		newStudent = new Student(params)
		newStudent.save(validate:false,flash:true)
		
		controller.response.reset()
		
		params["emailId"] = 'piyu1812@yahoo.co.in'
		params["password"] = '123'
		controller.login()
		assert response.redirectedUrl == '/student/register/'+Student?.findByEmailIdAndPassword('piyu1812@yahoo.co.in','123'?.encodeAsSHA1Hex())?.id
		
		controller.response.reset()
		
		params["emailId"] = 'piyu181203@gmail.com'
		params["password"] = '123'
		controller.login()
		assert session?.student == Student?.findByEmailIdAndPassword('piyu181203@gmail.com','123'?.encodeAsSHA1Hex())
		assert response.redirectedUrl == '/student/list'
	}
	
	void testList(){
		testLogin()
		def model  = controller.list()
		assert model?.studentList == Student?.list()
		assert response.redirectedUrl == '/student/list'
	}
	void testLogout(){
		
		controller.logout()
		assert response.redirectedUrl == '/student/index'
		
		controller.response.reset()
		
		String.metaClass.'static'.encodeAsSHA1Hex = { }
		studentCreateValidParams(params)
		def newStudent = new Student(params)
		newStudent.save(validate:false,flash:true)
		
		assert newStudent == Student?.get(1)

		params["emailId"] = 'piyu181203@gmail.com'
		params["password"] = '123'
		controller.login()
		assert session?.student == Student?.findByEmailIdAndPassword('piyu181203@gmail.com','123'?.encodeAsSHA1Hex())
		assert response.redirectedUrl == '/student/list'
		
		controller.response.reset()

		controller.logout()
		assert response.redirectedUrl == '/student/index'
		assert session?.student == null
	}
	
	void testDelete(){
		testLogin()
		controller.response.reset()
		
		params["emailId"] = 'piyu181203@gmail.com'
		params["password"] = '123'
		controller.login()
		assert session?.student == Student?.findByEmailIdAndPassword('piyu181203@gmail.com','123'?.encodeAsSHA1Hex())
		assert response.redirectedUrl == '/student/list'
		
		controller.response.reset()
		
		params["id"] = '1'
		controller.delete()
		assert response.redirectedUrl == '/student/list'
		assert Student?.list()?.size() == 1
	}
	
	void testRegister(){
		String.metaClass.'static'.encodeAsSHA1Hex = { }
		studentCreateValidParams(params)
		def newStudent = new Student(params)
		newStudent.save(validate:false,flash:true)
		
		assert newStudent == Student?.get(1)
		params["id"] = '1'
		def model = controller.register()
		assert model.studentInstance instanceof Student
		
		controller.response.reset()
		params["id"] = '1'
		params["edit"] = 'edit'
		model = controller.register()
		assert model.studentInstance instanceof Student
		assert model.edit == 'edit'
	}	
	
	void testSave(){
		String.metaClass.'static'.encodeAsSHA1Hex = { }
		studentCreateValidParams(params)
		def newStudent = new Student(params)
		newStudent.save(validate:false,flash:true)
		
		assert newStudent == Student?.get(1)
		controller.response.reset()
		
		params["id"] = '1'
		params["edit"] = ''
		params["firstName"] = null
		params["lastName"] = null
		params["dateOfBirth"] = null
		params["gender"] = null
		params["address"] = null
		params["higestQulification"] = null//com.grails.Student.HigestQulification?.MCA?.toString()
		params["languages"] = null
		params["hobby"] = null
		params["termAndCondition"] = null
		controller.save()
		assert view == "/student/register"
		assert controller?.modelAndView?.model?.studentInstance instanceof StudentRegisterCommand
		assert controller?.modelAndView?.model?.edit == ''
		
		controller.response.reset()
		
		params["id"] = '1'
		params["edit"] = 'edit'
		params["firstName"] = 'Alpan'
		params["lastName"] = 'Patel'
		params["dateOfBirth"] = '03/27/2014'
		params["gender"] = 'Male'
		params["address"] = 'Valsad, Surat'
		params["higestQulification"] = 'MTech'
		params["languages"] = 'Gujarati,Hindi'
		params["hobby"] = 'Reading,Surffing'
		params["termAndCondition"] = false
		controller.save()
		assert view == "/student/register"
		assert controller?.modelAndView?.model?.studentInstance instanceof StudentRegisterCommand
		assert controller?.modelAndView?.model?.edit == 'edit'
		
		controller.response.reset()
		
		params["id"] = '1'
		params["edit"] = ''
		params["firstName"] = 'Alpan'
		params["lastName"] = 'Patel'
		params["dateOfBirth"] = '03/27/2014'
		params["gender"] = 'Male'
		params["address"] = 'Valsad, Surat'
		params["higestQulification"] = 'MTech'//com.grails.Student.HigestQulification?.MCA?.toString()
		params["languages"] = 'Gujarati,Hindi'
		params["hobby"] = 'Reading,Surffing'
		params["termAndCondition"] = false
		params.remove('emailId')
		params.remove('password')
		controller.save()
		assert view == "/student/register"
		assert controller?.modelAndView?.model?.studentInstance instanceof StudentRegisterCommand
		assert controller?.modelAndView?.model?.edit == ''
		
		controller.response.reset()
		
		params["id"] = '1'
		params["edit"] = ''
		params["firstName"] = 'Alpan'
		params["lastName"] = 'Patel'
		params["dateOfBirth"] = '03/27/2014'
		params["gender"] = 'Male'
		params["address"] = 'Valsad, Surat'
		params["higestQulification"] = 'MTech'
		params["languages"] = 'Gujarati,Hindi'
		params["hobby"] = 'Reading,Surffing'
		params["termAndCondition"] = true
		params.remove('emailId')
		params.remove('password')
		params.remove('isActive')
		controller.save()
		assert view == "/student/register"
		assert controller?.modelAndView?.model?.studentInstance instanceof StudentRegisterCommand
		assert controller?.modelAndView?.model?.edit == ''
	}
}
