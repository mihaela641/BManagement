import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Type } from './Entities/Type';

@Injectable({
  providedIn: 'root'
})
export class TypeService {
  private URL = environment.apiBaseURL;
  
  constructor(private http: HttpClient) { }

  getTypesArray():Observable<any>{
    return this.http.get<any>(this.URL+"/types");
  }

  getTypes(params:HttpParams):Observable<any>{
    return this.http.get<any>(this.URL+"/types/paging",{params});
  }

  getType(id:number):Observable<Type>{
    return this.http.get<Type>(this.URL+"/types/"+id);
  }

  addType(type: Type):Observable<Type>{
    return this.http.post<Type>(this.URL+"/types",type);
  }
 
  updateType(type:Type,id:number):Observable<Type>{
    return this.http.put<Type>(this.URL+"/types/"+id,type);
  }

  deleteType(id:number):Observable<any>{
    return this.http.delete(this.URL+"/types/"+id);
  }
}
