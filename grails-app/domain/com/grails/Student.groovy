package com.grails

class Student {

	def studentService
	
	String firstName
	String lastName
	String emailId
	String password
	Date dateOfBirth
	Gender gender
	enum Gender{
		Male,
		Female
	}
	
	String address
	
	HigestQulification higestQulification
	enum HigestQulification{
		MCA("M.C.A"),
		BCA("B.C.A"),
		MTech("M.Tech"),
		BTech("B.Tech"),
		MSc("M.Sc"),
		BSc("B.Sc")
		
		private final String value
		HigestQulification(String value) {
			 this.value = value
		}
		public String getQulification() {
			 return value
		}
	}
	
	
	String languages
	
	enum Languages{
		English,
		Hindi,
		Gujarati,
		Marathi,
		Tamil,
		Urdu
	}
	
	String hobby
	
	enum Hobbies{
		Reading,
		Swimming,
		Surffing
	}
 	
	Boolean termAndCondition
	Boolean isActive
	
    static constraints = {
		firstName blank:false,nullable:true
		lastName blank:false,nullable:true
		emailId blank:false,nullable:false,email: true,unique: true
		password blank:false,nullable:false
		dateOfBirth blank:false,nullable:true
		gender blank:false,nullable:true,validator:{value,obj ->
			if(value){
				if(!value in Gender?.values()){
					return['not.inList']
				}else{
					return true
				}
			}else{
				return['blank']
			}
		}
		address blank:false,nullable:true
		higestQulification blank:false,nullable:true,validator:{value,obj ->
			if(value){
				if(!value in HigestQulification?.values()){
					return['not.inList']
				}else{
					return true
				}
			}else{
				return['blank']
			}
		}
		languages blank:false,nullable:true
		hobby blank:false,nullable:true
		termAndCondition blank:false,nullable:true,validator:{value,obj ->
			if(value){
				if(value?.equals(Boolean.TRUE)){
					return true
				}else{
					return['blank']
				}
			}else{
				return['blank']
			}
		}
		isActive blank:false,nullable:true
    }
	
	static mapping = {
		firstName(type:"text")
		lastName(type:"text")
		emailId(type:"text")
		password(type:"text")
		address(type:"text")
		languages(type:"text")
		hobby(type:"text")
	}
}
