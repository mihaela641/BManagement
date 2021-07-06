import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ProductRecipe } from './Entities/ProductRecipe';

@Injectable({
  providedIn: 'root'
})
export class ProductRecipeService {

  private URL = environment.apiBaseURL;
  
  constructor(private http: HttpClient) { }

  getProductsRecipesArray():Observable<any>{
    return this.http.get<any>(this.URL+"/productsRecipes");
  }

  getProductRecipes(params:HttpParams):Observable<any>{
    return this.http.get<any>(this.URL+"/productsRecipes/paging",{params});
  }

  getProductRecipe(id:number):Observable<ProductRecipe>{
    return this.http.get<ProductRecipe>(this.URL+"/productsRecipes/"+id);
  }
  addProductRecipe(productRecipe:ProductRecipe):Observable<ProductRecipe>{
    return this.http.post<ProductRecipe>(this.URL+"/productsRecipes",productRecipe);
  }

  updateProductRecipe(productRecipe:ProductRecipe,id:number):Observable<ProductRecipe>{
    return this.http.put<ProductRecipe>(this.URL+"/productsRecipes/"+id,productRecipe);
  }

  deleteProductRecipe(id:number):Observable<any>{
    return this.http.delete(this.URL+"/productsRecipes/"+id);
  }
}
