import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Employee } from 'classes/Employee';
import { EmployeeService } from 'src/app/services/employee.service';

@Component({
  selector: 'app-view-employees',
  templateUrl: './view-employees.component.html',
  styleUrls: ['./view-employees.component.css']
})
export class ViewEmployeesComponent implements OnInit {

employees:Employee[]=[]

  constructor(private employeeService:EmployeeService) { }

  ngOnInit(): void {
    this.getEmployees()

  }
  getEmployees(){
    this.employeeService.getEmployees().subscribe(
      (response:Employee[])=>{this.employees=response},
      (error:HttpErrorResponse)=>{alert(error.message)}
    )
  }
  

}
