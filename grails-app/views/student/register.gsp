<!DOCTYPE html>
<html>
  <head>
    <title>Register</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- jQuery UI -->

    <!-- Bootstrap -->
    <link rel="icon" href="${resource(dir: '/images', file: 'security_filter.png')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'bootstrap/css', file: 'bootstrap.min.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'styles.css')}" type="text/css">
    <!-- styles -->
    <g:javascript library="jquery" />
	<r:require modules="jquery-ui"/>
	
	<g:javascript>
		$(document).ready(function() {
			$( "#dateOfBirth" ).datepicker({
				dateFormat: "mm/dd/yy",
				changeMonth: true,
		     	changeYear: true,
			})
			
			
			$('#dateOfBirthIcon').click(function() {
			      $('#dateOfBirth').datepicker('show');
			});
	});
	</g:javascript>
    <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
    <link href="vendors/form-helpers/css/bootstrap-formhelpers.min.css" rel="stylesheet">
    <link href="vendors/select/bootstrap-select.min.css" rel="stylesheet">
    <link href="vendors/tags/css/bootstrap-tags.css" rel="stylesheet">

    
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'forms.css')}" type="text/css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    <r:layoutResources/>
  </head>
  <body>
  <r:layoutResources/>
  	<div class="header">
	     <div class="container">
	        <div class="row">
	           <div class="col-md-5">
	              <!-- Logo -->
	              <div class="logo">
	                 <h1><a href="http://grails.org/doc/2.2.1/ref/Plug-ins/filters.html">Grails Security Filter</a></h1>
	              </div>
	           </div>
	           <g:if test="${session?.student}">
		           <div class="col-md-7">
		              <div class="navbar navbar-inverse" role="banner">
		                  <nav class="collapse navbar-collapse bs-navbar-collapse navbar-right" role="navigation">
		                    <ul class="nav navbar-nav">
		                      <li class="dropdown">
		                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Welcome, ${((session?.student?.firstName ? session?.student?.firstName :'')+' '+(session?.student?.lastName ? session?.student?.lastName : ''))?.trim()}<b class="caret"></b></a>
		                        <ul class="dropdown-menu animated fadeInUp">
		                          <li><g:link controller="student" action="register" id="${session?.student?.id}" params="['edit':'edit']" class="glyphicon  glyphicon-user"> Profile</g:link></li>
		                          <li><g:link controller="student" action="logout" class="glyphicon glyphicon-log-out"> Logout</g:link> </li>
		                        </ul>
		                      </li>
		                    </ul>
		                  </nav>
		              </div>
		           </div>
	           </g:if>
	        </div>
	     </div>
	</div>

    <div class="page-content">
    	<div class="row">
		  <div class="col-md-10">
	  			<div class="row">
	  				<div class="col-md-6 col-md-offset-4">
	  					<div class="content-box-large">
			  				<div class="panel-heading">
					            <h4>${edit ? 'Update Student':'Registration'} Detail</h4>
					        </div>
			  				<div class="panel-body">
			  					<g:form controller="student" action="save" method="post">
			  						<g:hiddenField name="id" value="${studentInstance?.id}" />
			  						<g:hiddenField name="edit" value="${edit}" />
				  					<div class="row">
				  						<div class="form-group col-sm-5 ${hasErrors(bean:studentInstance,field:'firstName', 'has-error')}">
				  							<label>First Name</label>
											<g:textField id="firstName" name="firstName" value="${studentInstance?.firstName}" class="form-control" placeholder="First Name"/>
											<span class="control-label"> <g:fieldError bean="${studentInstance}" field="firstName"></g:fieldError> </span>										
										</div>
										<div class="form-group col-sm-5 ${hasErrors(bean:studentInstance,field:'lastName', 'has-error')}">
				  							<label>Last Name</label>
											<g:textField id="lastName" name="lastName" value="${studentInstance?.lastName}" class="form-control" placeholder="Last Name"/>
											<span class="control-label"> <g:fieldError bean="${studentInstance}" field="lastName"></g:fieldError> </span>										
										</div>
				  					</div>
				  					<div class="row">
				  						<div class="form-group col-sm-5 ${hasErrors(bean:studentInstance,field:'dateOfBirth', 'has-error')}">
				  							<label>Date of Birth</label>
				  							<div class="input-group" >
					  							<g:textField id="dateOfBirth" name="dateOfBirth" class="form-control" value="${studentInstance?.dateOfBirth ? formatDate(format:"MM/dd/yyyy",date:studentInstance?.dateOfBirth) : ''}" placeholder="Date Of Birth" />
					  							<span class="input-group-addon">
					  								<i id="dateOfBirthIcon" class="glyphicon glyphicon-calendar btn-link" title="Select Date" ></i>
					  							</span>
				  							</div>
				  							<span class="control-label"> <g:fieldError bean="${studentInstance}" field="dateOfBirth"></g:fieldError> </span>
				  						</div>
				  					</div>
				  					<div class="row">
				  						<div class="form-group col-sm-5 has-error">
				  							<label>Gender</label><br>
				  							<g:radioGroup name="gender" labels="${com.grails.Student.Gender?.values()}" values="${com.grails.Student?.Gender}" value="${studentInstance?.gender}">
												 ${it.radio}${it.label}
											</g:radioGroup>
											<br>
											<span class="control-label"> <g:fieldError bean="${studentInstance}" field="gender"></g:fieldError> </span>
				  						</div>
				  					</div>
				  					<div class="row">
				  						<div class="form-group col-sm-8 ${hasErrors(bean:studentInstance,field:'address', 'has-error')}">
				  							<label>Address</label>
				  							<g:textArea class="form-control" style="resize:none" rows="3" id="address" name="address" placeholder="Address" >${studentInstance?.address}</g:textArea>
				  							<span class="control-label"> <g:fieldError bean="${studentInstance}" field="address"></g:fieldError> </span>
				  						</div>
				  					</div>
				  					<div class="row">
				  						<div class="form-group col-sm-6 ${hasErrors(bean:studentInstance,field:'higestQulification', 'has-error')}">
				  							<label>Highest Qualification</label>
				  							<g:select name="higestQulification" class="form-control" optionValue="value" from="${com.grails.Student.HigestQulification?.values()}" value="${studentInstance?.higestQulification}"  noSelection="['':'Select Highest Qualification']"/>
				  							<span class="control-label"> <g:fieldError bean="${studentInstance}" field="higestQulification"></g:fieldError> </span>
				  							<br>
				  						</div>
				  					</div>
				  					<div class="row">
				  						<div class="form-group col-sm-6 ${hasErrors(bean:studentInstance,field:'languages', 'has-error')}">
				  							<label>Languages Known</label>
				  							<g:select name="languages" class="form-control custom-scroll" multiple="true" from="${com.grails.Student.Languages?.collect{it?.toString()}}" value="${studentInstance?.languages ? studentInstance?.languages?.split(",")?.collect{it?.toString()} : ''}"  noSelection="['':'Select Languages Known']"/>
				  							<span class="control-label"> <g:fieldError bean="${studentInstance}" field="languages"></g:fieldError> </span>
				  						</div>
				  					</div>
				  					<div class="row">
				  						<div class="form-group col-sm-6 has-error">
				  							<label>Hobby</label><br>
				  							<g:each in="${com.grails.Student?.Hobbies?.collect{it?.toString()}}"  >
				  								<g:checkBox name="hobby" value="${it}" checked="${studentInstance?.hobby? (studentInstance?.hobby?.split(",")?.collect{ value -> value?.toString()}?.contains(it) ? true : false):false}"  /> ${it}
				  							</g:each>
				  							<br>
											<span class="control-label"> <g:fieldError bean="${studentInstance}" field="hobby"></g:fieldError> </span>
				  						</div>
				  					</div>
				  					<div class="row">
				  						<div class="form-group col-sm-6 has-error">
			  								<g:checkBox name="termAndCondition" value="${studentInstance?.termAndCondition?.equals(Boolean?.TRUE) ? true : false}" checked="${studentInstance?.termAndCondition?.equals(Boolean?.TRUE) ? true : false}" />  <label>Terms and Conditions </label>
					  						<br>
											<span class="control-label"> <g:fieldError bean="${studentInstance}" field="termAndCondition"></g:fieldError> </span>
				  						</div>
				  					</div>
				  					<div>
										<g:submitButton name="register" value="${edit ? 'Update':'Register'}" title="${edit ? 'Update':'Register'}" class="btn btn-primary"/>
										<g:if test="${edit}">
											<g:link controller="student" action="list" style="cursor: pointer;text-decoration:none;" title="Cancel" class="btn btn-warning" >Cancel</g:link>
										</g:if>
									</div>
			  					</g:form>
			  				</div>
			  			</div>
	  				</div>
	  			</div>
	  		<!--  Page content -->
		  </div>
		</div>
    </div>

    <footer>
         <div class="container">
            <div class="copy text-center">
               Copyright 2014 <a href='#'>Website</a>
            </div>
         </div>
    </footer>
    <g:javascript src="../bootstrap/js/bootstrap.min.js"/>
    <g:javascript src="../vendors/form-helpers/js/bootstrap-formhelpers.min.js"/>
    <g:javascript src="../vendors/select/bootstrap-select.min.js"/>
    <g:javascript src="../vendors/tags/js/bootstrap-tags.min.js"/>
    <!-- 
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="vendors/form-helpers/js/bootstrap-formhelpers.min.js"></script>
    <script src="vendors/select/bootstrap-select.min.js"></script>
    <script src="vendors/tags/js/bootstrap-tags.min.js"></script>
     -->
  </body>
</html>