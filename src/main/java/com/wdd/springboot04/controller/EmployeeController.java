package com.wdd.springboot04.controller;

import com.wdd.springboot04.dao.DepartmentDao;
import com.wdd.springboot04.dao.EmployeeDao;
import com.wdd.springboot04.entities.Department;
import com.wdd.springboot04.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;
    //获取所有员工
    @GetMapping("/emps")
    public String list(Model model){
        //获取所有员工
        Collection<Employee> all = employeeDao.getAll();
        //将获取到的员工存进session域中
        model.addAttribute("emps",all);
        //thymeleaf默认就会进行拼串；前缀是classpath:/templates/xxxx.html
        return "emp/list";
    }
    @GetMapping("/addEmp")
    public String toAddPage(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        //返回到添加页面
        return "emp/add";
    }
   @PostMapping("/addEmp")
    public String addEmp(Employee employee){
        System.out.println("保存的员工是："+employee);
        employeeDao.save(employee);
        //来到员工列表页面
        //redirect：重定向到一个地址
        //forward:转发到一个地址
        return "redirect:/emps";
    }
    //来到修改页面，查出当前员工，并进行回显
    @GetMapping("/editEmp/{id}")
    public String toEditPage( @PathVariable("id") Integer id, Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);
        //页面显示所有的部门信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/add";
    }
    //进行员工修改，需要传递该员工的id
    @PutMapping("/addEmp")
    public String updateEmp(Employee employee){
        System.out.println("收到的员工信息："+employee);
        System.out.println(employeeDao);
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    //员工删除方法
    @DeleteMapping("/deleteEmp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
