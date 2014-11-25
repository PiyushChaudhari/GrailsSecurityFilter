package com.grails

class SecurityFilters {
	def grailsApplication
	def filters = {
		all(controller:'*', action:'*') {
			
			before = {
				if(session?.student){
					if(controllerName.equals('student') && actionName?.equals('login')){
						chain(controller:'student',action:'list')
						return false
					}else if(controllerName.equals('student') && actionName?.equals('signUp')){
						chain(controller:'student',action:'list')
						return false
					}else if(controllerName.equals('student') && actionName?.equals('create')){
						chain(controller:'student',action:'list')
						return false
					}else if(controllerName.equals('student') && actionName?.equals('save')){
						if(!params?.id){
							chain(controller:'student',action:'list')
							return false
						}						
					}else if(controllerName.equals('student') && actionName?.equals('register')){
						if(Student?.countById(params?.id) == 0){
							chain(controller:'student',action:'list')
							return false
						}						
					}
				}else{
					if(controllerName.equals('student') && actionName?.toString() in ['list','delete']){
						chain(controller:'student',action:'index')
						return false
					}else if(controllerName.equals('student') && actionName?.equals('register')){
						if(Student?.countById(params?.id) == 0){
							chain(controller:'student',action:'index')
							return false
						}else if(Student?.countByIdAndIsActive(params?.id,true as Boolean) > 0){
							chain(controller:'student',action:'index')
							return false
						}else if(params?.id && params?.edit){
							chain(controller:'student',action:'index')
							return false
						}
					}else if(controllerName.equals('student') && actionName?.equals('save')){
						if(!request?.forwardURI?.toString()?.equals('/'+grailsApplication?.metadata['app.name']+'/'+controllerName?.toString()+'/'+actionName?.toString())){
							chain(controller:'student',action:'index')
							return false
						}else if(!params?.id){
							chain(controller:'student',action:'index')
							return false
						}else if(Student?.countByIdAndIsActiveInList(params?.id,[true as Boolean,false as Boolean])==0){
							chain(controller:'student',action:'index')
							return false
						}else if(Student?.countByIdAndIsActive(params?.id,true as Boolean) > 0){
							chain(controller:'student',action:'index')
							return false
						}
					}
				}
			}
			after = { Map model ->
			}
			afterView = { Exception e ->
			}
		}
	}
}
