import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Employee } from './Entities/Employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http:HttpClient) { }

  private URL = environment.apiBaseURL;
  

  getEmployees(params:HttpParams):Observable<any>{
    return this.http.get<any>(this.URL+"/employees/paging",{params});
  }

  getEmployee(id:number):Observable<Employee>{
    return this.http.get<Employee>(this.URL+"/employees/"+id);
  }

  addEmployee(employee:Employee):Observable<Employee>{
    return this.http.post<Employee>(this.URL+"/employees",employee);
  }

  updateEmployee(employee:Employee,id:number):Observable<Employee>{
    return this.http.put<Employee>(this.URL+"/employees/"+id,employee);
  }

  deleteEmployee(id:number):Observable<any>{
    return this.http.delete(this.URL+"/employees/"+id);
  }
}
