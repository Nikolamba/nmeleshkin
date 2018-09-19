<%--
  Created by IntelliJ IDEA.
  User: Kalampus
  Date: 03.09.2018
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script>
        function validate() {
            var result = true;
            if ($('#name').val() == '') {
                alert($('#name').attr('title'));
                result = false;
            }
            if ($('#lastname').val() == '') {
                alert($('#lastname').attr('title'));
                result = false
            }
            return result;
        }
        function addRow() {
            if (validate()) {
                var name = ($('#name').val());
                var lastname = ($('#lastname').val());
                $('#table tr:last').after('<tr><td>' + name + '</td>' +
                    '<td>' + lastname +'</td></tr>');
                console.log("addRow() method was invoked");
            }
        }

        function ajaxMethod() {
            var data = {'name' : $('#name').val() , 'lastname' : $('#lastname').val()};
            $.ajax({
                type: 'POST',
                url: '${pageContext.servletContext.contextPath}/ajax',
                data: JSON.stringify(data),
                dataType: 'json',
                contentType: "application/json; charset=UTF-8",
                error: [function(xhr, status, error) {
                    alert(xhr.responseText + '|\n' + status + '|\n' +error);
                }],
                success: [function (response) {
                    var tableData = "<tr><td>" + response.name + "</td><td>" + response.lastname + "</td></tr>";
                    $('#table tr:last').after(tableData);
                }]
            });
        }
    </script>
    <style>
        #user_form {
            margin: 0px 40% 0px 40%;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <h1>My First Bootstrap Page</h1>
    <p>This is some text.</p>
</div>

<form id="user_form">
    <div class="form-group">
        <label for="name">Name</label>
        <input type="text" class="form-control" id="name" title="Enter user name">
    </div>
    <div class="form-group">
        <label for="lastname">Lastname</label>
        <input type="text" class="form-control" id="lastname" title="Enter lastname">
    </div>
    <div class="form-group">
        <label for="sex">Sex</label>
        <select class="form-control" id="sex">
            <option>Male</option>
            <option>Female</option>
        </select>
    </div>
    <div class="form-group">
        <label for="description">Description</label>
        <textarea class="form-control" id="description"></textarea>
    </div>

    <div class="checkbox">
        <label><input type="checkbox"> Remember me</label>
    </div>
    <button type="submit" class="btn btn-default" onclick="validate();">Submit</button>
    <button type="button" class="btn btn-default" onclick="addRow()">Add row</button>
    <button type="button" class="btn btn-default" onclick="ajaxMethod()">Send to server</button>
</form>

<div class="container">
    <h2>Hover Rows</h2>
    <p>The .table-hover class enables a hover state on table rows:</p>
    <table id="table" class="table table-hover">
        <thead>
        <tr>
            <th>Firstname</th>
            <th>Lastname</th>
            <th>Email</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>John</td>
            <td>Doe</td>
            <td>john@example.com</td>
        </tr>
        <tr>
            <td>Mary</td>
            <td>Moe</td>
            <td>mary@example.com</td>
        </tr>
        <tr>
            <td>July</td>
            <td>Dooley</td>
            <td>july@example.com</td>
        </tr>
        </tbody>
    </table>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
