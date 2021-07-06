import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductRecipeService } from '../product-recipe.service';


@Component({
  selector: 'app-product-recipe',
  templateUrl: './product-recipe.component.html',
  styleUrls: ['./product-recipe.component.css']
})
export class ProductRecipeComponent implements OnInit {

  productRecipes: Array<any>;
  page:number = 1;
  items:number = 0;
  itemsPerPage:number= 10;
  sortBy:string = '';
  sortDirection:string = '';

  inputRecipe:string= "";
  inputShop:string="";
  inputProduct:string= "";

  params:HttpParams = new HttpParams().append('page', this.page)
  .append('size', this.itemsPerPage)

  constructor(private service:ProductRecipeService,private router:Router) { 
    this.productRecipes = new Array<any>();
  }


  ngOnInit(): void {
    this.getProductRecipes();
  }

  getProductRecipes(){
    console.log(this.params);
    this.service.getProductRecipes(this.params).subscribe(data=>{
      console.log(data);
      this.productRecipes=data['content'];
      this.items = data['totalElements'];
    });
  }
  
  deleteProductRecipe(id:number){
    this.service.deleteProductRecipe(id).subscribe(data=>{
      console.log(data);
      this.getProductRecipes();
    },error=>console.log(error));
  }

  updateProductRecipe(id:number){
    this.router.navigate(["/update-productRecipe",id]);
  }

  changePage(event:any){
    this.page=event;
    this.params= this.params.set('page',event);
    this.getProductRecipes();
  }

  search(){
    if(this.inputRecipe.trim()!=""){
      this.params = this.params.set('recipeName',this.inputRecipe);
    }
    if(this.inputProduct.trim()!=""){
      this.params = this.params.set('productName' , this.inputProduct);
    }
    this.getProductRecipes();
  }

  clear(){
    this.params = this.params.delete('recipeName');
    this.params = this.params.delete('productName');
    this.inputRecipe="";
    this.inputProduct="";
    this.getProductRecipes();
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

    if(by=="recipeName"){
      this.params = this.params.set('sortBy',"recipeName");
      if(this.params.get('sortDirection')=="asc"){
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"desc");
      }else{
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"asc");
      }
    }
    this.getProductRecipes();
  }

}
