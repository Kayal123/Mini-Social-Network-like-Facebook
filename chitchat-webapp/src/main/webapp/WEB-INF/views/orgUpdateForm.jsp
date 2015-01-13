<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<pre>
	<form action="/chitchat/org/submitData/${new_org.id}" method="post">
		<table>
			<tr>
				<td>
				<label path="name">Name</label>
				</td>
				<td>
				<input type="text" name="name"/>
				</td>
			</tr>
			<tr>
				<td>
				<label path="street">Street</label>
				</td>
				<td>
				<input type="text" name="street"/>
				</td>
			</tr>
			<tr>
				<td>
				<label path="city">City</label>
				</td>
				<td>
				<input type="text" name="city"/>
				</td>
			</tr>
			<tr>
				<td>
				<label path="state">State</label>
				</td>
				<td>
				<input type="text" name="state"/>
				</td>
			</tr>
			<tr>
				<td>
				<label path="zip">Zip</label>
				</td>
				<td>
				<input type="text" name="zip"/>
				</td>
			</tr>
			<tr>
				<td>
				<label path="description">Description</label>
				</td>
				<td>
				<input type="text" name="description"/>
				</td>
			</tr>
		</table>
		<input type="submit" name="SUBMIT">
	</form>
</pre>