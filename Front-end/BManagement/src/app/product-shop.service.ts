import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ProductShop } from './Entities/ProductShop';

@Injectable({
  providedIn: 'root'
})
export class ProductShopService {

  private URL = environment.apiBaseURL;
  
  constructor(private http: HttpClient) { }

  getProductsShopsArray():Observable<any>{
    return this.http.get<any>(this.URL+"/productsShops");
  }

  getProductsShops(params:HttpParams):Observable<any>{
    return this.http.get<any>(this.URL+"/productsShops/paging",{params});
  }

  getProductShop(id:number):Observable<ProductShop>{
    return this.http.get<ProductShop>(this.URL+"/productsShops/"+id);
  }

  addProductShop(productShop:ProductShop):Observable<ProductShop>{
    return this.http.post<ProductShop>(this.URL+"/productsShops",productShop);
  }

  updateProductShop(productShop:ProductShop,id:number):Observable<ProductShop>{
    return this.http.put<ProductShop>(this.URL+"/productsShops/"+id,productShop);
  }

  deleteProductShop(id:number):Observable<any>{
    return this.http.delete(this.URL+"/productsShops/"+id);
  }
}
