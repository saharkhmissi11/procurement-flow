import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Employee } from 'classes/Employee';
@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private apiServiceUrl=environment.apiBaseUrl;
   headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYWhhci5raG1pc3NpQGdtYWlsLmNvbSIsImlhdCI6MTY5OTA5OTg5MSwiZXhwIjoxNzA3MDk5ODkxfQ.9csQV2leTEwSsxlB5V9ULDpMK0kIPRYEoZiN9rzr0Lo'
  });
  constructor(private http :HttpClient) { }
  // Register employee
  public registerEmployee(employee:Employee):Observable<Employee>{
    return this.http.post<Employee>(`${this.apiServiceUrl}/api/user/add`,employee,{headers:this.headers});
  } 
  // Get employees
  public getEmployees():Observable<Employee[]>{
    return this.http.get<Employee[]>(`${this.apiServiceUrl}/api/user/all`,{headers:this.headers});
  } 

}
