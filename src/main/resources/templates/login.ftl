<!DOCTYPE html>
<head></head>
<body>
登录
<form action="/login" method="post">
    <#if error??>密码或用户名错误</#if>
    <#if logout??>注销成功</#if>
    <#if expired??>您的账号在另一地方登录，你特么被顶下去了！</#if>
    <p>
        <label for="username">Username</label>
        <input type="text" id="username" name="username"/>
    </p>
    <p>
        <label for="password">Password</label>
        <input type="password" id="password" name="password"/>
    </p>
    <p>
        <input type="checkbox" class="remember" id="remember-me" name="remember-me"><label for="remember-me">一周内记住我</label>
    </p>
    <button type="submit" class="btn">Log in</button>
</form>
</body>
</html>