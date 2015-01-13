<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<form class="form-horizontal" role="form" action="/chitchat/org" method="post">
  		<div class="form-group">
				<label path="name" class="col-sm-2 control-label">Name</label>
    		<div class="col-sm-4">
				<input type="text" class="form-control" name="name"/>
    		</div>
  		</div>
		  <div class="form-group">
				<label path="description" class="col-sm-2 control-label">Description</label>
    		<div class="col-sm-4">
				<input type="text" class="form-control" name="description"/>
		    </div>
		  </div>
		  <div class="form-group">
				<label path="street" class="col-sm-2 control-label">Street</label>
    		<div class="col-sm-4">
				<input type="text" class="form-control" name="street"/>
		    </div>
		  </div>
		  <div class="form-group">
				<label path="city" class="col-sm-2 control-label">City</label>
		    <div class="col-sm-4">
				<input type="text" class="form-control" name="city"/>
		    </div>
		  </div>
		  <div class="form-group">
				<label path="state" class="col-sm-2 control-label">State</label>
		    <div class="col-sm-4">
				<input type="text" class="form-control" name="state"/>
		    </div>
		  </div>
		  <div class="form-group">
				<label path="zip" class="col-sm-2 control-label">Zip</label>
    		<div class="col-sm-4">
				<input type="text" class="form-control" name="zip"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-4">
				<input type="submit" class="btn btn-default" name="CreateOrg" value="Create">
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
