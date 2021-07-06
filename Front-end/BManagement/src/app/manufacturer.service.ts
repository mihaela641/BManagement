import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Manufacturer } from './Entities/Manufacturer';

@Injectable({
  providedIn: 'root'
})
export class ManufacturerService {
  private URL = environment.apiBaseURL;
  
  constructor(private http: HttpClient) { }

  getManufacturersArray():Observable<any>{
    return this.http.get<any>(this.URL+"/manufacturers");
  }

  getManufacturers(params:HttpParams):Observable<any>{
    return this.http.get<any>(this.URL+"/manufacturers/paging",{params});
  }

  getManufacturer(id:number):Observable<Manufacturer>{
    return this.http.get<Manufacturer>(this.URL+"/manufacturers/"+id);
  }

  addManufacturer(manufacturer:Manufacturer):Observable<Manufacturer>{
    return this.http.post<Manufacturer>(this.URL+"/manufacturers",manufacturer);
  }

  updateManufacturer(manufacturer:Manufacturer,id:number):Observable<Manufacturer>{
    return this.http.put<Manufacturer>(this.URL+"/manufacturers/"+id,manufacturer);
  }

  deleteManufacturer(id:number):Observable<any>{
    return this.http.delete(this.URL+"/manufacturers/"+id);
  }
}
