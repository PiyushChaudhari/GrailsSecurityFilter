<!DOCTYPE html>
<html>
  <head>
    <title>Page not found</title>
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
    <g:javascript library="jquery" />
	<r:require modules="jquery-ui"/>
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
	<br><br><br>
	
	<div class="row">
		<div class="col-md-6 col-md-offset-3 ">
			<div class="content-box-header">
				<div class="panel-title"><h1>Page not found! (404 Error)</h1></div>
			</div>
			<div class="content-box-large box-with-header">
	 			Sorry, the page your requested may have been moved or deleted.
	 			<g:if test="${session?.student}">
	 				<g:link controller="student" action="list" >click here.</g:link>
	 			</g:if>
	 			<g:else>
					<g:link controller="student" action="index">click here.</g:link>	 				
	 			</g:else>
			<br><br>
			</div>
		</div>
	</div>

    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <g:javascript src="../bootstrap/js/bootstrap.min.js"/>
    <g:javascript src="../js/custom.js"/>
  </body>
</html>