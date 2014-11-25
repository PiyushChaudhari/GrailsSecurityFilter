<!DOCTYPE html>
<html>
  <head>
    <title>Sign Up</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
	<link rel="icon" href="${resource(dir: '/images', file: 'security_filter.png')}" type="text/css">    
    <link rel="stylesheet" href="${resource(dir: 'bootstrap/css', file: 'bootstrap.min.css')}" type="text/css">
    
    <!-- styles -->
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'styles.css')}" type="text/css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  </head>
  <body class="login-bg">
  	<div class="header">
	     <div class="container">
	        <div class="row">
	           <div class="col-md-12">
	              <!-- Logo -->
	              <div class="logo">
	                 <h1><a href="http://grails.org/doc/2.2.1/ref/Plug-ins/filters.html">Grails Security Filter</a></h1>
	              </div>
	           </div>
	        </div>
	     </div>
	</div>
	<br><br><br>
	<div class="page-content container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-wrapper">
			        <div class="box">
			            <div class="content-wrap">
			            	<g:form controller="student"  action="create" class="form-horizontal">			            	
	                            <fieldset>
		                            <div class="division">
		                                <hr class="left">
		                                <span>Sign Up</span>
		                                <hr class="right">
		                            </div>
			                        <div class="form-group has-error" align="left">
												<label>E-mail address</label>
												<g:textField name="emailId" value="${studentInstance?.emailId}" class="form-control" placeholder="E-mail address"/>
												<span class="control-label"> <g:fieldError bean="${studentInstance}" field="emailId"></g:fieldError> </span>
									</div>
					                <div class="form-group has-error" align="left">
										<label>Password</label>
										<g:passwordField name="password" class="form-control" placeholder="Password"/>
										<span class="control-label"> <g:fieldError bean="${studentInstance}" field="password"></g:fieldError> </span>
									</div>
					                <div class="form-group has-error" align="left">
										<label>Confirm Password</label>
										<g:passwordField name="confirmPassword" class="form-control" placeholder="Confirm Password"/>
										<span class="control-label"> <g:fieldError bean="${studentInstance}" field="confirmPassword"></g:fieldError> </span>
									</div>
									<div class="">
					                    <g:submitButton name="signUp" value="Sign Up" class="btn btn-primary signup"/>
					                </div>
								</fieldset>
							</g:form>              
			            </div>
			        </div>
			        <div class="already">
			            <p>Have an account already?</p>
			            <g:link controller="student" action="index" >Login</g:link>
			        </div>
			    </div>
			</div>
		</div>
	</div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://code.jquery.com/jquery.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="js/custom.js"></script>
  </body>
</html>