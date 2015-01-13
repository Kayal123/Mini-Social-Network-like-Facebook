<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<pre>
		<table style="width: auto !important;">
			<tr>
				<td>
				<label path="name">Name</label>
				</td>
				<td>
				${org.name}
				</td>
			</tr>
			<tr>
				<td>
				<label path="street">Address</label>
				</td>
				<td>
				${org.street}
				${org.city}
				${org.state}  ${org.zip}
				</td>
			</tr>
			<tr>
				<td>
				<label path="description">Description</label>
				</td>
				<td>
				${org.description}
				</td>
			</tr>
		</table>
	</pre>
