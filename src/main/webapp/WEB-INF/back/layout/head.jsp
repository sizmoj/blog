<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">admin</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath}/index">主页</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                       <!--  <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                        </li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                        </li>
                        <li class="divider"></li> -->
                        <li><a href="${pageContext.request.contextPath}/back/loginOut"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                </li>
            </ul>

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li>
                            <a href="${pageContext.request.contextPath}/back/postList"><i class="fa fa-edit fa-fw"></i>文章</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/back/termList"><i class="fa fa-sitemap fa-fw"></i>类别</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/back/linkList"><i class="fa fa-tasks fa-fw"></i>友情连接</a>
                        </li>
                        
                        <li>
                            <a href="${pageContext.request.contextPath}/back/updateProjects"><i class="fa fa-files-o fa-fw"></i>项目</a>
                        </li>
                        
                        <li>
                            <a href="${pageContext.request.contextPath}/back/updateAbout"><i class="fa fa-wrench fa-fw"></i>关于</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/back/mdpassword"><i class="fa fa-bar-chart-o fa-fw"></i>密码修改</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    