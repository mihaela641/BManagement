import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Order } from './Entities/Order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private URL = environment.apiBaseURL;

  constructor(private http: HttpClient) { }

  getOrders(params:HttpParams):Observable<any>{
    return this.http.get<any>(this.URL+"/orders/paging",{params});
  }

  getOrder(id: number): Observable<Order>{
    return this.http.get<Order>(this.URL+"/orders/"+ id);
  }

  addOrder(order:Order):Observable<Order>{
    return this.http.post<Order>(this.URL+"/orders",order);
  }

  updateOrder(order:Order,id:number):Observable<Order>{
    return this.http.put<Order>(this.URL+"/orders/"+id,order);
  }

  deleteOrder(id:number):Observable<any>{
    return this.http.delete(this.URL+"/orders/"+id);
  }
}
