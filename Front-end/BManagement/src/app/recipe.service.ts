import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Recipe } from './Entities/Recipe';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  private URL = environment.apiBaseURL;
  
  constructor(private http: HttpClient) { }

  getRecipes(params:HttpParams):Observable<any>{
    return this.http.get<any>(this.URL+"/recipes/paging",{params});
  }
  
  getRecipe(id: number): Observable<Recipe>{
    return this.http.get<Recipe>(this.URL+"/recipes/"+ id);
  }

  addRecipe(recipe:Recipe):Observable<Recipe>{
    return this.http.post<Recipe>(this.URL+"/recipes",recipe);
  }

  updateRecipe(recipe:Recipe,id:number):Observable<Recipe>{
    return this.http.put<Recipe>(this.URL+"/recipes/"+id,recipe);
  }

  deleteRecipe(id:number):Observable<any>{
    return this.http.delete(this.URL+"/recipes/"+id);
  }


}
