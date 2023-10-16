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
    'Authorization': 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYWhhci5raGVtaXNzaTFAZ21haWwuY29tIiwiaWF0IjoxNjk2ODAzMzQ3LCJleHAiOjE3MDQ4MDMzNDd9.pS_x2NW3LlCYNvEDNNLccBl15sbP3lOnMn3KT2_WcAs'
  });
  constructor(private http :HttpClient) { }
  // Register employee
  public registerEmployee(employee:Employee):Observable<Employee>{
    return this.http.post<Employee>(`${this.apiServiceUrl}/api/admin/register-employee`,employee,{headers:this.headers});
  } 
  // Get employees
  public getEmployees():Observable<Employee[]>{
    return this.http.get<Employee[]>(`${this.apiServiceUrl}/api/admin/get-employees`,{headers:this.headers});
  } 

}
