<html>
<head>
	<script type="text/javascript" src="js/jquery-2.1.0.js"></script>
	<script type="text/javascript" src="js/FacebookLogin.js"></script>
</head>
<body>
<div id="fb-root"></div>

<!--
  Below we include the Login Button social plugin. This button uses the JavaScript SDK to
  present a graphical Login button that triggers the FB.login() function when clicked. -->
<!--
<fb:login-button show-faces="true" width="200" max-rows="1"></fb:login-button> -->

<button onclick="loginToFacebook()">Login To Facebook</button>

<fb:login-button  scope="publish_stream"></fb:login-button>

<div id="post" style="display: none">
	<textarea id="comment" rows="20" cols="100"></textarea>
	<button onclick="postComment('comment');">Post Comment</button>
</div>
</body>
</html>