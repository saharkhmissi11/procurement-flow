import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Email } from 'classes/Email';
import { Employee } from 'classes/Employee';
import { EmailService } from 'src/app/services/email.service';
import { EmployeeService } from 'src/app/services/employee.service';
@Component({
  selector: 'app-register-employees',
  templateUrl: './register-employees.component.html',
  styleUrls: ['./register-employees.component.css']
})
export class RegisterEmployeesComponent implements OnInit {
  employee: any = {};
  email:any={}
  constructor(private employeeService: EmployeeService,private emailService:EmailService) { }

  ngOnInit(): void {
  }
  //Register employee
  registerEmployee(addForm: NgForm) {
    this.employeeService.registerEmployee(addForm.value).subscribe(
      (response: Employee) => {
        
        this.email.toEmail="sahar.khmissi1@gmail.com"
        this.email.subject="Account Credentials"
        this.email.body="Dear"+addForm.value.firstname+addForm.value.lastname+", "+"Please find below your credentials .. Email : "+addForm.value.email+" and  Password : "+addForm.value.password
        this.sendEmail()
        console.log("email" + this.email.body)
      },
      (error: HttpErrorResponse) => {
        alert("erreur" + error.message);
      },
    );
    console.log("email" + this.employee.email)
  }
  //Send Email to new employee to inform him
  sendEmail(){
    this.emailService.sendEmail(this.email).subscribe(
      (response: Email) => {this.emailService.sendEmail(this.email)},
      (error: HttpErrorResponse) => {
        alert("erreur" + error.message);
      },
    )
  }
}
