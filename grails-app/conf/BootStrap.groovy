import com.grails.Student
import com.grails.Student.HigestQulification;

class BootStrap {

    def init = { servletContext ->
		def student = new Student()
		student.firstName = 'Piyush'
		student.lastName = 'Chaudhari'
		student.emailId = 'piyu181203@gmail.com'
		student.password = '123'?.encodeAsSHA1Hex()
		student.dateOfBirth = null
		student.gender = null
		student.address = null
		student.higestQulification = HigestQulification?.MTech
		student.languages = 'Gujarati,Tamil'
		student.hobby = null
		student.termAndCondition = null
		student.isActive = true
		
		student.save(validate:false,failOnError :true)
		
		student = new Student()
		student.firstName = 'Piyush'
		student.lastName = 'Chaudhari'
		student.emailId = 'piyu1812@yahoo.co.in'
		student.password = '123'?.encodeAsSHA1Hex()
		student.dateOfBirth = null
		student.gender = null
		student.address = null
		student.higestQulification = HigestQulification?.MTech
		student.languages = 'Gujarati,Hindi'
		student.hobby = null
		student.termAndCondition = null
		student.isActive = false
		
		student.save(validate:false,failOnError :true)
		
		student = new Student()
		student.firstName = 'Viki'
		student.lastName = 'Vayade'
		student.emailId = 'vikiv@gmail.com'
		student.password = '123'?.encodeAsSHA1Hex()
		student.dateOfBirth = null
		student.gender = null
		student.address = null
		student.higestQulification = HigestQulification?.MTech
		student.languages = 'Gujarati,Hindi'
		student.hobby = null
		student.termAndCondition = null
		student.isActive = false
		
		student.save(validate:false,failOnError :true)
		
		student = new Student()
		student.firstName = 'Alpan'
		student.lastName = 'patel'
		student.emailId = 'alpanp@gmail.com'
		student.password = '123'?.encodeAsSHA1Hex()
		student.dateOfBirth = null
		student.gender = null
		student.address = null
		student.higestQulification = HigestQulification?.MTech
		student.languages = 'Gujarati,Hindi'
		student.hobby = null
		student.termAndCondition = null
		student.isActive = false
		
		student.save(validate:false,failOnError :true)
    }
    def destroy = {
    }
}
