    package com.tust.app.controller;

    import com.tust.app.domain.Users;
    import com.tust.app.services.UsersService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestParam;

    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpSession;

    @Controller
    public class RegisterController {

        @Autowired
        private UsersService usersService;

        @RequestMapping("/register")
        public String showRegisterPage() {
            // 显示注册页面
            return "register"; // 返回注册页面的视图名，具体视图解析器配置可以根据项目实际情况配置
        }

        @PostMapping("/register")
        public String registerUser(@RequestParam("username") String username,
                                   @RequestParam("password") String password,
                                   @RequestParam("email") String email,
                                   HttpServletRequest request) {

            // 创建一个用户对象
            Users user = new Users();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setState(1); // 设置默认状态
            user.setRoleId(2); // 设置默认角色ID

            // 调用服务层方法进行注册
            Integer result = usersService.registerUser(user);

            // 根据注册结果进行处理
            if (result > 0) {
                // 注册成功，设置成功信息并跳转到注册成功页面
                request.setAttribute("successMsg", "注册成功，请登录！");
                return "redirect:/registSuccess.jsp"; // 注册成功后跳转到注册成功页面
            } else {
                // 注册失败，设置错误信息并返回注册页面重新注册
                request.setAttribute("errorMsg", "注册失败，用户名已存在！");
                return "register"; // 注册失败返回注册页面
            }
        }
    }
