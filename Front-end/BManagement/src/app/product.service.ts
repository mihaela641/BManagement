import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Product } from './Entities/Product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private URL = environment.apiBaseURL;
  
  constructor(private http: HttpClient) { }

  getProductsArray():Observable<any>{
    return this.http.get<any>(this.URL+"/products");
  }

  getProducts(params:HttpParams):Observable<any>{
    return this.http.get<any>(this.URL+"/products/paging",{params});
  }

  getProduct(id:number):Observable<Product>{
    return this.http.get<Product>(this.URL+"/products/"+id);
  }

  addProduct(product:Product):Observable<Product>{
    return this.http.post<Product>(this.URL+"/products",product);
  }

  updateProduct(product:Product,id:number):Observable<Product>{
    return this.http.put<Product>(this.URL+"/products/"+id,product);
  }

  deleteProduct(id:number):Observable<any>{
    return this.http.delete(this.URL+"/products/"+id);
  }
}
