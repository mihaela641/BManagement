import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Manufacturer } from '../Entities/Manufacturer';
import { Product } from '../Entities/Product';
import { Type } from '../Entities/Type';
import { ManufacturerService } from '../manufacturer.service';
import { ProductService } from '../product.service';
import { TypeService } from '../type.service';

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.css']
})
export class CreateProductComponent implements OnInit {
  product:Product = new Product();
  message:string = "";

  manufacturers:Manufacturer[] = new Array();
  types:Type[] = new Array();

  //inputManufacturer:Manufacturer = new Manufacturer();
  //inputType:Type = new Type();

  constructor(private service:ProductService
    ,private router:Router
    ,private manufacturerService:ManufacturerService
    ,private typeService:TypeService) { }

  ngOnInit(): void {
    this.getManufacturers();
    this.getTypes();
  }

  getManufacturers(){
    this.manufacturerService.getManufacturersArray().subscribe(data=>this.manufacturers=data);
  }
  getTypes(){
    this.typeService.getTypesArray().subscribe(data=>this.types=data);
  }

  goToList(){
    this.router.navigate(["/products"]);
  }

  saveProduct(){
    this.service.addProduct(this.product).subscribe(data=>{
      console.log(data);
    },error => console.error(error));
    this.goToList();
  }
  onSubmit(){
  if(this.product.name.trim()!=""
    &&this.product.dateAdded.toString().trim()!=""
    &&this.product.manufacturer.name.trim()!=""
    &&this.product.price>0
    &&this.product.type!=undefined){
    this.saveProduct();
  }else{
    this.message = "Please enter valid input";
  }
}
}
