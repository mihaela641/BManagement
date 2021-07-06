import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Address } from './Entities/Address';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class AddressService {
  private URL = environment.apiBaseURL;
  constructor(private http:HttpClient) { }
  
  getAddressesArray():Observable<any>{
    return this.http.get<any>(this.URL+"/addresses");
  }

  getAddresses(params:HttpParams):Observable<any>{
    return this.http.get<any>(this.URL+"/addresses/paging",{params});
  }

  getAddress(id:number):Observable<any>{
    return this.http.get<Address>(this.URL+"/addresses/"+id);
  }

  addAddress(address:Address):Observable<Address>{
    return this.http.post<Address>(this.URL+"/addresses",address);
  }

  updateAddress(address:Address,id:number):Observable<Address>{
    return this.http.put<Address>(this.URL+"/addresses/"+id,address);
  }
 
  deleteAddress(id:number):Observable<any>{
    return this.http.delete(this.URL+"/addresses/"+id);
  }
}
