import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RecipeService } from '../recipe.service';

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css']
})
export class RecipeComponent implements OnInit {

  recipes: Array<any>;
  page:number = 1;
  items:number = 0;
  itemsPerPage:number= 10;
  sortBy:string = '';
  sortDirection:string = '';

  inputName:string = "";

  params:HttpParams = new HttpParams().append('page', this.page)
  .append('size', this.itemsPerPage)

  constructor(private service:RecipeService,private router:Router) { 
    this.recipes = new Array<any>();
  }

  ngOnInit(): void {
    this.getRecipes();
  }

  getRecipes(){
    console.log(this.params);
    this.service.getRecipes(this.params).subscribe(data=>{
      console.log(data);
      this.recipes=data['content'];
      this.items = data['totalElements'];
    });
  }

  deleteRecipe(id:number){
    this.service.deleteRecipe(id).subscribe(data=>{
      console.log(data);
      this.getRecipes();
    },error=>console.log(error));
  }

  updateRecipe(id:number){
    this.router.navigate(["/update-recipe",id]);
  }

  changePage(event:any){
    this.page=event;
    this.params= this.params.set('page',event);
    this.getRecipes();
  }

  search(){
    if(this.inputName.trim()!=""){
      this.params = this.params.set('name',this.inputName);
    }
    this.getRecipes();
  }

  clear(){
    this.params = this.params.delete('name');
    this.inputName="";
    this.getRecipes();
  }
  sort(by:any){
    if(by=="id"){
      this.params = this.params.set('sortBy',"id");
      if(this.params.get('sortDirection')=="asc"){
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"desc");
      }else{
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"asc");
      }
    }
    if(by=="name"){
      this.params = this.params.set('sortBy',"name");
      if(this.params.get('sortDirection')=="asc"){
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"desc");
      }else{
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"asc");
      }
    }

    this.getRecipes();
  }  
}
