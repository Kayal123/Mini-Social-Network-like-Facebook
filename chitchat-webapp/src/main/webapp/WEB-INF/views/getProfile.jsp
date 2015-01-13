<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

  		<div class="row">
    		<div class="col-sm-4">
				<label class="pull-right" path="firstname">First Name</label>
    		</div>
    		<div class="col-sm-4">
				${profile.firstname}
    		</div>
  		</div>
  		<div class="row">
    		<div class="col-sm-4">
				<label class="pull-right" path="lastname">Last Name</label>
     		</div>
    		<div class="col-sm-4">
				${profile.lastname}
    		</div>
  		</div>
  		<div class="row">
    		<div class="col-sm-4">
				<label class="pull-right" path="email">E-mail</label>
    		</div>
    		<div class="col-sm-4">
				${profile.email}
    		</div>
  		</div>
  		<div class="row">
    		<div class="col-sm-4">
				<label class="pull-right" path="street">Address</label>
     		</div>
    		<div class="col-sm-4">
				${profile.street}
				${profile.city}
				${profile.state}  ${profile.zip}
    		</div>
  		</div>
  		<div class="row">
    		<div class="col-sm-4">
				<label class="pull-right" path="description">Description</label>
    		</div>
    		<div class="col-sm-4">
				${profile.description}
    		</div>
  		</div>

