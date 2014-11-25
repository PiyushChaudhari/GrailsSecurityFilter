<!DOCTYPE html>
<html>
  <head>
    <title>Grails Security Filter</title>
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
	<script type="text/javascript">
		function deleteStudent(studentId){
			var result = confirm("Are you sure you want ot delete this student ?");
			if (result == true) {
			    document.location.href= '${createLink(controller: 'student' , action:'delete')}/'+studentId;
			    return true;
			} else {
			    return false;
			}
		}
	</script>
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
		                          <li><g:link controller="student" action="logout"  class="glyphicon glyphicon-log-out"> Logout</g:link> </li>
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
	<div class="page-content container">
		<div class="row">
					<div class="col-md-10 content-box-large col-md-offset-1">
	  				<div class="panel-heading">
						<div class="panel-title">Student List</div>
					</div>
	  				<div class="panel-body">
	  					<div class="table-responsive">
	  						<table class="table">
				              <thead>
				                <tr>
				                  <th>Id</th>
				                  <th>First Name</th>
				                  <th>Last Name</th>
				                  <th>Email Id</th>
				                  <th>Date Of Birth</th>
				                  <th>Active</th>
				                  <th>Action</th>
				                </tr>
				              </thead>
				              <tbody>
				              <g:if test="${studentList}" >
					                <g:each in="${studentList}" var="studentInstance" >
					                	<tr class="${studentInstance?.isActive ?'success':'danger'}" >
						                  <td><g:fieldValue bean="${studentInstance}" field="id"/></td>
						                  <td><g:fieldValue bean="${studentInstance}" field="firstName"/></td>
						                  <td><g:fieldValue bean="${studentInstance}" field="lastName"/></td>
						                  <td><g:fieldValue bean="${studentInstance}" field="emailId"/></td>
						                  <td><g:formatDate format="yyyy-MM-dd" date="${studentInstance?.dateOfBirth}"/></td>
						                  <td>${studentInstance?.isActive?.toString()?.toUpperCase()}</td>
						                  <td>
						                  	<g:link controller="student" action="register" id="${studentInstance?.id}" params="['edit':'edit']" style="cursor: pointer;text-decoration:none;" class="glyphicon glyphicon-pencil" title="Edit" ></g:link>
						                  	<span onclick="deleteStudent('${studentInstance?.id}')" style="cursor: pointer;" class="glyphicon glyphicon-trash" title="Delete"></span>
						                  </td>
						                </tr>
					                </g:each>
				                </g:if>
				                <g:else>
				                	<tr class="danger" >
				                		<td colspan="100%" align="center"  >No record found.</td>
				                	</tr>
				                </g:else>
				              </tbody>
				            </table>
	  					</div>
	  				</div>
			</div>
		</div>
	</div>



    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <g:javascript src="../bootstrap/js/bootstrap.min.js"/>
    <g:javascript src="../js/custom.js"/>
  </body>
</html>