<%@ include file="/views/jspf/directive/page.jspf" %>
<%@ include file="/views/jspf/directive/taglib.jspf" %>


<html>

<c:set var="title" value="Login" />
<%@ include file="/views/jspf/head.jspf" %>

<body>

<div class="outer">

  <span class="inner">

      <form id="login_form" action="controller" method="post">

        <input type="hidden" name="command" value="login"/>

        <fieldset >
          <legend>Login</legend>
          <input name="login"/><br/>
        </fieldset><br/>

        <fieldset>
          <legend>Password</legend>
          <input type="password" name="password"/>
        </fieldset><br/>

        <input type="submit" value="Login">
      </form>
  </span>
</div>
</body>
</html>
