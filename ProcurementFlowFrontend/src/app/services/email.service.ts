import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Email } from 'classes/Email';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EmailService {
  private apiServiceUrl=environment.apiBaseUrl;
  constructor(private http:HttpClient) { }
  public sendEmail(email:Email):Observable<Email>{
    return this.http.post<Email>(`${this.apiServiceUrl}/api/email/send`,email)
  }
}
