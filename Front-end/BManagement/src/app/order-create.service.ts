import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { OrderCreate } from './Entities/OrderCreate';

@Injectable({
  providedIn: 'root'
})
export class OrderCreateService {
  private URL = environment.apiBaseURL;
  constructor(private http: HttpClient) { }

  getMyOrders(params:HttpParams):Observable<any>{
    return this.http.get<any>(this.URL+"/productsShops/paging",{params});
  }

  getMyOrder(id:number):Observable<OrderCreate>{
    return this.http.get<OrderCreate>(this.URL+"/productsShops/"+id);
  }

  addMyOrder(productShop:OrderCreate):Observable<OrderCreate>{
    return this.http.post<OrderCreate>(this.URL+"/productsShops",productShop);
  }
  updateMyOrder(productShop:OrderCreate,id:number):Observable<OrderCreate>{
    return this.http.put<OrderCreate>(this.URL+"/productsShops/"+id,productShop);
  }

  deleteMyOrder(id:number):Observable<any>{
    return this.http.delete(this.URL+"/productsShops/"+id);
  }
}
