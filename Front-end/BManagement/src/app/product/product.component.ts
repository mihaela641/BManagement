import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  products: Array<any>;
  page:number = 1;
  items:number = 0;
  itemsPerPage:number= 10;

  inputName:string = "";
  inputDateFrom:string = "";
  inputDateTo:string = "";

  params:HttpParams = new HttpParams().append('page', this.page)
  .append('size', this.itemsPerPage)
  

  constructor(private service:ProductService, private router:Router) {
    this.products = new Array();
   }

  ngOnInit(): void {
    this.getProducts();
  }

  getProducts(){
    this.service.getProducts(this.params).subscribe(data=>{
      console.log(data);
      this.products=data['content'];
      this.items = data['totalElements'];
    });
  }

  deleteProduct(id:number){
    this.service.deleteProduct(id).subscribe(data=>{
      console.log(data);
      this.getProducts();
    },error=>console.log(error));
  }
  updateProduct(id:number){
    this.router.navigate(["/update-product",id]);
  }
  
  changePage(event:any){
    this.page=event;
    this.params= this.params.set('page',event);
    this.getProducts();
  }

  search(){
    if(this.inputName.trim()!=""){
      this.params = this.params.set('name',this.inputName);
    }
    if(this.inputDateTo.trim()!=""){
      this.params = this.params.set('dateTo',this.inputDateTo);
    }
    if(this.inputDateFrom.trim()!=""){
      this.params = this.params.set('dateFrom',this.inputDateFrom);
    }
    console.log(this.params);
    this.getProducts();
  }

  clear(){
    this.params = this.params.delete('name');
    this.params = this.params.delete('dateFrom');
    this.params = this.params.delete('dateTo');
    this.inputName="";
    this.inputDateTo = "";
    this.inputDateFrom= "";
    this.getProducts();
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
    if(by=="type"){
      this.params = this.params.set('sortBy',"type");
      if(this.params.get('sortDirection')=="asc"){
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"desc");
      }else{
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"asc");
      }
    }
    if(by=="price"){
      this.params = this.params.set('sortBy',"price");
      if(this.params.get('sortDirection')=="asc"){
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"desc");
      }else{
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"asc");
      }
    }
    if(by=="dateAdded"){
      this.params = this.params.set('sortBy',"dateAdded");
      if(this.params.get('sortDirection')=="asc"){
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"desc");
      }else{
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"asc");
      }
    }
    if(by=="manufacturer"){
      this.params = this.params.set('sortBy',"manufacturer");
      if(this.params.get('sortDirection')=="asc"){
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"desc");
      }else{
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"asc");
      }
    }

    this.getProducts();
  }
}
