import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Shop } from './Entities/Shop';

@Injectable({
  providedIn: 'root'
})
export class ShopService {
  private URL = environment.apiBaseURL;
  
  constructor(private http: HttpClient) { }

  getShopsArray():Observable<any>{
    return this.http.get<any>(this.URL+"/shops");
  }

  getShops(params:HttpParams):Observable<any>{
    return this.http.get<any>(this.URL+"/shops/paging",{params});
  }

  getShop(id:number):Observable<Shop>{
    return this.http.get<Shop>(this.URL+"/shops/"+id);
  }

  addShop(shop:Shop):Observable<Shop>{
    return this.http.post<Shop>(this.URL+"/shops",shop);
  }

  updateShop(shop:Shop,id:number):Observable<Shop>{
    return this.http.put<Shop>(this.URL+"/shops/"+id,shop);
  }

  deleteShop(id:number):Observable<any>{
    return this.http.delete(this.URL+"/shops/"+id);
  }
}
