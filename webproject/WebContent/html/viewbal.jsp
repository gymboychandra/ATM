<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>View Balance</title>


<style>

body 
{
font-family: Arial, Helvetica, sans-serif;
}

form 
{
border: 3px solid Black;
    margin: auto;
    
     padding:10%;
}

input,select{
  width: 25%;
  padding: 14px 20px;
  margin: 8px 0;
     border: 1px solid #ccc;
  display: inline-block;
 cursor: pointer;
}

.container 
{
    margin: auto;
  width: 40%;
    padding-top: 0px;
 
   
    
}

ul 
{
    
  list-style-type: none;
  margin: 0;
  padding:0 ;
  overflow: hidden;
  background-color: #333;
   
}

li 
{
  float: left;
  border-right:1px solid #bbb;
}

li:last-child 
{
  border-right: none;
}

li a 
{
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

.active 
{
  background-color: #4CAF50;
}
    .reset{
        background-color: red;
  color: white;
  padding: 14px 20px;
  margin: auto;
  border: none;
  cursor: pointer;
  width: 10%;
        
    }
    .reset:hover 
{
  opacity: 0.5;
}
    p{
        margin-top: 0;
        font-size: 12px;
        color:firebrick;
    }
    
</style>
</head>


<body>
<ul>
<li><a class="active" >WIPRO BANK</a></li>
<li style="float:right"><a  href="/webproject/logoutprocess" >Logout</a></li>
</ul>
<form action="/webproject/viewbalprocess" method="post">
   <h2 style="text-align: center; color: blue; padding-bottom: 30px;  ">VIEW BALANCE</h2>
<center>
	<div class="container"></div>
    <label for="psw"><b>Type Of Account</b></label>
   
    <select  name="acctype">
       <option value="savings">Savings</option>
        <option value="current">Current</option>
        </select><br><br>
	
	<label for="psw"><b>Current Balance</b></label>
    <input type="text" name="cbal" value="<%=session.getAttribute("currentbal") %>  INR"  readonly="" ><br><br>
    <br><br>
<input type="submit" style="background-color:green" class="reset">
 </form>
 <button type="button" onclick="window.location.href ='menu.html'"style="background-color:red" class="reset">Cancel</button>

</center>
    

</body>
</html>