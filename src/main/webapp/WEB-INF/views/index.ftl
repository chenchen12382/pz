<!DOCTYPE html>
<html lang="en" >
    <head>
        <meta charset="utf-8">
        <title>培正信息管理系统</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link href="${ctx}/assets/css/icons.css" rel="stylesheet"/>
        <link href="${ctx}/assets/css/bootstrap.css" rel="stylesheet"/>
        <link href="${ctx}/assets/css/main.css" rel="stylesheet"/>   
    </head>
    <body class="login-page" style="background-image:url(images/0001.jpg); background-size:100%;"  >
        <!-- Start #login -->
        <div id="login" class="animated bounceIn"  >
            <div class="login-wrapper">
                <ul id="myTab" class="nav nav-tabs nav-justified bn">
                    <li>
                        <a href="javascript:void(0);" data-toggle="tab" style="color:red">登录</a>
                    </li>
                </ul>
                <div id="myTabContent" class="tab-content bn">
                    <div class="tab-pane fade active in" id="log-in">
                        <form class="form-horizontal mt10" >
                            <div class="form-group">
                                <div class="col-lg-12">
                                    <input type="text" name="userName" id="userName" class="form-control left-icon" placeholder="请输入用户名">
                                    <i class="ec-user s16 left-input-icon"></i>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-12">
                                    <input type="password" name="password" id="password" class="form-control left-icon" placeholder="请输入密码">
                                    <i class="ec-locked s16 left-input-icon"></i>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-8 col-md-8">
                                    <input type="text" name="code" id="code" class="form-control left-icon" placeholder="请输入验证码">
                                    <i class="ec-locked s16 left-input-icon"></i>
                                </div>
                                <div class="col-md-4">
                                    <img id="codeImg" alt="验证码" src="${ctx}/authCode/code" onclick="changeImg();"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-8">
                                    <!-- col-lg-12 start here -->
                                    <label class="checkbox">
                                        <input type="checkbox" id="remember" value="option">记住用户名?
                                    </label>
                                </div>
                                <!-- col-lg-12 end here -->
                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-4" style="">
                                    <!-- col-lg-12 start here -->
                                    <button class="btn btn-success pull-right" type="button" id="submitBtn" >登录</button>
                                </div>
                                <!-- col-lg-12 end here -->
                            </div>
                        </form>
                    </div>
                </div>
          
            <!-- End #.login-wrapper -->
            
            
        </div>
        </br></br></br></br></br></br>        
            <div region="south" style="height: 30px;padding: 5px;overflow: hidden; color: #080808" align="center">
               ©   网络信息中心2017
             </div>
         </div>
        <script src="${ctx}/jquery-easyui-1.3.3/jquery.min.js" ></script>
        <script src="${ctx}/js/jquery.cookie.js" ></script>
        <script src="${ctx}/js/index.js"></script>
    </body>
</html>