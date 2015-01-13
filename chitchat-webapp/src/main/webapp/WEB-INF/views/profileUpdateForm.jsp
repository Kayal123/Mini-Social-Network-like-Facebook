<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<form class="form-horizontal" role="form" action="/chitchat/profile/submitData/${profile.id}" method="post">
	
        <div class="form-group">
                <label class="col-sm-2 control-label">Id</label>
            <div class="col-sm-4">
                ${profile.id}
            </div>
        </div>
	
  		<div class="form-group">
				<label path="firstname" class="col-sm-2 control-label">First Name</label>
    		<div class="col-sm-4">
				<input type="text" class="form-control" name="firstname" value="${profile.firstname}"/>
    		</div>
  		</div>
  		<div class="form-group">
				<label path="lastname" class="col-sm-2 control-label">Last Name</label>
    		<div class="col-sm-4">
				<input type="text" class="form-control" name="lastname" value="${profile.lastname}"/>
		    </div>
		  </div>
		  <div class="form-group">
				<label path="email" class="col-sm-2 control-label">E-mail</label>
    		<div class="col-sm-4">
				<input type="email" class="form-control" name="email" value="${profile.email}"/>
		    </div>
		  </div>
		  <div class="form-group">
				<label path="street" class="col-sm-2 control-label">Street</label>
    		<div class="col-sm-4">
				<input type="text" class="form-control" name="street" value="${profile.street}"/>
		    </div>
		  </div>
		  <div class="form-group">
				<label path="city" class="col-sm-2 control-label">City</label>
		    <div class="col-sm-4">
				<input type="text" class="form-control" name="city" value="${profile.city}"/>
		    </div>
		  </div>
		  <div class="form-group">
				<label path="state" class="col-sm-2 control-label">State</label>
		    <div class="col-sm-4">
				<input type="text" class="form-control" name="state" value="${profile.state}"/>
		    </div>
		  </div>
		  <div class="form-group">
				<label path="zip" class="col-sm-2 control-label">Zip</label>
    		<div class="col-sm-4">
				<input type="text" class="form-control" name="zip" value="${profile.zip}"/>
		    </div>
		  </div>
		  <div class="form-group">
				<label path="description" class="col-sm-2 control-label">Description</label>
		    <div class="col-sm-4">
				<input type="text" class="form-control" name="description" value="${profile.description}"/>
		    </div>
		  </div>
		  <div class="form-group">
				<label path="org" class="col-sm-2 control-label">Organization</label>
		    <div class="col-sm-4">
				<input type="text" class="form-control" name="orgId" value="${profile.orgId}"/>
		    </div>
		  </div>
		  <!--  
		  <div class="form-group">
                <label path="pw" class="col-sm-2 control-label">Password</label>
		    <div class="col-sm-4">
                <input type="password" class="form-control" name="pw"/>
		    </div>
		  </div>
		  -->
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-4">
				<input type="submit" class="btn btn-default" name="SUBMIT">
		    </div>
		  </div>
	</form>
	<script language="JavaScript" type="text/javascript">
	<!--

		document.forms[0].firstname.mandatory = true
		document.forms[0].lastname.mandatory = true
		document.forms[0].email.mandatory = true
		
	//-->
	</script>
